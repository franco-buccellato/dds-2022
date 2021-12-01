package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import servicio.HogarTransitoServicio;


public class RefugioTest {
  HogarTransitoServicio hogares = new HogarTransitoServicio();

  @Test
  void testApiPaginaInicial() {
    String hogar = hogares.buscarHogares(1).getHogares().get(1).getNombre();
    String hogar2 = hogares.hogaresDisponibles().get(1).getNombre();

    assertEquals(hogar, hogar2);
  }

  @Test
  void testApiPaginaAlta() {
    String hogar = hogares.buscarHogares(3).getHogares().get(1).getNombre();
    String hogar2 = hogares.hogaresDisponibles().get(21).getNombre();

    assertEquals(hogar, hogar2);
  }
}