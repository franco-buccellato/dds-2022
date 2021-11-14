package controllers;

import static spark.Spark.halt;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.TipoPregunta;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CaracteristicaController extends BaseController {
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
    return null;
  }
}
