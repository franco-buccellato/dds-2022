//package domain;
//
//import constants.Fixture;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class UbicacionTest extends Fixture {
//
//  Ubicacion ubicacion1, ubicacion2, ubicacion3;
//
//  @BeforeEach
//  void setup(){
//    ubicacion1 = ubicacion1();
//    ubicacion2 = ubicacion2();
//    ubicacion3 = ubicacion3();
//  }
//
//  @Test
//  void distanciaEntreLaMismaUbicacionEs0(){
//    assertEquals(ubicacion1.distanciaA(ubicacion1),0);
//  }
//
//  @Test
//  void distanciaEntreUbicacion1Ubicacion2Es3(){
//    assertEquals(ubicacion1.distanciaA(ubicacion2),13);
//  }
//
//  @Test
//  void distanciaEntreUbicacion1Ubicacion3Es10() {
//    assertEquals(ubicacion1.distanciaA(ubicacion3), 9);
//  }
//
//
//}