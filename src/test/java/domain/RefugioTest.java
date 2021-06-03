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
  void testApi() throws NoSuchAlgorithmException, KeyManagementException {
    assertEquals("Adopteros Argentina", hogares.buscarHogares(1).get(1).getNombre());
  }
}