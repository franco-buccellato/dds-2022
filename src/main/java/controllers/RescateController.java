package controllers;

import domain.*;
import domain.repositorios.RepositorioMascotas;
import domain.repositorios.RepositorioOpciones;
import domain.repositorios.RepositorioPreguntas;
import domain.repositorios.RepositorioUsuarios;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;

import javax.imageio.ImageIO;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static domain.ObjetivoPregunta.CARACTERISTICA_MASCOTA;


public class RescateController extends BaseController implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {
  public ModelAndView registroRescateConChapa(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();
    boolean sesionIniciada = this.sesionIniciada(request);
    modelo.put("sesionIniciada", sesionIniciada);
    modelo.put("tipos_documentos", TipoIdentificacion.values());
    return new ModelAndView(modelo, "mascota_con_chapa.html.hbs");
  }

  public Void guardarRescateConChapa(Request request, Response response) throws ServletException, IOException {
    Map<String, Object> modelo = new HashMap<>();
    boolean sesionIniciada = this.sesionIniciada(request);
    modelo.put("sesionIniciada", sesionIniciada);
    Long idUsuario = request.session().attribute("idUsuario");
    Usuario usuarioActual = RepositorioUsuarios.getInstance().getById(idUsuario);
    /*
    request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
    BufferedImage bufferedImage;
    try (InputStream input = request.raw().getPart("chapa").getInputStream()) {
      bufferedImage = ImageIO.read(input);
    }
    */
    Contacto contactoRescatista = new Contacto(
      request.queryParams("nombre"),
      request.queryParams("apellido"),
      request.queryParams("tel"),
      request.queryParams("email"),
      Vinculo.TITULAR
    );
    Ubicacion ubicacionRescatista = new Ubicacion(
        request.queryParams("location"),
        request.queryParams("postal_code"),
        request.queryParams("locality"),
        BigDecimal.valueOf(Double.parseDouble(request.queryParams("latitude"))),
        BigDecimal.valueOf(Double.parseDouble(request.queryParams("longitude")))
    );
    Ubicacion ubicacionRescate = new Ubicacion(
        request.queryParams("m_dir"),
        request.queryParams("m_cp"),
        request.queryParams("m_loc")
    );
    Rescatista rescatista = new Rescatista(
        new DatoPersonal(
            request.queryParams("nombre"),
            request.queryParams("apellido"),
            TipoIdentificacion.valueOf(request.queryParams("tip_doc")),
            request.queryParams("ndoc"),
            LocalDate.parse(request.queryParams("fnacimiento"))
        ),
        contactoRescatista,
        ubicacionRescatista,
        usuarioActual
    );
    RescateConChapa rescateConChapa = new RescateConChapa(
        Arrays.asList(request.queryParams("foto")),
        request.queryParams("desc"),
        ubicacionRescate,
        LocalDate.now(),
        RepositorioMascotas.getInstace().getById(Long.getLong(request.queryParams("chapa"))),
        rescatista
    );
    withTransaction(() -> {
        persist(ubicacionRescate);
        persist(ubicacionRescatista);
        persist(contactoRescatista);
        persist(rescatista);
        persist(rescateConChapa);
      });
    response.redirect("/encontreMascota");
    return null;
  }

  public ModelAndView registroRescateSinChapa(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();

    boolean sesionIniciada = this.sesionIniciada(request);

    RepositorioPreguntas repositorioPreguntas = RepositorioPreguntas.getInstance();

    List<Pregunta> textos = repositorioPreguntas.listarSegunTipo(
          TipoPregunta.TEXT, ObjetivoPregunta.PREGUNTA_ASOCIACION_COMODIDAD
    );
    List<Pregunta> numeros = repositorioPreguntas.listarSegunTipo(
        TipoPregunta.NUMBER, ObjetivoPregunta.PREGUNTA_ASOCIACION_COMODIDAD
    );
    List<Pregunta> bullets = repositorioPreguntas.listarSegunTipo(
        TipoPregunta.BULLET, ObjetivoPregunta.PREGUNTA_ASOCIACION_COMODIDAD
    );
    List<Pregunta> checkboxs = repositorioPreguntas.listarSegunTipo(
        TipoPregunta.CHECKBOX, ObjetivoPregunta.PREGUNTA_ASOCIACION_COMODIDAD
    );

    modelo.put("textos", textos);
    modelo.put("numeros", numeros);
    modelo.put("bullets", bullets);
    modelo.put("checkboxs", checkboxs);
    modelo.put("sesionIniciada", sesionIniciada);
    modelo.put("tipos_documentos", TipoIdentificacion.values());
    modelo.put("tipos_mascotas", TipoMascota.values());
    modelo.put("sexo", Sexo.values());

    return new ModelAndView(modelo, "mascota_sin_chapa.html.hbs");
  }

  public Void guardarRescateSinChapa(Request request, Response response) {
    Long idUsuario = request.session().attribute("idUsuario");
    Usuario usuarioActual = RepositorioUsuarios.getInstance().getById(idUsuario);

    Contacto contactoRescatista = new Contacto(
        request.queryParams("nombre"),
        request.queryParams("apellido"),
        request.queryParams("tel"),
        request.queryParams("email"),
        Vinculo.TITULAR
    );
    Ubicacion ubicacionRescatista = new Ubicacion(
        request.queryParams("r_dir"),
        request.queryParams("r_cp"),
        request.queryParams("r_loc"),
        BigDecimal.valueOf(Double.parseDouble(request.queryParams("latitude"))),
        BigDecimal.valueOf(Double.parseDouble(request.queryParams("longitude")))
    );
    Ubicacion ubicacionRescate = new Ubicacion(
        request.queryParams("m_dir"),
        request.queryParams("m_cp"),
        request.queryParams("m_loc"),
        BigDecimal.valueOf(Double.parseDouble(request.queryParams("m_latitude"))),
        BigDecimal.valueOf(Double.parseDouble(request.queryParams("m_longitude")))
    );
    Rescatista rescatista = new Rescatista(
        new DatoPersonal(
            request.queryParams("nombre"),
            request.queryParams("apellido"),
            TipoIdentificacion.valueOf(request.queryParams("tip_doc")),
            request.queryParams("ndoc"),
            LocalDate.parse(request.queryParams("fnacimiento"))
        ),
        contactoRescatista,
        ubicacionRescatista,
        usuarioActual
    );

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

    Mascota mascotaRescatada = new Mascota(
        TipoMascota.valueOf(request.queryParams("tipos_mascotas")),
        "",
        "",
        Double.valueOf(request.queryParams("edad")),
        Sexo.valueOf(request.queryParams("sexo")),
        request.queryParams("desc"),
        Arrays.asList(request.queryParams("foto_mascota")),
        respuestasCaracteristicas,
        SituacionMascota.EN_HOGAR_TRANSITORIO
    );
    RescateSinChapa rescateSinChapa = new RescateSinChapa(
        Arrays.asList(request.queryParams("foto_mascota")),
        request.queryParams("desc"),
        ubicacionRescate,
        LocalDate.now(),
        mascotaRescatada,
        rescatista
    );
    withTransaction(() -> {
      persist(ubicacionRescate);
      persist(ubicacionRescatista);
      persist(contactoRescatista);
      persist(rescatista);
      persist(mascotaRescatada);
      persist(rescateSinChapa);
    });

    //rescateSinChapa.buscarHogarDeTransito(50.0);

    response.redirect("/encontreMascota");
    return null;
  }

  private Opcion getOpcionFromParam(String param, String descripcion) {
    if (Arrays.asList("bullet", "checkbox").contains(param)) {
      return RepositorioOpciones.getInstance().buscar(Long.valueOf(descripcion));
    }
    return new Opcion(descripcion);
  }
}
