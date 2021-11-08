package controllers;

import domain.*;
import domain.repositorios.RepositorioDuenio;
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

  public Void registrarMascota(Request request, Response response) {
    //Obtengo el idUsuario y el tipo de usuario logueado
    String idUsuario = request.session().attribute("idUsuario");
    String tipoUsuario = request.session().attribute("tipoUsuario");
    //Si el tipo de usuario es "ESTANDAR" puede registrar su mascota
    if(tipoUsuario == TipoUsuario.ESTANDAR.toString()) {
      //Obtener el duenio del repositorio de duenios a partir de su usuario
      RepositorioDuenio repositorio = RepositorioDuenio.getInstance();
      Duenio duenio = repositorio.buscarDuenioAPartirDeIdUsuario(idUsuario);

      //Obtengo los datos completados en el form
      TipoMascota tipoMascota;
      if(request.queryParams("registro-especie") == "PERRO") {
        tipoMascota = TipoMascota.PERRO;
      } else {
        tipoMascota = TipoMascota.GATO;
      }
      String nombre = request.queryParams("registro-nombre");
      String apodo = request.queryParams("registro-apodo");
      Double edad = Double.parseDouble(request.queryParams("registro-edad"));
      Sexo sexo;
      if(request.queryParams("registro-sexo") == "MACHO") {
        sexo = Sexo.MACHO;
      } else {
        sexo = Sexo.HEMBRA;
      }
      String descripcionFisica = request.queryParams("registro-descripcion");
      String fotos = request.queryParams("registro-fotos");
      String caracteristicas = request.queryParams("registro-caracteristicas");
      //String situacionMascota = SituacionMascota.EN_HOGAR_PROPIO;

      //Creo la mascota
      Mascota mascota = new Mascota(tipoMascota, nombre, apodo, edad, sexo, descripcionFisica, fotos, caracteristicas, SituacionMascota.EN_HOGAR_PROPIO);
      //Agrego la mascota al duenio logueado
      duenio.addMascota(mascota);

      //Redireccionar a la misma página
      response.redirect("/registrarMascota");
    }
    return null;
  }

  public Void encontreMascota(Request request, Response response) {

    return null;
  }
}
