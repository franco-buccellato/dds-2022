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

  public Void registrarMascota(Request request, Response response) throws IOException {
    //Obtengo el idUsuario y el tipo de usuario logueado
    Long idUsuario = Long.parseLong(request.session().attribute("idUsuario"));
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
    //TODO: Ver si se puede hacer de una mejor manera
    Sexo sexo;
    if (request.queryParams("registro-sexo") == "MACHO") {
      sexo = Sexo.MACHO;
    } else {
      sexo = Sexo.HEMBRA;
    }
    String descripcionFisica = request.queryParams("registro-descripcion");
    String fotos = request.queryParams("registro-fotos");
    String caracteristicas = request.queryParams("registro-caracteristicas");

    //TODO: Ver lo de ArrayList
    List listaFotos = new ArrayList();
    listaFotos.add(fotos);
    //TODO: Ver lo de ArrayList
    List listaCaracteristicas = new ArrayList();
    listaCaracteristicas.add(caracteristicas);

    //Creo la mascota
    Mascota mascota = new Mascota(tipoMascota, nombreMascota, apodo, edad, sexo, descripcionFisica, listaFotos, listaCaracteristicas, SituacionMascota.EN_HOGAR_PROPIO);

    //Obtengo el Usuario
    RepositorioUsuarios repositorioUsuarios = RepositorioUsuarios.getInstance();
    Usuario usuario = repositorioUsuarios.getById(idUsuario);

    //Obtener el duenio del repositorio de duenios a partir de su usuario
    RepositorioDuenio repositorioDuenio = RepositorioDuenio.getInstance();
    //Verifico si el usuario ya era duenio
    if(repositorioDuenio.getDuenios().stream().filter(unDuenio -> unDuenio.getUsuario().getId() == idUsuario).findFirst().get() == null){
      //Si no era duenio tengo que cargar sus datos personales completos
      //Obtengo los datos personales del duenio que va a registrar la mascota
      String nombreDuenio = request.queryParams("registro-nombreDuenio");
      String apellidoDuenio = request.queryParams("registro-apellido");
      //TODO: Ver si se puede hacer de una mejor manera
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
      //TODO: Ver si se puede hacer de una mejor manera
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
      //TODO: Ver si se puede hacer de una mejor manera
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
    Duenio duenio = repositorioDuenio.getDuenios().stream().filter(unDuenio -> unDuenio.getUsuario().getId() == idUsuario).findFirst().get();
    //Agrego la mascota al duenio
    duenio.addMascota(mascota);

    //Redireccionar a la misma página
    response.redirect("/registrarMascota");
    return null;
  }

  public Void encontreMascota(Request request, Response response) {

    return null;
  }
  
  public ModelAndView formularioRegistrarMascota(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();
    modelo.put("sesionIniciada", request.session().attribute("idUsuario") != null);
    return new ModelAndView(modelo, "registroMascota.html.hbs");
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
