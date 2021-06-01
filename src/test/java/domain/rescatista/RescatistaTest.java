package domain.rescatista;

import static domain.TipoMascota.PERRO;
import static domain.exception.Mensajes.NOT_NULO;
import static domain.TipoIdentificacion.DNI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.Image;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RescatistaTest {
  DatoPersonal datoPersonalDonRescatista;
  DatoPersonal getDatoPersonalDoniaRescatista;
  Rescatista donRescatista;
  Rescatista doniaRescatista;
  Rescate rescateCaniche;
  Mascota caniche;
  Contacto contactoDon;
  List<String> fotos;

  @BeforeEach

  public void setup() {
    contactoDon = new Contacto("don", "Rescatista", "1234", "donrescatista@rescatista.com", Vinculo.TITULAR);
      datoPersonalDonRescatista = new DatoPersonal(
          "Don",
          "Toretto",
          TipoIdentificacion.DNI,
          "99999999",
          LocalDate.of(1995, 5, 5)
      );
    getDatoPersonalDoniaRescatista = new DatoPersonal(
        "Donia",
        "Florinda",
        TipoIdentificacion.DNI,
        "88888888",
        LocalDate.of(1995, 4, 4)
    );
    donRescatista = new Rescatista(
        datoPersonalDonRescatista,
        contactoDon,
        ubicacion()
    );
    doniaRescatista = new Rescatista(
        datoPersonalDonRescatista,
        new Contacto(
            "donia",
            "Rescatista",
            "1234",
            "doniarescatista@rescatista.com",
            Vinculo.TITULAR
        ),
        ubicacion()
    );
    fotos = new ArrayList<>(Collections.singletonList("unaFoto"));
    caniche = new MascotaConChapa(
        PERRO,
        "Penelope",
        "Pepi",
        3.0,
        Sexo.HEMBRA,
        "Tiene pulgas",
        fotos,
        null,
        SituacionMascota.EN_HOGAR_TRANSITORIO
    );
    rescateCaniche = new Rescate(
        fotos,
        "Canino macho, color negro, raza caniche",
        ubicacion(),
        LocalDate.of(2021, 4, 4),
        caniche,
        donRescatista
    );
  }

  @Test
  void noPuedoCrearUnRescatistaSiFaltanUnParametroRequerido() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new Rescatista(
          new DatoPersonal(
              "Don",
              null,
              TipoIdentificacion.DNI,
              "99999999",
              LocalDate.of(1995, 5, 5)
          ),
          contactoDon,
          ubicacion()
      );
    });
    assertEquals(NOT_NULO.mensaje("apellido"), exception.getMessage());
  }

  @Test
  void puedoObtenerDatosDeUnRescatista() {
    assertEquals("Don", donRescatista.datoPersonal().getNombre());
    assertEquals("Toretto", donRescatista.datoPersonal().getApellido());
    assertEquals(DNI, donRescatista.datoPersonal().getTipoIdentificacion());
    assertEquals("99999999", donRescatista.datoPersonal().getNumeroIdentificacion());
    assertEquals(contactoDon, donRescatista.getContacto());
    assertEquals(LocalDate.of(1995, 5, 5), donRescatista.datoPersonal().getFechaNacimiento());
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
