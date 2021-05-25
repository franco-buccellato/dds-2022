package domain;

import utilidades.LectorArchivos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LectorDeArchivosTest {
  LectorArchivos lector1;
  LectorArchivos lector2;
  String pathCorrecto;
  String pathErroneo;

  @BeforeEach
  public void init() {
    pathCorrecto = "resources/commonCredentials/10k-most-common.txt";
    pathErroneo = "resources/commonCredentials/test.txt";
    lector1 = new LectorArchivos(pathCorrecto);
    lector2 = new LectorArchivos(pathErroneo);
  }

  @Test
  public void testPathValido() {
    assertTrue(lector1.pathArchivoValido());
  }

  @Test
  public void testPathErroneo() {
    assertFalse(lector2.pathArchivoValido());
  }
}
