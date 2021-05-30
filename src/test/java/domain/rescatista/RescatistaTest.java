package domain.rescatista;

import static domain.exception.Mensajes.NOT_NULO;
import static domain.TipoIdentificacion.DNI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;

import domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RescatistaTest {
  Rescatista donRescatista;
  Rescatista doniaRescatista;
  Rescate caniche;
  Contacto contactoDon;
  Image fotoPerro1Encontrado;
  List<Image> fotos1;

  @BeforeEach

  public void setup() throws IOException {
    fotoPerro1Encontrado = ImageIO.read(new File("resources/images/perroEncontrado1.jpg"));
    fotos1 = new ArrayList<>(Collections.singletonList(fotoPerro1Encontrado));
    caniche = new Rescate(fotos1, "Canino macho, color negro, raza caniche", ubicacion(), LocalDate.of(2021, 4, 4));
    contactoDon = new Contacto("don", "Rescatista", "1234", "donrescatista@rescatista.com", Vinculo.TITULAR);
    donRescatista = new Rescatista(
        "Don",
        "Rescatista",
        TipoIdentificacion.DNI,
        "99999999",
        LocalDate.of(1995, 5, 5),
        caniche,
        contactoDon,
        ubicacion()
    );
    doniaRescatista = new Rescatista(
        "Donia",
        "Rescatista",
        TipoIdentificacion.DNI,
        "88888888",
        LocalDate.of(1995, 4, 4),
        new Rescate(
            fotos1,
            "Canino hembra, color blanco, raza caniche",
            ubicacion(),
            LocalDate.of(2021, 5, 4)
        ),
        new Contacto(
            "donia",
            "Rescatista",
            "1234",
            "doniarescatista@rescatista.com",
            Vinculo.TITULAR
        ),
        ubicacion()
    );
  }

  @Test
  void noPuedoCrearUnRescatistaSiFaltanUnParametroRequerido() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new Rescatista(
          "Don",
          null,
          TipoIdentificacion.DNI,
          "99999999",
          LocalDate.of(1995, 5, 5),
          new Rescate(fotos1, "Canino macho, color negro, raza caniche", ubicacion(), LocalDate.of(2021, 4, 4)),
          new Contacto("don", "Rescatista", "1234", "donrescatista@rescatista.com", Vinculo.TITULAR),
          ubicacion()
      );
    });
    assertEquals(NOT_NULO.mensaje("apellido"), exception.getMessage());
  }

  @Test
  void puedoObtenerDatosDeUnRescatista() {
    assertEquals("Don", donRescatista.getNombre());
    assertEquals("Rescatista", donRescatista.getApellido());
    assertEquals(DNI, donRescatista.getTipoIdentificacion());
    assertEquals("99999999", donRescatista.getNumeroIdentificacion());
    assertEquals(caniche, donRescatista.getRescate());
    assertEquals(contactoDon, donRescatista.getContacto());
    assertEquals(LocalDate.of(1995, 5, 5), donRescatista.getFechaNacimiento());
  }

  protected Ubicacion ubicacion() {
    return new Ubicacion(
        "El Rescatista 123",
        "1417",
        "CABA",
        BigDecimal.valueOf(56.54684),
        BigDecimal.valueOf(56.54684)
    );
  }
}
