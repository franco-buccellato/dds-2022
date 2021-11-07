package controllers;

import domain.Usuario;
import domain.repositorios.RepositorioUsuarios;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class SesionController {
  // TODO GRAN TODO: notar que las responsabildades
  // de saber si una personas está con sesión inciada,
  // de saber le usuarie actual, etc, probablmente se vayan a repetir
  // y convendrá generalizarlas

  public ModelAndView mostrarLogin(Request request, Response response) {
    if (request.session().attribute("idUsuario") != null) {
      response.redirect("/");
      return null;
    }
    Map<String, Object> modelo = new HashMap<>();
    modelo.put("sesionIniciada", request.session().attribute("idUsuario") != null);
    return new ModelAndView(modelo, "inicioSesion.html.hbs");
  }

  public Void iniciarSesion(Request request, Response response) {
    RepositorioUsuarios repositorio = RepositorioUsuarios.getInstance();
    String password = request.queryParams("password");
    String nombreUsuario = request.queryParams("usuario");
    try {
      Usuario usuario = repositorio.verificarUsuarioClave(nombreUsuario, password);

      request.session().attribute("idUsuario", usuario.getId());
      request.session().attribute("tipoUsuario", usuario.getTipoUsuario().toString());

      response.redirect("/");
    } catch (NoSuchElementException e) {
      response.redirect("/login");
    }
    return null;
  }

  public Void cerrarSesion(Request request, Response response) {
    request.session().removeAttribute("idUsuario");
    request.session().removeAttribute("tipoUsuario");
    response.redirect("/");
    return null;
  }
}
