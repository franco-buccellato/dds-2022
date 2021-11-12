package controllers;

import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController extends BaseController {
  public ModelAndView getHome(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();

    boolean sesionIniciada = this.sesionIniciada(request);
    modelo.put("sesionIniciada", sesionIniciada);

    boolean usuarioCreadorCaracteristicas = this.usuarioCreadorCaracteristicas(request);
    modelo.put("usuarioCreadorCaracteristicas", usuarioCreadorCaracteristicas);

    return new ModelAndView(modelo, "index.html.hbs");
  }
}
