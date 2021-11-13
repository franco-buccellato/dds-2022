package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import servicio.HogarTransitoServicio;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RefugioTest {
  HogarTransitoServicio hogares = new HogarTransitoServicio();

  @BeforeEach
  void setup() throws NoSuchAlgorithmException, KeyManagementException {
  }

  @Test
  void testApiPaginaInicial() throws NoSuchAlgorithmException, KeyManagementException {
    String hogar = hogares.buscarHogares(1).getHogares().get(1).getNombre();
    String hogar2 = hogares.hogaresDisponibles().get(1).getNombre();

    assertEquals(hogar, hogar2);
  }

  @Test
  void testApiPaginaAlta() throws NoSuchAlgorithmException, KeyManagementException {
    String hogar = hogares.buscarHogares(3).getHogares().get(1).getNombre();
    String hogar2 = hogares.hogaresDisponibles().get(21).getNombre();

    assertEquals(hogar, hogar2);
  }
}