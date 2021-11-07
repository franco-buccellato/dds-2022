package domain.repositorios;

import domain.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class RepositorioUsuarios implements WithGlobalEntityManager {
  List<Usuario> usuarios = new ArrayList<>();

  private static RepositorioUsuarios instance = new RepositorioUsuarios();

  public static RepositorioUsuarios getInstance() {
    if (instance == null) {
      instance = new RepositorioUsuarios();
    }

    return instance;
  }

  private RepositorioUsuarios() {
  }

  public List<Usuario> listar() {
    return entityManager()
        .createQuery("from Usuario", Usuario.class)
        .getResultList();
  }

  public Usuario getById(Integer id) {
    return entityManager().find(Usuario.class, id);
  }

  public void agregar(Usuario usuario) {
    entityManager().persist(usuario);
  }

  public Usuario verificarUsuarioClave(String nombreUsuario, String password) {
    return this.listar().stream()
        .filter(u -> u.getContrasenia().equals(password) && u.getNombreUsuario().equals(nombreUsuario))
        .findFirst().get();
  }

  public void limpiarRepositorio() {
    this.usuarios.clear();
  }
}
