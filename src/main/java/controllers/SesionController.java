package controllers;

import domain.*;
import domain.exception.PasswordDebilException;
import domain.repositorios.RepositorioDuenio;
import domain.repositorios.RepositorioUsuarios;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class SesionController implements WithGlobalEntityManager, TransactionalOps {
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

  public ModelAndView mostrarRegistroUsuario(Request request, Response response) {
    return new ModelAndView(null, "crear_usuario.html.hbs");
  }

  public ModelAndView registrarUsuario(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();
    RepositorioUsuarios repositorio = RepositorioUsuarios.getInstance();
    String password = request.queryParams("password");
    String nombreUsuario = request.queryParams("usuario");
    try {
      Usuario usuario = new Usuario(nombreUsuario, password, TipoUsuario.ESTANDAR);
      withTransaction(() -> repositorio.agregar(usuario));
      request.session().attribute("idUsuario", usuario.getId());
      modelo.put("sesionIniciada", request.session().attribute("idUsuario") != null);
      return new ModelAndView(modelo, "index.html.hbs");
    } catch (PasswordDebilException e) {
      modelo.put("passwordDebil", e.getMessage());
      return new ModelAndView(modelo, "crear_usuario.html.hbs");
    }
  }
}
