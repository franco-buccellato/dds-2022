package controllers;

import static spark.Spark.halt;

import domain.*;
import domain.exception.TipoPreguntaInexistenteException;
import domain.repositorios.RepositorioCaracteristicas;

import java.util.*;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CaracteristicaController extends BaseController implements WithGlobalEntityManager, TransactionalOps {

  // public ModelAndView getCaracteristicas(Request request, Response response) {
  //   Map<String, Object> modelo = this.setMetadata(request);

  //   return new ModelAndView(modelo, "listarCaracteristicas.html.hbs");
  // }

  public ModelAndView mostrarCrearCaracteristica(Request request, Response response) {
    Map<String, Object> modelo = this.setMetadata(request);

    return new ModelAndView(modelo, "crearCaracteristica.html.hbs");
  }

  public ModelAndView crearCaracteristica(Request request, Response response) {
    Map<String, Object> modelo = this.setMetadata(request);

    TipoPregunta tipoPregunta = TipoPregunta.valueOf(request.queryParams("tipoCaracteristica"));
    List<ObjetivoPregunta> objetivos = Arrays.asList(ObjetivoPregunta.CARACTERISTICA_MASCOTA);
    String descripcion = request.queryParams("descripcion");
    Boolean obligatoria = request.queryParams("obligatoria").equals("SI");
    RepositorioCaracteristicas repositorioCaracteristicas = RepositorioCaracteristicas.getInstance();

    String test = request.queryParams("opcion-1");
    System.out.println("TEST: " + test);

    String test2 = request.queryParams("opcion-inexistente");
    System.out.println("TEST 2: " + test2);
    System.out.println("IS NULL: " + Objects.isNull(test2));

    int i = 1;
    String descripcionOpcion;
    List<Opcion> opciones = new ArrayList<>();

    while (!Objects.isNull(descripcionOpcion = request.queryParams("opcion-" + i))) {
      System.out.println(descripcionOpcion);
      opciones.add(new Opcion(descripcionOpcion));
      i++;
    }

    try {
      Pregunta pregunta = TipoPreguntaFactory.makePregunta(
          tipoPregunta, objetivos, descripcion, obligatoria, opciones
      );

      withTransaction(() -> {
        repositorioCaracteristicas.agregar(pregunta);
      });

    } catch (TipoPreguntaInexistenteException | NullPointerException exception) {

      modelo.put("error", exception.getMessage());

      return new ModelAndView(modelo, "crearCaracteristica.html.hbs");
    }

    return new ModelAndView(modelo, "crearCaracteristica.html.hbs");
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
