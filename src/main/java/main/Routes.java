package main;

import static spark.Spark.after;
import static spark.Spark.before;
import static spark.Spark.halt;
import static spark.Spark.staticFiles;
import static spark.debug.DebugScreen.enableDebugScreen;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import controllers.CaracteristicaController;
import controllers.HomeController;
import controllers.MascotaController;
import controllers.RescateController;
import controllers.SesionController;
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
    Spark.post("/registrarMascota", mascotaController::registrarMascota, engine);
    Spark.get("/encontreMascota", mascotaController::encontreMascota, engine);
    Spark.get("/mascota_con_chapa", rescateController::registroRescateConChapa, engine);
    Spark.post("/mascota_con_chapa", rescateController::guardarRescateConChapa);
    Spark.get("/mascota_sin_chapa", rescateController::registroRescateSinChapa, engine);
    Spark.post("/mascota_sin_chapa", rescateController::guardarRescateSinChapa);
    Spark.get("/registro", sesionController::mostrarRegistroUsuario, engine);
    Spark.post("/registro", sesionController::registrarUsuario, engine);
    Spark.get("/caracteristicas", caracteristicaController::getCaracteristicas, engine);
    Spark.get("/caracteristicas/crear", caracteristicaController::mostrarCrearCaracteristica, engine);
    Spark.get("/caracteristicas/:objetivo/:id", caracteristicaController::getDetalleCaracteristica, engine);
    Spark.post("/caracteristicas", caracteristicaController::crearCaracteristica, engine);
    Spark.post("/caracteristicas/:id", caracteristicaController::actualizarCaracteristica, engine);
    Spark.get("/preguntas/crear", caracteristicaController::mostrarCrearPregunta, engine);
    Spark.get("/preguntas/:id", caracteristicaController::getDetallePregunta, engine);
    Spark.post("/preguntas", caracteristicaController::crearPregunta, engine);
    Spark.post("/preguntas/:id", caracteristicaController::actualizarPregunta, engine);

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
