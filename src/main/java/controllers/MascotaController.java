package controllers;

import static domain.ObjetivoPregunta.CARACTERISTICA_MASCOTA;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import domain.Contacto;
import domain.DatoPersonal;
import domain.Duenio;
import domain.Mascota;
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
import domain.exception.DuenioDiferenteAlDeMascotaException;
import domain.exception.MascotaNoEncontradaException;
import domain.repositorios.RepositorioDuenio;
import domain.repositorios.RepositorioMascotas;
import domain.repositorios.RepositorioOpciones;
import domain.repositorios.RepositorioPreguntas;
import domain.repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;

public class MascotaController extends BaseController implements WithGlobalEntityManager, TransactionalOps {

  public ModelAndView getMascotas(Request request, Response response) {
    Map<String, Object> modelo = this.setMetadata(request);

    List<Mascota> mascotas = RepositorioMascotas.getInstace().getByUsuario(
        request.session().attribute("idUsuario")
    );

    modelo.put("noTieneMascota", mascotas == null || mascotas.isEmpty());
    modelo.put("mascotas", mascotas);

    return new ModelAndView(modelo, "listarMascotas.html.hbs");
  }

  public ModelAndView getMascota(Request request, Response response) {
    Map<String, Object> modelo = this.setMetadata(request);

    try {
      Long id = Long.valueOf(request.params(":id"));

      Duenio duenio = RepositorioDuenio.getInstance().getDuenioByIdMascota(id);
      Mascota mascota = RepositorioMascotas.getInstace().getById(id);

      if (duenio == null || mascota == null) {
        response.status(404);
        throw new MascotaNoEncontradaException("Mascota no encontrada");
      }

      if (!duenio.esMismoUsuario(request.session().attribute("idUsuario"))) {
        response.status(401);
        throw new DuenioDiferenteAlDeMascotaException("No esta autorizado para ver a la mascota");
      }

      modelo.put("mascota", mascota);
      return new ModelAndView(modelo, "detalleMascota.html.hbs");

    } catch (NumberFormatException | NullPointerException exception) {
      response.status(400);
      modelo.put("error", "Solicitud erronea");

    } catch (DuenioDiferenteAlDeMascotaException | MascotaNoEncontradaException exception) {
      modelo.put("error", exception.getMessage());

    } finally {

      List<Mascota> mascotas = RepositorioMascotas.getInstace().getByUsuario(
          request.session().attribute("idUsuario")
      );

      modelo.put("noTieneMascota", mascotas == null || mascotas.isEmpty());
      modelo.put("mascotas", mascotas);
    }

    return new ModelAndView(modelo, "listarMascotas.html.hbs");
  }

  public ModelAndView registrarMascota(Request request, Response response) throws IOException {
    Map<String, Object> modelo = this.setMetadata(request);
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
      Contacto contacto1 = new Contacto(nombreContacto1,
                                        apellidoContacto1,
                                        telefonoContacto1,
                                        mailContacto1,
                                        vinculoContacto1);
      //Obtengo los datos del Contacto 2
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
          vinculoContacto2
      );

      duenio = new Duenio(datosDuenio, Arrays.asList(contacto1, contacto2), null, usuario);
    }

    List<RespuestaCaracteristicaMascota> respuestasCaracteristicas = new ArrayList<>();
    //      Busco por tipo
    Arrays.asList("texto", "bullet", "numero", "checkbox").forEach(nombre -> {
      QueryParamsMap paramsNombre = request.queryMap().get(nombre);
      if (paramsNombre.hasKeys()) {
        //      Si existe respuesta del tipo busco existencia de la pregunta
        paramsNombre.toMap().keySet()
            .forEach(key -> {
              Pregunta pregunta = RepositorioPreguntas.getInstance().buscar(Long.valueOf(key));
              List<Opcion> opciones = new ArrayList<>();
              //      Busco la existencia de opciones o las creo en el caso de las input
              Arrays.stream(paramsNombre.get(key).values())
                  .forEach(value -> opciones.add(getOpcionFromParam(nombre, value)));
              respuestasCaracteristicas.add(new RespuestaCaracteristicaMascota(pregunta, opciones));
            });
      }
    });

    if (!Objects.isNull(modelo.get("error"))) {
      return new ModelAndView(modelo, "registroMascota.html.hbs");
    }

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

    duenio.addMascota(mascota);

    Duenio finalDuenio = duenio;

    withTransaction(() -> repositorioDuenio.agregar(finalDuenio));

    //Redireccionar a la misma página
    // response.redirect("/registrarMascota");
    // return null;

    modelo.put("QR", String.valueOf(mascota.getId()));

    return new ModelAndView(modelo, "registroMascotaConfirmado.html.hbs");
  }

  public ModelAndView formularioRegistrarMascota(Request request, Response response) {
    Map<String, Object> modelo = this.setMetadata(request);

    RepositorioPreguntas repositorioPreguntas = RepositorioPreguntas.getInstance();

    List<Pregunta> textos = repositorioPreguntas.listarSegunTipo(
        TipoPregunta.TEXT, CARACTERISTICA_MASCOTA
    );
    List<Pregunta> numeros = repositorioPreguntas.listarSegunTipo(
        TipoPregunta.NUMBER, CARACTERISTICA_MASCOTA
    );
    List<Pregunta> bullets = repositorioPreguntas.listarSegunTipo(
        TipoPregunta.BULLET, CARACTERISTICA_MASCOTA
    );
    List<Pregunta> checkboxs = repositorioPreguntas.listarSegunTipo(
        TipoPregunta.CHECKBOX, CARACTERISTICA_MASCOTA
    );

    modelo.put("textos", textos);
    modelo.put("numeros", numeros);
    modelo.put("bullets", bullets);
    modelo.put("checkboxs", checkboxs);

    return new ModelAndView(modelo, "registroMascota.html.hbs");
  }

  public ModelAndView encontreMascota(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();
    boolean sesionIniciada = this.sesionIniciada(request);
    modelo.put("sesionIniciada", sesionIniciada);
    return new ModelAndView(modelo, "encontre_mascota.html.hbs");
  }

  private Opcion getOpcionFromParam(String param, String descripcion) {
    if (Arrays.asList("bullet", "checkbox").contains(param)) {
      return RepositorioOpciones.getInstance().buscar(Long.valueOf(descripcion));
    }
    return new Opcion(descripcion);
  }
}
