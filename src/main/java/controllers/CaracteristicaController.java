package controllers;

import static spark.Spark.halt;

import domain.ObjetivoPregunta;
import domain.Opcion;
import domain.Pregunta;
import domain.TipoPregunta;
import domain.TipoPreguntaFactory;
import domain.exception.TipoPreguntaInexistenteException;
import domain.repositorios.RepositorioPreguntas;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CaracteristicaController extends BaseController implements WithGlobalEntityManager, TransactionalOps {
  RepositorioPreguntas repositorioPreguntas = RepositorioPreguntas.getInstance();

  public ModelAndView getCaracteristicas(Request request, Response response) {
    Map<String, Object> modelo = this.setMetadata(request);

    modelo.put("caracteristicasDisponibles", repositorioPreguntas.listar());

    return new ModelAndView(modelo, "listarCaracteristicas.html.hbs");
  }

  public ModelAndView mostrarCrearCaracteristica(Request request, Response response) {
    Map<String, Object> modelo = this.setMetadata(request);
    modelo.put("caracteristicasDisponibles", repositorioPreguntas.listar());

    return new ModelAndView(modelo, "crearCaracteristica.html.hbs");
  }

  public ModelAndView crearCaracteristica(Request request, Response response) {
    Map<String, Object> modelo = this.setMetadata(request);

    TipoPregunta tipoPregunta;
    try {
      tipoPregunta = TipoPregunta.valueOf(request.queryParams("tipoCaracteristica"));

    } catch (IllegalArgumentException exception) {
      modelo.put("error", "El tipo de Pregunta seleccionado no existe");
      response.status(422);

      return new ModelAndView(modelo, "crearCaracteristica.html.hbs");
    }

    List<ObjetivoPregunta> objetivos = Arrays.asList(ObjetivoPregunta.CARACTERISTICA_MASCOTA);
    String descripcion = request.queryParams("descripcion");
    Boolean obligatoria = request.queryParams("obligatoria").equals("SI");

    List<Opcion> opciones = new ArrayList<>();
    List<String> paramsOpciones = request.queryParams()
        .stream()
        .filter(paramName -> paramName.contains("opcion"))
        .collect(Collectors.toList());
    for (String opcion : paramsOpciones) {
      String descripcionOpcion = request.queryParams(opcion);

      if (descripcionOpcion != null) {
        opciones.add(new Opcion(descripcionOpcion));
      }
    }

    try {
      Pregunta pregunta = TipoPreguntaFactory.makePregunta(
          tipoPregunta, objetivos, descripcion, obligatoria, opciones
      );

      withTransaction(() -> repositorioPreguntas.agregar(pregunta));

      modelo.put("success", "Creaste una nueva caracteristica!");
      modelo.put("caracteristicasDisponibles", repositorioPreguntas.listar());
      response.status(201);

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

    try {
      Pregunta caracteristica = repositorioPreguntas.buscar(Long.valueOf(id));

      if (caracteristica != null) {
        modelo.put("caracteristica", caracteristica);

        return new ModelAndView(modelo, "detalleCaracteristicas.html.hbs");
      }

      modelo.put("error", "Caracteristica no encontrada");
      response.status(404);

    } catch (NumberFormatException | NullPointerException exception) {
      modelo.put("error", "Request erronea");
      response.status(400);

    } finally {
      modelo.put("caracteristicasDisponibles", repositorioPreguntas.listar());
    }

    return new ModelAndView(modelo, "listarCaracteristicas.html.hbs");
  }

  public ModelAndView actualizarCaracteristica(Request request, Response response) {
    Map<String, Object> modelo = this.setMetadata(request);

    Long id;
    try {
      id = Long.parseLong(request.params(":id"));
    } catch (NumberFormatException | NullPointerException exception) {
      modelo.put("error", "Request erronea");
      modelo.put("caracteristicasDisponibles", repositorioPreguntas.listar());
      response.status(400);

      return new ModelAndView(modelo, "listarCaracteristicas.html.hbs");
    }

    String descripcion = request.queryParams("descripcion");
    Boolean obligatoria = request.queryParams("obligatoria").equals("SI");

    List<Opcion> opciones = new ArrayList<>();
    List<String> paramsOpciones = request.queryParams()
        .stream()
        .filter(paramName -> paramName.contains("opcion"))
        .collect(Collectors.toList());
    for (String opcion : paramsOpciones) {
      String descripcionOpcion = request.queryParams(opcion);

      if (descripcionOpcion != null) {
        opciones.add(new Opcion(descripcionOpcion));
      }
    }

    Pregunta pregunta = repositorioPreguntas.buscar(id);

    if (pregunta == null) {
      modelo.put("error", "Caracteristica no encontrada");
      modelo.put("caracteristicasDisponibles", repositorioPreguntas.listar());
      response.status(404);

      return new ModelAndView(modelo, "listarCaracteristicas.html.hbs");
    }

    try {
      pregunta.setDescripcion(descripcion);
      pregunta.setObligatoria(obligatoria);
      pregunta.setOpciones(opciones);

      withTransaction(() -> repositorioPreguntas.agregar(pregunta));

      modelo.put("success", "Actualizaste la caracteristica " + pregunta.getDescripcion() + "!");
      modelo.put("caracteristicasDisponibles", repositorioPreguntas.listar());
      response.status(201);

    } catch (NullPointerException exception) {
      modelo.put("caracteristica", pregunta);
      modelo.put("error", exception.getMessage());
      response.status(422);

      return new ModelAndView(modelo, "detalleCaracteristicas.html.hbs");
    }

    return new ModelAndView(modelo, "listarCaracteristicas.html.hbs");
  }

  public Map<String, Object> setMetadata(Request request) {
    Map<String, Object> modelo = new HashMap<>();

    boolean usuarioCreadorCaracteristicas = this.usuarioCreadorCaracteristicas(request);
    if (!usuarioCreadorCaracteristicas) {
      halt(401, "Acceso Denegeado");
    }
    modelo.put("usuarioCreadorCaracteristicas", true);

    boolean sesionIniciada = this.sesionIniciada(request);
    modelo.put("sesionIniciada", sesionIniciada);

    List<TipoPregunta> tipoCaracteristicas = Arrays.asList(TipoPregunta.values());
    modelo.put("tipoCaracteristicas", tipoCaracteristicas);

    return modelo;
  }
}
