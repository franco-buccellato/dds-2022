package main;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

import controllers.*;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Routes {
  public static void main(String[] args) {
    enableDebugScreen();
    System.out.println("Iniciando servidor");

    Spark.port(getHerokuAssignedPort());
    Spark.staticFileLocation("/public");
    staticFiles.externalLocation("src/main/resources/public/");

    new Bootstrap().run();

    HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
    SesionController sesionController = new SesionController();
    HomeController homeController = new HomeController();
    MascotaController mascotaController = new MascotaController();
    CaracteristicaController caracteristicaController = new CaracteristicaController();
    RescateController rescateController = new RescateController();

    before((request, response) -> {
      boolean autenticado = request.session().attribute("idUsuario") != null;
      boolean authEndpoints =
          (request.pathInfo().contains("registrarMascota"))
          || (request.pathInfo().contains("encontreMascota"))
          || (request.pathInfo().contains("caracteristicas/crear"));

      if (!autenticado && authEndpoints) {
        halt(401, "Acceso denegado");
      }
    });

    Spark.get("/", homeController::getHome, engine);
    Spark.get("/login", sesionController::mostrarLogin, engine);
    Spark.post("/login", sesionController::iniciarSesion);
    Spark.get("/logout", sesionController::cerrarSesion);
    Spark.get("/registrarMascota", mascotaController::formularioRegistrarMascota, engine);
    Spark.post("/registrarMascota", mascotaController::registrarMascota);
    Spark.get("/encontreMascota", mascotaController::encontreMascota, engine);
    Spark.get("/mascota_con_chapa", rescateController::registroRescateConChapa, engine);
    Spark.post("/mascota_con_chapa", rescateController::guardarRescateConChapa);
    Spark.get("/registro", sesionController::mostrarRegistroUsuario, engine);
    Spark.post("/registro", sesionController::registrarUsuario, engine);
    Spark.get("crearCaracteristica", caracteristicaController::crear, engine);
    Spark.post("crearCaracteristica", caracteristicaController::guardar);

    after((request, response) -> {
      PerThreadEntityManagers.getEntityManager();
      PerThreadEntityManagers.closeEntityManager();
    });
  }

  static int getHerokuAssignedPort() {
    ProcessBuilder processBuilder = new ProcessBuilder();
    if (processBuilder.environment().get("PORT") != null) {
      return Integer.parseInt(processBuilder.environment().get("PORT"));
    }
    return 8080; // puerto por defecto
  }
}
