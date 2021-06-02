package domain;

import constants.Fixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UbicacionTest {

  Ubicacion ubicacion1, ubicacion2, ubicacion3;

  @BeforeEach
  void setup(){
    Fixture fixture = new Fixture();
    ubicacion1 = fixture.ubicacion1();
    ubicacion2 = fixture.ubicacion2();
    ubicacion3 = fixture.ubicacion3();
  }

  @Test
  void distanciaEntreLaMismaUbicacionEs0(){
    assertEquals(ubicacion1.distanciaA(ubicacion1),0);
  }

  @Test
  void distanciaEntreUbicacion1Ubicacion2Es3(){
    assertEquals(ubicacion1.distanciaA(ubicacion2),3);
  }

  @Test
  void distanciaEntreUbicacion1Ubicacion3Es10() {
    assertEquals(ubicacion1.distanciaA(ubicacion3), 10);
  }


}