package controllers;

import static spark.Spark.halt;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.TipoCaracteristica;
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

    List<TipoCaracteristica> tipoCaracteristicas = Arrays.asList(TipoCaracteristica.values());
    modelo.put("tipoCaracteristicas", tipoCaracteristicas);

    return new ModelAndView(modelo, "crearCaracteristica.html.hbs");
  }

  public Void guardar(Request request, Response response) {
    return null;
  }
}
