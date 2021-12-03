package controllers;

import static spark.Spark.halt;

import domain.TipoPregunta;
import domain.Usuario;
import domain.repositorios.RepositorioDuenio;
import domain.repositorios.RepositorioUsuarios;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

  public Map<String, Object> setMetadata(Request request) {
    Map<String, Object> modelo = new HashMap<>();

    modelo.put("usuarioCreadorCaracteristicas", this.usuarioCreadorCaracteristicas(request));
    modelo.put("sesionIniciada", this.sesionIniciada(request));
    modelo.put("esDuenio", this.esDuenio(request));

    List<TipoPregunta> tipoCaracteristicas = Arrays.asList(TipoPregunta.values());
    modelo.put("tipoCaracteristicas", tipoCaracteristicas);

    return modelo;
  }
}
