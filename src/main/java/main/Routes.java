package main;

import static spark.Spark.after;

import controllers.HomeController;
import controllers.MascotaController;
import controllers.SesionController;
import controllers.MascotaController;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Routes {
  public static void main(String[] args) {
    System.out.println("Iniciando servidor");

    Spark.port(getHerokuAssignedPort());
    Spark.staticFileLocation("/public");

    new Bootstrap().run();

    HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
    SesionController sesionController = new SesionController();
    HomeController homeController = new HomeController();
    MascotaController mascotaController = new MascotaController();

    Spark.get("/", homeController::getHome, engine);
    Spark.get("/login", sesionController::mostrarLogin, engine);
    Spark.post("/login", sesionController::iniciarSesion);
    Spark.get("/logout", sesionController::cerrarSesion);
    Spark.get("/registrarMascota", mascotaController::formularioRegistrarMascota, engine);
    Spark.post("/registrarMascota", mascotaController::registrarMascota);
    Spark.get("/encontreMascota", mascotaController::encontreMascota);
    Spark.get("/registro", sesionController::mostrarRegistroUsuario, engine);
    Spark.post("/registro", sesionController::registrarUsuario, engine);

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
