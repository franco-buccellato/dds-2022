package domain.repositorios;

import domain.Opcion;
import domain.RespuestaCaracteristicaMascota;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.Collections;
import java.util.List;

public class RepositorioRespuestasCaracteristicas implements WithGlobalEntityManager {
  private List<Opcion> opciones = Collections.emptyList();
  private static final RepositorioRespuestasCaracteristicas INSTANCE = new RepositorioRespuestasCaracteristicas();

  public RepositorioRespuestasCaracteristicas() {
  }

  public static RepositorioRespuestasCaracteristicas getInstance() {
    return INSTANCE;
  }

  public Opcion buscar(Long id) {
    return entityManager().find(Opcion.class, id);
  }

  public void insertar(RespuestaCaracteristicaMascota respuestaCaracteristicaMascota) {
    entityManager().persist(respuestaCaracteristicaMascota);
  }

}
