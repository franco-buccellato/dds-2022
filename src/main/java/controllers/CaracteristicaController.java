package controllers;

import static spark.Spark.halt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import spark.Request;
import spark.Response;
import spark.TemplateEngine;

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

    TipoPregunta tipoPregunta = TipoPregunta.valueOf(request.queryParams("tipoCaracteristica"));
    List<ObjetivoPregunta> objetivos = Arrays.asList(ObjetivoPregunta.CARACTERISTICA_MASCOTA);
    String descripcion = request.queryParams("descripcion");
    Boolean obligatoria = request.queryParams("obligatoria").equals("SI");

    List<Opcion> opciones = new ArrayList<>();
    List<String> paramsOpciones = request.queryParams()
        .stream()
        .filter(paramName -> paramName.contains("opcion"))
        .collect(Collectors.toList());
    for(String opcion : paramsOpciones) {
      opciones.add(new Opcion(request.queryParams(opcion)));
    }

    try {
      Pregunta pregunta = TipoPreguntaFactory.makePregunta(
          tipoPregunta, objetivos, descripcion, obligatoria, opciones
      );

      withTransaction(() -> {
        repositorioPreguntas.agregar(pregunta);
      });

    } catch (TipoPreguntaInexistenteException | NullPointerException exception) {

      modelo.put("error", exception.getMessage());

    } finally {
      modelo.put("caracteristicasDisponibles", repositorioPreguntas.listar());
      return new ModelAndView(modelo, "listarCaracteristicas.html.hbs");
    }
  }

  public Object getDetalleCaracteristica(Request request, Response response, TemplateEngine engine) {
    Map<String, Object> modelo = this.setMetadata(request);
    String id = request.params(":id");
    try{
      Pregunta caracteristica = repositorioPreguntas.buscar(Long.valueOf(id));
      if(caracteristica != null) {
        modelo.put("caracteristica", caracteristica);
        return engine.render(new ModelAndView(modelo, "detalleCaracteristicas.html.hbs"));
      }
      return null;
    } catch(NumberFormatException | NullPointerException exception){
      return "Bad Request";
    }
  }

  public ModelAndView actualizarCaracteristica(Request request, Response response) {
    Map<String, Object> modelo = this.setMetadata(request);

    Long id = Long.valueOf(request.params(":id"));
    String descripcion = request.queryParams("descripcion");
    Boolean obligatoria = request.queryParams("obligatoria").equals("SI");

    List<Opcion> opciones = new ArrayList<>();
    List<String> paramsOpciones = request.queryParams()
        .stream()
        .filter(paramName -> paramName.contains("opcion"))
        .collect(Collectors.toList());
    for(String opcion : paramsOpciones) {
      opciones.add(new Opcion(request.queryParams(opcion)));
    }

    try {
      repositorioPreguntas.actualizarPregunta(id, descripcion, opciones, obligatoria);
    } catch (TipoPreguntaInexistenteException | NullPointerException exception) {
      modelo.put("error", exception.getMessage());
    } finally {
      modelo.put("caracteristicasDisponibles", repositorioPreguntas.listar());
      return new ModelAndView(modelo, "listarCaracteristicas.html.hbs");
    }
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
