package controllers;

import static spark.Spark.halt;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.*;
import domain.exception.TipoPreguntaInexistenteException;
import domain.repositorios.RepositorioCaracteristicas;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CaracteristicaController extends BaseController implements WithGlobalEntityManager, TransactionalOps {
  public ModelAndView crear(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();

    boolean usuarioCreadorCaracteristicas = this.usuarioCreadorCaracteristicas(request);
    if (!usuarioCreadorCaracteristicas) {
      halt(401, "Acceso Denegeado");
    }
    modelo.put("usuarioCreadorCaracteristicas", usuarioCreadorCaracteristicas);

    boolean sesionIniciada = this.sesionIniciada(request);
    modelo.put("sesionIniciada", sesionIniciada);

    List<TipoPregunta> tipoCaracteristicas = Arrays.asList(TipoPregunta.values());
    modelo.put("tipoCaracteristicas", tipoCaracteristicas);

    return new ModelAndView(modelo, "crearCaracteristica.html.hbs");
  }

  public Void guardar(Request request, Response response) {
    TipoPregunta tipoPregunta = TipoPregunta.valueOf(
        request.queryParams("registro-tipoCaracteristica")
    );

    List<ObjetivoPregunta> objetivos = Arrays.asList(ObjetivoPregunta.CARACTERISTICA_MASCOTA);

    String descripcion = request.queryParams("registro-descripcion");

    Boolean obligatoria = request.queryParams("registro-obligatoria").equals("SI");

    RepositorioCaracteristicas repositorioCaracteristicas = RepositorioCaracteristicas.getInstance();

    Pregunta pregunta;

    switch (tipoPregunta) {

      case TEXT:
        pregunta = new PreguntaText(objetivos, descripcion, obligatoria);
        break;

      case NUMBER:
        pregunta = new PreguntaNumber(objetivos, descripcion, obligatoria);
        break;

//      case BULLET:
//        pregunta = new PreguntaBullet(objetivos, descripcion, obligatoria);
//        break;
//
//      case CHECKBOX:
//        pregunta = new PreguntaCheckBox(objetivos, descripcion, obligatoria);
//        break;

      default:
        throw new TipoPreguntaInexistenteException("No existe el tipo de pregunta");
    }

    withTransaction(() -> {
      repositorioCaracteristicas.agregar(pregunta);
    });

    response.redirect("/caracteristicas/crear");

    return null;
  }
}
