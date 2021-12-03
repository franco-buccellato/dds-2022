package domain.repositorios;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import domain.Duenio;
import domain.Mascota;
import domain.Rescate;

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

//  public List findWithName(String name) {
//    return entityManager().createQuery(
//        "SELECT c FROM Customer c WHERE c.name LIKE :custName")
//        .setParameter("custName", name)
//        .setMaxResults(10)
//        .getResultList();
//  }

  public Optional<Duenio> findDuenioMascota(Mascota mascota) {    Query query = entityManager().createNativeQuery("select top 1 d.id from Duenios as d INNER JOIN mascotas as m ON d.id = m.duenio_id WHERE m.id = :mascota_id");
    query.setParameter("mascota_id", mascota.getId());
    return Optional.ofNullable(this.getById(((BigInteger) query.getSingleResult()).longValue()));
  }

  public List<Duenio> listar() {
    return entityManager().createQuery("from Duenio", Duenio.class).getResultList();
  }

}
