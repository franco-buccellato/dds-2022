package controllers;

import domain.*;
import domain.repositorios.RepositorioDuenio;
import domain.repositorios.RepositorioUsuarios;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

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

  public Void registrarMascota(Request request, Response response) throws IOException {
    //Obtengo el idUsuario y el tipo de usuario logueado
    String idUsuario = request.session().attribute("idUsuario");
    String tipoUsuario = request.session().attribute("tipoUsuario");

    //Obtengo los datos completados en el form para la mascota
    TipoMascota tipoMascota;
    if (request.queryParams("registro-especie") == "PERRO") {
      tipoMascota = TipoMascota.PERRO;
    } else {
      tipoMascota = TipoMascota.GATO;
    }
    String nombreMascota = request.queryParams("registro-nombre");
    String apodo = request.queryParams("registro-apodo");
    Double edad = Double.parseDouble(request.queryParams("registro-edad"));
    Sexo sexo;
    if (request.queryParams("registro-sexo") == "MACHO") {
      sexo = Sexo.MACHO;
    } else {
      sexo = Sexo.HEMBRA;
    }
    String descripcionFisica = request.queryParams("registro-descripcion");
    String fotos = request.queryParams("registro-fotos");
    String caracteristicas = request.queryParams("registro-caracteristicas");
    //String situacionMascota = SituacionMascota.EN_HOGAR_PROPIO;

    List listaFotos = new ArrayList();
    listaFotos.add(fotos);

    List listaCaracteristicas = new ArrayList();
    listaCaracteristicas.add(caracteristicas);

    //Creo la mascota
    Mascota mascota = new Mascota(tipoMascota, nombreMascota, apodo, edad, sexo, descripcionFisica, listaFotos, listaCaracteristicas, SituacionMascota.EN_HOGAR_PROPIO);

    //Obtengo el Usuario
    RepositorioUsuarios repositorioUsuarios = RepositorioUsuarios.getInstance();
    Usuario usuario = repositorioUsuarios.getById(Integer.parseInt(idUsuario));

    //Obtener el duenio del repositorio de duenios a partir de su usuario
    RepositorioDuenio repositorioDuenio = RepositorioDuenio.getInstance();
    if (repositorioDuenio.getDuenios().stream().filter(unDuenio -> unDuenio.getUsuario().getId().toString() == idUsuario).findFirst().get() == null) {
      //Obtengo los datos personales del duenio que va a registrar la mascota
      String nombreDuenio = request.queryParams("registro-nombreDuenio");
      String apellidoDuenio = request.queryParams("registro-apellido");
      TipoIdentificacion tipoIdentificacion;
      if (request.queryParams("registro-tipoDocumento") == "DNI") {
        tipoIdentificacion = TipoIdentificacion.DNI;
      } else if (request.queryParams("registro-tipoDocumento") == "LE") {
        tipoIdentificacion = TipoIdentificacion.LE;
      } else {
        tipoIdentificacion = TipoIdentificacion.CEDULA;
      }
      String numeroDocumento = request.queryParams("registro-documento");
      LocalDate fechaNacimiento = LocalDate.parse(request.queryParams("registro-fechaNacimiento"));

      DatoPersonal datosDuenio = new DatoPersonal(nombreDuenio, apellidoDuenio, tipoIdentificacion, numeroDocumento, fechaNacimiento);

      //Obtengo los datos del Contacto 1
      String nombreContacto1 = request.queryParams("registro-nombreContacto1");
      String apellidoContacto1 = request.queryParams("registro-apellidoContacto1");
      String telefonoContacto1 = request.queryParams("registro-telefonoContacto1");
      String mailContacto1 = request.queryParams("regsitro.mailContacto1");
      Vinculo vinculoContacto1;
      if (request.queryParams("registro-vinculoContacto1") == "TITULAR") {
        vinculoContacto1 = Vinculo.TITULAR;
      } else if (request.queryParams("registro-vinculoContacto1") == "AMISTAD") {
        vinculoContacto1 = Vinculo.AMISTAD;
      } else {
        vinculoContacto1 = Vinculo.FAMILIAR;
      }
      Contacto contacto1 = new Contacto(nombreContacto1, apellidoContacto1, telefonoContacto1, mailContacto1, vinculoContacto1, new MedioNotificacionEmail());

      //Obtengo los datos del Contacto 2
      String nombreContacto2 = request.queryParams("registro-nombreContacto1");
      String apellidoContacto2 = request.queryParams("registro-apellidoContacto1");
      String telefonoContacto2 = request.queryParams("registro-telefonoContacto1");
      String mailContacto2 = request.queryParams("regsitro.mailContacto1");
      Vinculo vinculoContacto2;
      if (request.queryParams("registro-vinculoContacto2") == "TITULAR") {
        vinculoContacto2 = Vinculo.TITULAR;
      } else if (request.queryParams("registro-vinculoContacto2") == "AMISTAD") {
        vinculoContacto2 = Vinculo.AMISTAD;
      } else {
        vinculoContacto2 = Vinculo.FAMILIAR;
      }
      Contacto contacto2 = new Contacto(nombreContacto2, apellidoContacto2, telefonoContacto2, mailContacto2, vinculoContacto2, new MedioNotificacionEmail());

      List contactos = new ArrayList();
      contactos.add(contacto1);
      contactos.add(contacto2);

      List mascotas = new ArrayList();
      mascotas.add(mascota);
      //Creo al duenio
      Duenio nuevoDuenio = new Duenio(datosDuenio, contactos, mascotas, usuario);
      repositorioDuenio.addDuenio(nuevoDuenio);
    }

    //Obtengo el duenio del repositorio
    Duenio duenio = repositorioDuenio.getDuenios().stream().filter(unDuenio -> unDuenio.getUsuario().getId().toString() == idUsuario).findFirst().get();
    //Agrego la mascota al duenio
    duenio.addMascota(mascota);

    //Redireccionar a la misma página
    response.redirect("/registrarMascota");
    return null;
  }

  public Void encontreMascota(Request request, Response response) {

    return null;
  }
}
