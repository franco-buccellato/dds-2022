package domain;

import constants.Constantes;
import domain.mascotaEncontrada.MascotaEncontrada;
import domain.mascotaEncontrada.RepositorioMascotaEncontrada;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepositorioMascotasEncontradasTest {
  private MascotaEncontrada pastorAleman;
  private MascotaEncontrada bulldog;
  private MascotaEncontrada husky;
  private MascotaEncontrada poodle;
  private MascotaEncontrada persa;
  private MascotaEncontrada maineCoon;
  private MascotaEncontrada siames;
  private MascotaEncontrada bengala;
  private RepositorioMascotaEncontrada repoTest;
  private LocalDate fechaSistema;
  private LocalDate fechaSistemaTest = LocalDate.of (2021, 5, 5);

  @BeforeEach
  public void inicio(){
    fechaSistema = Constantes.getConstates().getFechaSistema();
    Constantes.getConstates().setFechaSistema (fechaSistemaTest);

    pastorAleman = new MascotaEncontrada(null, "perro pastorAleman" , "", LocalDate.of(2021, 5, 4));
    bulldog		 = new MascotaEncontrada(null, "perro bulldog"		, "", LocalDate.of(2021, 5, 3));
    husky		 = new MascotaEncontrada(null, "perro husky"		, "", LocalDate.of(2021, 5, 2));
    poodle		 = new MascotaEncontrada(null, "perro poodle"		, "", LocalDate.of(2021, 5, 1));
    persa		 = new MascotaEncontrada(null, "gato persa"			, "", LocalDate.of(2021, 4, 12));
    maineCoon	 = new MascotaEncontrada(null, "gato maineCoon"		, "", LocalDate.of(2021, 3, 15));
    siames		 = new MascotaEncontrada(null, "gato siames"		, "", LocalDate.of(2021, 3, 15));
    bengala		 = new MascotaEncontrada(null, "gato bengala"		, "", LocalDate.of(2021, 2, 10));

    repoTest = RepositorioMascotaEncontrada.getRepositorio();
    repoTest.setMascotasEncontradas(Arrays.asList(pastorAleman, bulldog, husky, poodle, persa, maineCoon, siames, bengala));
  }

  @AfterEach
  public void fin(){
    Constantes.getConstates().setFechaSistema(fechaSistema);
  }

  public List<MascotaEncontrada> ultimasEncontradasEnDiezDias(){
    return Arrays.asList(pastorAleman,bulldog,husky,poodle);
  }

  @Test
  public void testUltimasMascotasEncontradasEnDiezDias(){
    assertEquals(this.ultimasEncontradasEnDiezDias(), repoTest.ultimasEncontradasEn10Dias());
  }
}
