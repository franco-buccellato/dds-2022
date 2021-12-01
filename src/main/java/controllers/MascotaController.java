package controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import domain.Mascota;
import domain.Pregunta;
import domain.Sexo;
import domain.SituacionMascota;
import domain.TipoMascota;
import domain.TipoPregunta;
import domain.Usuario;
import domain.repositorios.RepositorioDuenio;
import domain.repositorios.RepositorioPreguntas;
import domain.repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;

public class MascotaController extends BaseController implements WithGlobalEntityManager, TransactionalOps {

  public Void registrarMascota(Request request, Response response) throws IOException {
    RepositorioDuenio repositorioDuenio = RepositorioDuenio.getInstance();

    Usuario usuario = RepositorioUsuarios.getInstance()
        .getById(request.session().attribute("idUsuario"));
//
//    Duenio duenio = repositorioDuenio.getDuenioByIdUsuario(usuario.getId());
//
//    if (duenio == null) {
//      String nombreDuenio = request.queryParams("registro-nombreDuenio");
//      String apellidoDuenio = request.queryParams("registro-apellido");
//      TipoIdentificacion tipoIdentificacion = TipoIdentificacion.valueOf(
//          request.queryParams("registro-tipoDocumento")
//      );
//      String numeroDocumento = request.queryParams("registro-documento");
//      LocalDate fechaNacimiento = LocalDate.parse(request.queryParams("registro-fechaNacimiento"));
//      DatoPersonal datosDuenio = new DatoPersonal(
//          nombreDuenio,
//          apellidoDuenio,
//          tipoIdentificacion,
//          numeroDocumento,
//          fechaNacimiento
//      );
//
//      List<Contacto> contactos = new ArrayList<>();
//      List<String> contactoParams = request.queryParams()
//          .stream()
//          .filter(paramName -> paramName.contains("contacto"))
//          .collect(Collectors.toList());
//
//      String nombreContacto1 = request.queryParams("registro-nombreContacto1");
//      String apellidoContacto1 = request.queryParams("registro-apellidoContacto1");
//      String telefonoContacto1 = request.queryParams("registro-telefonoContacto1");
//      String mailContacto1 = request.queryParams("registro-mailContacto1");
//      Vinculo vinculoContacto1 = Vinculo.valueOf(request.queryParams("registro-vinculoContacto1"));
//      Contacto contacto1 = new Contacto(
//          nombreContacto1,
//          apellidoContacto1,
//          telefonoContacto1,
//          mailContacto1,
//          vinculoContacto1,
//          new MedioNotificacionEmail()
//      );
//
//      String nombreContacto2 = request.queryParams("registro-nombreContacto2");
//      String apellidoContacto2 = request.queryParams("registro-apellidoContacto2");
//      String telefonoContacto2 = request.queryParams("registro-telefonoContacto2");
//      String mailContacto2 = request.queryParams("registro-mailContacto2");
//      Vinculo vinculoContacto2 = Vinculo.valueOf(request.queryParams("registro-vinculoContacto2"));
//      Contacto contacto2 = new Contacto(
//          nombreContacto2,
//          apellidoContacto2,
//          telefonoContacto2,
//          mailContacto2,
//          vinculoContacto2,
//          new MedioNotificacionEmail()
//      );
//
//      duenio = new Duenio(datosDuenio, Arrays.asList(contacto1, contacto2), null, usuario);
//    }
    // TODO: Ejemplo de como se consiguen los valores de las opciones
    Arrays.asList("texto", "bullet", "number", "checkbox").forEach(param -> this.getValoresPorNombre(request, param));

    TipoMascota tipoMascota = TipoMascota.valueOf(request.queryParams("registro-especie"));
    String nombreMascota = request.queryParams("registro-nombre");
    String apodo = request.queryParams("registro-apodo");
    Double edad = new Double(request.queryParams("registro-edad"));
    Sexo sexo = Sexo.valueOf(request.queryParams("registro-sexo"));
    String descripcionFisica = request.queryParams("registro-descripcion");
    List<String> fotos = Collections.singletonList(request.queryParams("registro-fotos"));
    Mascota mascota = new Mascota(
        tipoMascota,
        nombreMascota,
        apodo,
        edad,
        sexo,
        descripcionFisica,
        fotos,
        null,
        SituacionMascota.EN_HOGAR_PROPIO
    );

//    duenio.addMascota(mascota);

//    Duenio finalDuenio = duenio;

//    withTransaction(() -> repositorioDuenio.agregar(finalDuenio));

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

    RepositorioPreguntas repositorioPreguntas = RepositorioPreguntas.getInstance();

    List<Pregunta> textos = repositorioPreguntas.listarSegunTipo(TipoPregunta.TEXT);
    List<Pregunta> numeros = repositorioPreguntas.listarSegunTipo(TipoPregunta.NUMBER);
    List<Pregunta> bullets = repositorioPreguntas.listarSegunTipo(TipoPregunta.BULLET);
    List<Pregunta> checkboxs = repositorioPreguntas.listarSegunTipo(TipoPregunta.CHECKBOX);

    modelo.put("textos", textos);
    modelo.put("numeros", numeros);
    modelo.put("bullets", bullets);
    modelo.put("checkboxs", checkboxs);

    return new ModelAndView(modelo, "registroMascota.html.hbs");
  }

  public Void encontreMascota(Request request, Response response) {
    return null;
  }

  // TODO: Ejemplo de como se consiguen los valores de las opciones
  Arrays.asList("texto", "bullet", "number", "checkbox").forEach(param -> this.getValoresPorNombre(request, param));
  private void getValoresPorNombre(Request request, String name) {
    QueryParamsMap opciones = request.queryMap().get(name);
    opciones.toMap().keySet()
        .forEach(key -> {
          System.out.println("==============");
          System.out.println(name + " id: " + key);
          Arrays.stream(opciones.get(key).values())
              .forEach(value -> System.out.println("Value: " + value));
        });
  }
}
