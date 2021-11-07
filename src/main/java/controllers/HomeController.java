package controllers;

import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController {
  public ModelAndView getHome(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();
    modelo.put("sesionIniciada", request.session().attribute("idUsuario") != null);
    return new ModelAndView(modelo, "index.html.hbs");
  }
}
