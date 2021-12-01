package controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import domain.Contacto;
import domain.DatoPersonal;
import domain.Duenio;
import domain.Mascota;
import domain.MedioNotificacionEmail;
import domain.Opcion;
import domain.Pregunta;
import domain.RespuestaCaracteristicaMascota;
import domain.Sexo;
import domain.SituacionMascota;
import domain.TipoIdentificacion;
import domain.TipoMascota;
import domain.TipoPregunta;
import domain.Usuario;
import domain.Vinculo;
import domain.repositorios.RepositorioDuenio;
import domain.repositorios.RepositorioOpciones;
import domain.repositorios.RepositorioPreguntas;
import domain.repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MascotaController extends BaseController implements WithGlobalEntityManager, TransactionalOps {

  public Void registrarMascota(Request request, Response response) throws IOException {
    RepositorioDuenio repositorioDuenio = RepositorioDuenio.getInstance();

    Usuario usuario = RepositorioUsuarios.getInstance()
        .getById(request.session().attribute("idUsuario"));

    Duenio duenio = repositorioDuenio.getDuenioByIdUsuario(usuario.getId());

    if (duenio == null) {
      String nombreDuenio = request.queryParams("registro-nombreDuenio");
      String apellidoDuenio = request.queryParams("registro-apellido");
      TipoIdentificacion tipoIdentificacion = TipoIdentificacion.valueOf(
          request.queryParams("registro-tipoDocumento")
      );
      String numeroDocumento = request.queryParams("registro-documento");
      LocalDate fechaNacimiento = LocalDate.parse(request.queryParams("registro-fechaNacimiento"));
      DatoPersonal datosDuenio = new DatoPersonal(
          nombreDuenio,
          apellidoDuenio,
          tipoIdentificacion,
          numeroDocumento,
          fechaNacimiento
      );

      List<Contacto> contactos = new ArrayList<>();
      List<String> contactoParams = request.queryParams()
          .stream()
          .filter(paramName -> paramName.contains("contacto"))
          .collect(Collectors.toList());

      String nombreContacto1 = request.queryParams("registro-nombreContacto1");
      String apellidoContacto1 = request.queryParams("registro-apellidoContacto1");
      String telefonoContacto1 = request.queryParams("registro-telefonoContacto1");
      String mailContacto1 = request.queryParams("registro-mailContacto1");
      Vinculo vinculoContacto1 = Vinculo.valueOf(request.queryParams("registro-vinculoContacto1"));
      Contacto contacto1 = new Contacto(
          nombreContacto1,
          apellidoContacto1,
          telefonoContacto1,
          mailContacto1,
          vinculoContacto1,
          new MedioNotificacionEmail()
      );

      String nombreContacto2 = request.queryParams("registro-nombreContacto2");
      String apellidoContacto2 = request.queryParams("registro-apellidoContacto2");
      String telefonoContacto2 = request.queryParams("registro-telefonoContacto2");
      String mailContacto2 = request.queryParams("registro-mailContacto2");
      Vinculo vinculoContacto2 = Vinculo.valueOf(request.queryParams("registro-vinculoContacto2"));
      Contacto contacto2 = new Contacto(
          nombreContacto2,
          apellidoContacto2,
          telefonoContacto2,
          mailContacto2,
          vinculoContacto2,
          new MedioNotificacionEmail()
      );

      duenio = new Duenio(datosDuenio, Arrays.asList(contacto1, contacto2), null, usuario);
    }

    List<String> paramsOpciones = request.queryParams()
        .stream()
        .filter(paramName -> paramName.contains("respuesta"))
        .collect(Collectors.toList());

    HashMap<Long, List<String>> respuestas = new HashMap<>();

    for (String opcion : paramsOpciones) {
      String[] spliteado = opcion.split("-");
      Long preguntaId = Long.valueOf(spliteado[1]);

      if (spliteado.length == 3) {
        List<String> respuesta = respuestas.get(preguntaId);
        if (respuesta == null) {
          respuesta = new ArrayList<>();
        }
        respuesta.add(spliteado[2]);
        respuestas.put(preguntaId, respuesta);

        continue;
      }

      respuestas.put(preguntaId, Collections.singletonList(request.queryParams(opcion)));
    }

    List<RespuestaCaracteristicaMascota> respuestasCaracteristicas = new ArrayList<>();

    respuestas.forEach((idPregunta, respuesta) -> {
      Pregunta pregunta = RepositorioPreguntas.getInstance().buscar(idPregunta);
      List<Opcion> opciones = new ArrayList<>();

      respuesta.forEach(res -> {
        Opcion opcion;
        try {
          opcion = RepositorioOpciones.getInstance().buscar(Long.valueOf(res));

          if (opcion == null) {
            opcion = new Opcion(res);
          }

        } catch (NumberFormatException e) {
          opcion = new Opcion(res);
        }

        opciones.add(opcion);
      });

      RespuestaCaracteristicaMascota respuestaCaracteristicaMascota = new RespuestaCaracteristicaMascota(
          pregunta,
          opciones
      );
      respuestasCaracteristicas.add(respuestaCaracteristicaMascota);
    });

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
        respuestasCaracteristicas,
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
}
