package domain;

import constants.Fixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

  @BeforeEach
  public void inicio() {
    List<String> fotos = new ArrayList<>(Collections.singletonList("unaFoto"));
    Fixture fixture = new Fixture();
    Ubicacion ubicacion = fixture.ubicacion();
    Rescatista rescatista = fixture.rescatista();
    Mascota mascota = fixture.mascotaConChapa();

    pastorAleman = new Rescate(
        fotos,
        "perro pastorAleman",
        ubicacion,
        LocalDate.now().minus(1, ChronoUnit.DAYS),
        mascota,
        rescatista
    );

    bulldog = new Rescate(
        fotos,
        "perro bulldog",
        ubicacion,
        LocalDate.now().minus(3, ChronoUnit.DAYS),
        mascota,
        rescatista
    );

    husky = new Rescate(
        fotos,
        "perro husky",
        ubicacion,
        LocalDate.now().minus(5, ChronoUnit.DAYS),
        mascota,
        rescatista
    );

    poodle = new Rescate(
        fotos,
        "perro poodle",
        ubicacion,
        LocalDate.now().minus(8, ChronoUnit.DAYS),
        mascota,
        rescatista
    );

    persa = new Rescate(
        fotos,
        "gato persa",
        ubicacion,
        LocalDate.of(2021, 4, 12),
        mascota,
        rescatista
    );

    maineCoon = new Rescate(
        fotos,
        "gato maineCoon",
        ubicacion,
        LocalDate.of(2021, 3, 15),
        mascota,
        rescatista
    );

    siames = new Rescate(
        fotos,
        "gato siames",
        ubicacion,
        LocalDate.of(2021, 3, 15),
        mascota,
        rescatista
    );

    bengala = new Rescate(
        fotos,
        "gato bengala",
        ubicacion,
        LocalDate.of(2021, 2, 10),
        mascota,
        rescatista
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
}
