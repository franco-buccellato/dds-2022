package controllers;

import domain.*;
import domain.repositorios.RepositorioMascotas;
import domain.repositorios.RepositorioUsuarios;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.imageio.ImageIO;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class RescateController extends BaseController implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {
  public ModelAndView registroRescateConChapa(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();
    boolean sesionIniciada = this.sesionIniciada(request);
    modelo.put("sesionIniciada", sesionIniciada);
    modelo.put("tipos_documentos", TipoIdentificacion.values());
    return new ModelAndView(modelo, "mascota_con_chapa.html.hbs");
  }

  public Void guardarRescateConChapa(Request request, Response response) throws ServletException, IOException {
    Map<String, Object> modelo = new HashMap<>();
    boolean sesionIniciada = this.sesionIniciada(request);
    modelo.put("sesionIniciada", sesionIniciada);
    Long idUsuario = request.session().attribute("idUsuario");
    Usuario usuarioActual = RepositorioUsuarios.getInstance().getById(idUsuario);

    request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
    BufferedImage bufferedImage;
    try (InputStream input = request.raw().getPart("chapa").getInputStream()) {
      bufferedImage = ImageIO.read(input);
    }
    Contacto contactoRescatista = new Contacto(
      request.queryParams("nombre"),
      request.queryParams("apellido"),
      request.queryParams("tel"),
      request.queryParams("email"),
      Vinculo.TITULAR
    );
    Ubicacion ubicacionRescatista = new Ubicacion(
        request.queryParams("r_dir"),
        request.queryParams("r_cp"),
        request.queryParams("r_loc")
    );
    Ubicacion ubicacionRescate = new Ubicacion(
        request.queryParams("m_dir"),
        request.queryParams("m_cp"),
        request.queryParams("m_loc")
    );
    Rescatista rescatista = new Rescatista(
        new DatoPersonal(
            request.queryParams("nombre"),
            request.queryParams("apellido"),
            TipoIdentificacion.valueOf(request.queryParams("tip_doc")),
            request.queryParams("ndoc"),
            LocalDate.parse(request.queryParams("fnacimiento"))
        ),
        contactoRescatista,
        ubicacionRescatista,
        usuarioActual
    );
    RescateConChapa rescateConChapa = new RescateConChapa(
        Arrays.asList(request.queryParams("foto")),
        request.queryParams("desc"),
        ubicacionRescate,
        LocalDate.now(),
        RepositorioMascotas.getInstace().getByQR(bufferedImage),
        rescatista
    );
    withTransaction(() -> {
        persist(ubicacionRescate);
        persist(ubicacionRescatista);
        persist(contactoRescatista);
        persist(rescatista);
        persist(rescateConChapa);
      });
    response.redirect("/encontreMascota");
    return null;
  }
}
