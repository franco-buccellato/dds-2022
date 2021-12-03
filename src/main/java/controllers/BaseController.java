package controllers;

import domain.Usuario;
import domain.repositorios.RepositorioDuenio;
import domain.repositorios.RepositorioUsuarios;
import java.util.List;
import java.util.Objects;
import servicio.Funcion;
import spark.Request;

public class BaseController {

  public boolean sesionIniciada(Request request) {
    return request.session().attribute("idUsuario") != null;
  }

  public boolean esDuenio(Request request) {
    RepositorioDuenio repositorioDuenio = RepositorioDuenio.getInstance();

    return repositorioDuenio.getDuenioByIdUsuario(request.session().attribute("idUsuario")) != null;
  }

  public boolean usuarioCreadorCaracteristicas(Request request) {
    RepositorioUsuarios repositorioUsuarios = RepositorioUsuarios.getInstance();

    if (!this.sesionIniciada(request)) {
      return false;
    }

    Usuario usuario = repositorioUsuarios.getById(request.session().attribute("idUsuario"));

    List<Funcion> funciones = usuario.getTipoUsuario().getFuncionesDisponibles();

    return !Objects.isNull(funciones) && funciones.stream()
        .anyMatch(funcion -> funcion.equals(Funcion.AGREGAR_CARACTERISTICAS));
  }
}
