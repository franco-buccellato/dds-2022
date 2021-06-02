package domain;

import constants.Fixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepositorioAsociacionesTest {

  Asociacion asociacion1, asociacion2, asociacion3;
  RepositorioAsociaciones repositorioAsociacionesTest;
  Ubicacion ubicacionRescatista;

  @BeforeEach
  void setup() {
    Fixture fixture = new Fixture();
    asociacion1 = new Asociacion("Asociacion1", fixture.ubicacionAsociacion1());
    asociacion2 = new Asociacion("Asociacion2", fixture.ubicacionAsociacion2());
    asociacion3 = new Asociacion("Asociacion3", fixture.ubicacionAsociacion3());
    ubicacionRescatista = fixture.ubicacionRescatista();
    repositorioAsociacionesTest = RepositorioAsociaciones.getRrepositorioAsociaciones();
    repositorioAsociacionesTest.setAsociaciones(Arrays.asList(
        asociacion1,
        asociacion2,
        asociacion3)
    );
  }

  @Test
  void elRepositorioAsociacionesTestPosee3Asociaciones(){
    assertEquals(repositorioAsociacionesTest.size(), 3);
  }

  @Test
  void laAsociacion1EsLaMasCercanaALaUbicacionDelRescatista() {
    assertEquals(repositorioAsociacionesTest.encontrarMasCercana(ubicacionRescatista).getNombre(), asociacion1.getNombre());
  }

}
