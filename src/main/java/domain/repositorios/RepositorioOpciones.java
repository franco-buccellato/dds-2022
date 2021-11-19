package domain.repositorios;

import domain.Opcion;
import java.util.Collections;
import java.util.List;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class RepositorioOpciones implements WithGlobalEntityManager {
  private List<Opcion> opciones = Collections.emptyList();
  private static final RepositorioOpciones INSTANCE = new RepositorioOpciones();

  public RepositorioOpciones() {
  }

  public static RepositorioOpciones getInstance() {
    return INSTANCE;
  }

  public Opcion buscar(Long id) {
    return entityManager().find(Opcion.class, id);
  }

}
