package domain;

import constants.Constantes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepositorioMascotasEncontradasTest {
  private Rescate pastorAleman;
  private Rescate bulldog;
  private Rescate husky;
  private Rescate poodle;
  private Rescate persa;
  private Rescate maineCoon;
  private Rescate siames;
  private Rescate bengala;
  private RepositorioMascotaEncontrada repoTest;
  private LocalDate fechaSistema;
  private LocalDate fechaSistemaTest = LocalDate.of (2021, 5, 5);

  @BeforeEach
  public void inicio() throws IOException {
    Image fotoPerro1Encontrado = ImageIO.read(new File("resources/images/perroEncontrado1.jpg"));
    List<Image> fotos = new ArrayList<>(Collections.singletonList(fotoPerro1Encontrado));

    pastorAleman = new Rescate(
        fotos,
        "perro pastorAleman",
        ubicacion(),
        LocalDate.now().minus(1, ChronoUnit.DAYS)
    );
    bulldog = new Rescate(
        fotos,
        "perro bulldog",
        ubicacion(),
        LocalDate.now().minus(3, ChronoUnit.DAYS)
    );

    husky = new Rescate(
        fotos,
        "perro husky",
        ubicacion(),
        LocalDate.now().minus(5, ChronoUnit.DAYS)
    );

    poodle = new Rescate(
        fotos,
        "perro poodle",
        ubicacion(),
        LocalDate.now().minus(8, ChronoUnit.DAYS)
    );

    persa = new Rescate(
        fotos,
        "gato persa",
        ubicacion(),
        LocalDate.of(2021, 4, 12)
    );

    maineCoon = new Rescate(
        fotos,
        "gato maineCoon",
        ubicacion(),
        LocalDate.of(2021, 3, 15)
    );

    siames = new Rescate(
        fotos,
        "gato siames",
        ubicacion(),
        LocalDate.of(2021, 3, 15)
    );

    bengala = new Rescate(
        fotos,
        "gato bengala",
        ubicacion(),
        LocalDate.of(2021, 2, 10)
    );

    repoTest = RepositorioMascotaEncontrada.getRepositorio();
    repoTest.setMascotasEncontradas(Arrays.asList(
        pastorAleman,
        bulldog,
        husky,
        poodle,
        persa,
        maineCoon,
        siames,
        bengala)
    );
  }

  public List<Rescate> ultimasEncontradasEnDiezDias() {
    return Arrays.asList(pastorAleman, bulldog, husky, poodle);
  }

  @Test
  public void testUltimasMascotasEncontradasEnDiezDias() {
    assertEquals(this.ultimasEncontradasEnDiezDias(), repoTest.ultimasEncontradasEn10Dias());
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
