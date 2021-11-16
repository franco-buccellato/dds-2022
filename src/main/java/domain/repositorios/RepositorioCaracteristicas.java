package domain.repositorios;

import domain.ObjetivoPregunta;
import domain.Pregunta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class RepositorioCaracteristicas implements WithGlobalEntityManager {
  private List<Pregunta> caracteristicasDisponibles = Collections.emptyList();
  private static RepositorioCaracteristicas INSTANCE = new RepositorioCaracteristicas();

  public RepositorioCaracteristicas() {
  }

  public static RepositorioCaracteristicas getInstance() {
    return INSTANCE;
  }

  public RepositorioCaracteristicas(List<Pregunta> caracteristicas) {
    this.caracteristicasDisponibles = new ArrayList<>(caracteristicas);
  }

  public List<Pregunta> getCaracteristicasDisponibles() {
    return caracteristicasDisponibles.stream()
        .filter(caracteristica -> caracteristica.getObjetivos().contains(
            ObjetivoPregunta.CARACTERISTICA_MASCOTA)
        ).collect(Collectors.toList());
  }

  public void addCaracteristicasDisponibles(Pregunta caracteristica) {
    this.caracteristicasDisponibles.add(caracteristica);
  }

  public void removeCaracteristicasDisponibles(Pregunta caracteristica) {
    this.caracteristicasDisponibles.remove(caracteristica);
  }

  public void agregar(Pregunta pregunta) {
    entityManager().persist(pregunta);
  }

  public List<Pregunta> listarPregruntasDisponibles() {
    return entityManager().createQuery("from preguntas ", Pregunta.class).getResultList();
  }
}
