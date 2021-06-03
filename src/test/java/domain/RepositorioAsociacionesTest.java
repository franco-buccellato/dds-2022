package domain;

import constants.Fixture;
import domain.exception.AsociacionNoEncontradaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    repositorioAsociacionesTest = RepositorioAsociaciones.getRepositorioAsociaciones();
  }

  @Test
  void elRepositorioDeAsociacionesTiene3Asociaciones() {
    assertEquals(3, repositorioAsociacionesTest.getAsociaciones().size());
  }

  @Test
  void siNoHayAsociacionesAlBuscarLaMasCercanaLanzaUnaExcepcion() {
    repositorioAsociacionesTest.setAsociaciones(new ArrayList<>(Collections.emptyList()));
    Exception exception = assertThrows(AsociacionNoEncontradaException.class, () -> {
      repositorioAsociacionesTest.encontrarMasCercana(ubicacionRescatista);
    });
    assertEquals("No se encontro asociacion cercana", exception.getMessage());
  }

  @Test
  void laAsociacion1EsLaMasCercanaALaUbicacionDelRescatista() {
    repositorioAsociacionesTest.setAsociaciones(new ArrayList<>(Arrays.asList(asociacion1, asociacion2, asociacion3)));
    assertEquals(
        repositorioAsociacionesTest.encontrarMasCercana(ubicacionRescatista).getNombre(),
        asociacion1.getNombre()
    );
  }

}
