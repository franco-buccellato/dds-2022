package domain;

import constants.Constantes;
import domain.mascotaEncontrada.MascotaEncontrada;
import domain.mascotaEncontrada.RepositorioMascotaEncontrada;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
  public void inicio() throws IOException {
    Image fotoPerro1Encontrado = ImageIO.read(new File("resources/images/perroEncontrado1.jpg"));
    List<Image> fotos = new ArrayList<>(Collections.singletonList(fotoPerro1Encontrado));
    fechaSistema = Constantes.getConstates().getFechaSistema();
    Constantes.getConstates().setFechaSistema (fechaSistemaTest);

    pastorAleman = new MascotaEncontrada(fotos, "perro pastorAleman" , "", LocalDate.of(2021, 5, 4));
    bulldog		 = new MascotaEncontrada(fotos, "perro bulldog"		, "", LocalDate.of(2021, 5, 3));
    husky		 = new MascotaEncontrada(fotos, "perro husky"		, "", LocalDate.of(2021, 5, 2));
    poodle		 = new MascotaEncontrada(fotos, "perro poodle"		, "", LocalDate.of(2021, 5, 1));
    persa		 = new MascotaEncontrada(fotos, "gato persa"			, "", LocalDate.of(2021, 4, 12));
    maineCoon	 = new MascotaEncontrada(fotos, "gato maineCoon"		, "", LocalDate.of(2021, 3, 15));
    siames		 = new MascotaEncontrada(fotos, "gato siames"		, "", LocalDate.of(2021, 3, 15));
    bengala		 = new MascotaEncontrada(fotos, "gato bengala"		, "", LocalDate.of(2021, 2, 10));

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
