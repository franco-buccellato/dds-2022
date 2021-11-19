package domain.repositorios;

import domain.ObjetivoPregunta;
import domain.Pregunta;
import domain.TipoPregunta;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class RepositorioPreguntas implements WithGlobalEntityManager {
  private List<Pregunta> caracteristicasDisponibles = Collections.emptyList();
  private static final RepositorioPreguntas INSTANCE = new RepositorioPreguntas();

  public RepositorioPreguntas() {
  }

  public static RepositorioPreguntas getInstance() {
    return INSTANCE;
  }

  public RepositorioPreguntas(List<Pregunta> caracteristicas) {
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

  public List<Pregunta> listar() {
    return entityManager().createQuery("from preguntas", Pregunta.class).getResultList();
  }

  public List<Pregunta> listarSegunTipo(TipoPregunta tipoPregunta) {
    return entityManager().createQuery(
            "from preguntas p where p.tipoPregunta = :tipoPregunta",
            Pregunta.class
        ).setParameter("tipoPregunta", tipoPregunta)
        .getResultList();
  }

  public Pregunta buscar(Long id) {
    return entityManager().find(Pregunta.class, id);
  }
}
