package controllers;

import static domain.ObjetivoPregunta.CARACTERISTICA_MASCOTA;
import static domain.ObjetivoPregunta.PREGUNTA_ASOCIACION_COMODIDAD;
import static spark.Spark.halt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import domain.ObjetivoPregunta;
import domain.Opcion;
import domain.Pregunta;
import domain.TipoPregunta;
import domain.TipoPreguntaFactory;
import domain.exception.TipoPreguntaInexistenteException;
import domain.repositorios.RepositorioPreguntas;
import spark.ModelAndView;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;

public class CaracteristicaController extends BaseController implements WithGlobalEntityManager, TransactionalOps {
  RepositorioPreguntas repositorioPreguntas = RepositorioPreguntas.getInstance();

  public ModelAndView getCaracteristicas(Request request, Response response) {
    Map<String, Object> modelo = this.setMetadata(request);

    modelo.put("disponibles", getDisponibles());
    return new ModelAndView(modelo, "listarCaracteristicas.html.hbs");
  }

  public ModelAndView mostrarCrearCaracteristica(Request request, Response response) {
    Map<String, Object> modelo = this.setMetadata(request);

    modelo.put("alcance", request.queryParams("alcance"));
    return new ModelAndView(modelo, "crearCaracteristica.html.hbs");
  }

  public ModelAndView crearCaracteristica(Request request, Response response) {
    Map<String, Object> modelo = this.setMetadata(request);

    TipoPregunta tipoPregunta = TipoPregunta.valueOf(request.queryParams("tipoCaracteristica"));
    ObjetivoPregunta objetivo =
        request.queryParams("alcance").equals("Caracteristica")
            ? CARACTERISTICA_MASCOTA
            : PREGUNTA_ASOCIACION_COMODIDAD;
    List<Opcion> opciones = new ArrayList<>();
    QueryParamsMap paramsOpciones = request.queryMap().get("opcion");
    if (paramsOpciones.hasValue()) {
      Arrays.stream(paramsOpciones.values())
          .forEach(value -> opciones.add(new Opcion(value)));
    }

    try {
      Pregunta pregunta = TipoPreguntaFactory.makePregunta(
          tipoPregunta,
          Arrays.asList(objetivo),
          request.queryParams("descripcion"),
          request.queryParams("obligatoria").equals("SI"),
          opciones
      );
      withTransaction(() -> repositorioPreguntas.agregar(pregunta));
      response.status(201);
      modelo.put("disponibles", getDisponibles());
//      response.redirect("/caracteristicas");
    } catch (TipoPreguntaInexistenteException | NullPointerException exception) {
      modelo.put("error", exception.getMessage());
      response.status(422);
      return new ModelAndView(modelo, "crearCaracteristica.html.hbs");
    }
    return new ModelAndView(modelo, "listarCaracteristicas.html.hbs");
  }

  public ModelAndView getDetalleCaracteristica(Request request, Response response) {
    Map<String, Object> modelo = this.setMetadata(request);
    String id = request.params(":id");
    String objetivo = request.params(":objetivo");

    try {
      Pregunta caracteristica = repositorioPreguntas.buscar(Long.valueOf(id));

      if (caracteristica != null) {
        modelo.put("caracteristica", caracteristica);
        modelo.put("objetivo", objetivo);
        return new ModelAndView(modelo, "detalleCaracteristicas.html.hbs");
      }
      modelo.put("error", objetivo + " no encontrada");
      response.status(404);

    } catch (NumberFormatException | NullPointerException exception) {
      modelo.put("error", "Request erronea");
      response.status(400);

    }
    modelo.put("disponibles", getDisponibles());
    return new ModelAndView(modelo, "listarCaracteristicas.html.hbs");
  }

  public ModelAndView actualizarCaracteristica(Request request, Response response) {
    Map<String, Object> modelo = this.setMetadata(request);

    Long id = Long.parseLong(request.params(":id"));
    Pregunta pregunta = repositorioPreguntas.buscar(id);
    if (pregunta == null) {
      modelo.put("error", "Caracteristica no encontrada");
      modelo.put("disponibles", getDisponibles());
      response.status(404);
      response.redirect("/caracteristicas");
    }

    String descripcion = request.queryParams("descripcion");
    Boolean obligatoria = request.queryParams("obligatoria").equals("SI");
    List<Opcion> opciones = new ArrayList<>();
    QueryParamsMap paramsOpciones = request.queryMap().get("opcion");
    if (paramsOpciones.hasValue()) {
      Arrays.stream(paramsOpciones.values())
          .forEach(value -> opciones.add(new Opcion(value)));
    }

    try {
      pregunta.setDescripcion(descripcion);
      pregunta.setObligatoria(obligatoria);
      pregunta.setOpciones(opciones);

      withTransaction(() -> repositorioPreguntas.agregar(pregunta));

      modelo.put("success", "Actualizaste la caracteristica " + pregunta.getDescripcion() + "!");
      response.status(201);
      response.redirect("/caracteristicas");
    } catch (NullPointerException exception) {
      modelo.put("caracteristica", pregunta);
      modelo.put("error", exception.getMessage());
      response.status(422);

      return new ModelAndView(modelo, "detalleCaracteristicas.html.hbs");
    }

    modelo.put("disponibles", getDisponibles());
    return new ModelAndView(modelo, "listarCaracteristicas.html.hbs");
  }


  private Map<String, List<Pregunta>> getDisponibles() {
    return new HashMap<String, List<Pregunta>>() {{
      put("Caracteristica", repositorioPreguntas.listarSegunObjetivo(CARACTERISTICA_MASCOTA));
      put("Pregunta", repositorioPreguntas.listarSegunObjetivo(PREGUNTA_ASOCIACION_COMODIDAD));
    }};
  }

  @Override
  public Map<String, Object> setMetadata(Request request) {
    Map<String, Object> modelo = super.setMetadata(request);
    if (modelo.get("usuarioCreadorCaracteristicas").equals(false)) {
      halt(401, "Acceso Denegado");
    }

    return modelo;
  }
}
