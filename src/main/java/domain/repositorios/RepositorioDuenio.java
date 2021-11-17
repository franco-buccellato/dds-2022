package domain.repositorios;

import domain.Duenio;
import domain.Mascota;
import domain.Rescate;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class RepositorioDuenio implements WithGlobalEntityManager {
  private List<Duenio> duenios = new ArrayList<>();
  private static RepositorioDuenio INSTANCE = new RepositorioDuenio();

  public RepositorioDuenio() {
  }

  public static RepositorioDuenio getInstance() {
    return INSTANCE;
  }

  public List<Duenio> getDuenios() {
    return duenios;
  }

  public void addDuenio(Duenio duenio) {
    duenios.add(duenio);
  }

  public void informarMascotaRescatada(Rescate rescate, Duenio elDuenio) {
    elDuenio.notificarMascotaEncontrada(rescate);
  }

  public void agregar(Duenio duenio) {
    entityManager().persist(duenio);
  }

  public Duenio getById(Long id) {
    return entityManager().find(Duenio.class, id);
  }

  public Duenio getDuenioByIdUsuario(Long userId) {
    try {
      return entityManager().createQuery("from Duenio d where d.usuario.id = :userId", Duenio.class)
          .setParameter("userId", userId)
          .getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  public Optional<Duenio> findDuenioMascota(Mascota mascota) {
    return duenios.stream().filter(duenio -> duenio.getMascotas().contains(mascota)).findFirst();
  }

  public List<Duenio> listar() {
    return entityManager().createQuery("from Duenio", Duenio.class).getResultList();
  }

}

// TODO: Logica no va