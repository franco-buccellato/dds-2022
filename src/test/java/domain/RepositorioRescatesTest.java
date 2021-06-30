package domain;

import constants.Fixture;
import domain.repositorios.RepositorioRescates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepositorioRescatesTest {
  private RescateSinChapa pastorAleman;
  private RescateSinChapa bulldog;
  private RescateSinChapa husky;
  private RescateSinChapa poodle;
  private RescateSinChapa persa;
  private RescateSinChapa maineCoon;
  private RescateSinChapa siames;
  private RescateSinChapa bengala;
  private RepositorioRescates repoTest;

  @BeforeEach
  public void inicio() {
    List<String> fotos = new ArrayList<>(Collections.singletonList("unaFoto"));
    Fixture fixture = new Fixture();
    Ubicacion ubicacion = fixture.ubicacion1();
    Rescatista rescatista = fixture.rescatista();
    Mascota mascota = fixture.mascota1();

    pastorAleman = new RescateSinChapa(
        fotos,
        "perro pastorAleman",
        ubicacion,
        LocalDate.now().minus(1, ChronoUnit.DAYS),
        mascota,
        rescatista
    );

    bulldog = new RescateSinChapa(
        fotos,
        "perro bulldog",
        ubicacion,
        LocalDate.now().minus(3, ChronoUnit.DAYS),
        mascota,
        rescatista
    );

    husky = new RescateSinChapa(
        fotos,
        "perro husky",
        ubicacion,
        LocalDate.now().minus(5, ChronoUnit.DAYS),
        mascota,
        rescatista
    );

    poodle = new RescateSinChapa(
        fotos,
        "perro poodle",
        ubicacion,
        LocalDate.now().minus(8, ChronoUnit.DAYS),
        mascota,
        rescatista
    );

    persa = new RescateSinChapa(
        fotos,
        "gato persa",
        ubicacion,
        LocalDate.of(2021, 4, 12),
        mascota,
        rescatista
    );

    maineCoon = new RescateSinChapa(
        fotos,
        "gato maineCoon",
        ubicacion,
        LocalDate.of(2021, 3, 15),
        mascota,
        rescatista
    );

    siames = new RescateSinChapa(
        fotos,
        "gato siames",
        ubicacion,
        LocalDate.of(2021, 3, 15),
        mascota,
        rescatista
    );

    bengala = new RescateSinChapa(
        fotos,
        "gato bengala",
        ubicacion,
        LocalDate.of(2021, 2, 10),
        mascota,
        rescatista
    );

    repoTest = RepositorioRescates.getRepositorio();
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

  public List<RescateSinChapa> ultimasEncontradasEnDiezDias() {
    return Arrays.asList(pastorAleman, bulldog, husky, poodle);
  }

  @Test
  public void testUltimasMascotasEncontradasEnDiezDias() {
    assertEquals(this.ultimasEncontradasEnDiezDias(), repoTest.ultimosRescatesEn10Dias());
  }
}
