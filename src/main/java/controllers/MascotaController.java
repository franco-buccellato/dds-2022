package controllers;

import domain.*;
import domain.exception.PasswordDebilException;
import domain.repositorios.RepositorioDuenio;
import domain.repositorios.RepositorioUsuarios;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class MascotaController extends BaseController implements WithGlobalEntityManager, TransactionalOps {

  public Void registrarMascota(Request request, Response response) throws IOException {
    RepositorioUsuarios repositorioUsuarios = RepositorioUsuarios.getInstance();
    Long idUsuario = request.session().attribute("idUsuario");
    Usuario usuarioActual = repositorioUsuarios.getById(idUsuario);
    RepositorioDuenio repositorioDuenio = RepositorioDuenio.getInstance();
    Duenio nuevoDuenio;
    //Obtengo los datos completados en el form para la mascota
    TipoMascota tipoMascota = TipoMascota.valueOf(request.queryParams("registro-especie"));
    String nombreMascota = request.queryParams("registro-nombre");
    String apodo = request.queryParams("registro-apodo");
    Double edad = new Double(request.queryParams("registro-edad"));
    Sexo sexo = Sexo.valueOf(request.queryParams("registro-sexo"));
    String descripcionFisica = request.queryParams("registro-descripcion");
    String fotos = request.queryParams("registro-fotos");
    List<String> listaFotos = new ArrayList<>();
    listaFotos.add(fotos);
    String caracteristicas = request.queryParams("registro-caracteristicas");
    //TODO: Ver lo de ArrayList
    List<Caracteristica> listaCaracteristicas = new ArrayList<>();
    //listaCaracteristicas.add(caracteristicas);
    //Creo la mascota
    Mascota mascota = new Mascota(tipoMascota,
                                  nombreMascota,
                                  apodo,
                                  edad,
                                  sexo,
                                  descripcionFisica,
                                  listaFotos,
                                  listaCaracteristicas,
                                  SituacionMascota.EN_HOGAR_PROPIO);
    //Verifico si el usuario ya era duenio
    Duenio duenioExistente = repositorioDuenio.getDuenioByIdUsuario(idUsuario);
    if (duenioExistente == null) {
      //Obtengo los datos del Contacto 1
      String nombreContacto1 = request.queryParams("registro-nombreContacto1");
      String apellidoContacto1 = request.queryParams("registro-apellidoContacto1");
      String telefonoContacto1 = request.queryParams("registro-telefonoContacto1");
      String mailContacto1 = request.queryParams("registro-mailContacto1");
      Vinculo vinculoContacto1 = Vinculo.valueOf(request.queryParams("registro-vinculoContacto1"));
      Contacto contacto1 = new Contacto(nombreContacto1,
                                        apellidoContacto1,
                                        telefonoContacto1,
                                        mailContacto1,
                                        vinculoContacto1,
                                        new MedioNotificacionEmail());
      //Obtengo los datos del Contacto 2
      String nombreContacto2 = request.queryParams("registro-nombreContacto2");
      String apellidoContacto2 = request.queryParams("registro-apellidoContacto2");
      String telefonoContacto2 = request.queryParams("registro-telefonoContacto2");
      String mailContacto2 = request.queryParams("registro-mailContacto2");
      Vinculo vinculoContacto2 = Vinculo.valueOf(request.queryParams("registro-vinculoContacto2"));
      Contacto contacto2 = new Contacto(nombreContacto2,
                                        apellidoContacto2,
                                        telefonoContacto2,
                                        mailContacto2,
                                        vinculoContacto2,
                                        new MedioNotificacionEmail());
      //Armos listas de Contactos
      List<Contacto> contactos = new ArrayList<>();
      contactos.add(contacto1);
      contactos.add(contacto2);
      //Armo lista de mascotas
      List<Mascota> mascotas = new ArrayList<>();
      mascotas.add(mascota);
      //Creo al duenio
      //Si no era duenio tengo que cargar sus datos personales completos
      //Obtengo los datos personales del duenio que va a registrar la mascota
      String nombreDuenio = request.queryParams("registro-nombreDuenio");
      String apellidoDuenio = request.queryParams("registro-apellido");
      TipoIdentificacion tipoIdentificacion = TipoIdentificacion.valueOf(request.queryParams(
          "registro-tipoDocumento"));
      String numeroDocumento = request.queryParams("registro-documento");
      LocalDate fechaNacimiento = LocalDate.parse(request.queryParams("registro-fechaNacimiento"));
      DatoPersonal datosDuenio = new DatoPersonal(nombreDuenio,
                                                  apellidoDuenio,
                                                  tipoIdentificacion,
                                                  numeroDocumento,
                                                  fechaNacimiento);
      nuevoDuenio = new Duenio(datosDuenio, contactos, mascotas, usuarioActual);
      //Persistir nuevoDuenio
      withTransaction(() -> repositorioDuenio.agregar(nuevoDuenio));
    } else {
      //Agrego la mascota al duenio
      duenioExistente.addMascota(mascota);
      //Persistir duenio
      withTransaction(() -> repositorioDuenio.agregar(duenioExistente));
    }
    //Redireccionar a la misma página
    response.redirect("/registrarMascota");
    return null;
  }

  public ModelAndView formularioRegistrarMascota(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();
    boolean sesionIniciada = this.sesionIniciada(request);
    modelo.put("sesionIniciada", sesionIniciada);

    boolean esDuenio = this.esDuenio(request);
    modelo.put("esDuenio", esDuenio);

    boolean usuarioCreadorCaracteristicas = this.usuarioCreadorCaracteristicas(request);
    modelo.put("usuarioCreadorCaracteristicas", usuarioCreadorCaracteristicas);

    return new ModelAndView(modelo, "registroMascota.html.hbs");
  }

  public Void encontreMascota(Request request, Response response) {

    return null;
  }

}
