package domain.usuario;

import domain.exception.PasswordDebilException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UsuarioYClaveTest {
  CreadorUsuario creadorUsuario;

  @BeforeEach
  public void init() {
    creadorUsuario = new CreadorUsuario();
  }

  @Test
  public void claveTieneSecuenciaDeLetrasYNumeros() {
    assertThrows(PasswordDebilException.class, () ->
        creadorUsuario.nuevoUsuario("uPassSecuenciaMixta", "abc12345", TipoUsuario.ESTANDAR, null));
  }

  @Test
  public void claveTieneSecuenciaDeLetras() {
    assertThrows(PasswordDebilException.class, () ->
        creadorUsuario.nuevoUsuario("uPassSecuenciaLetras", "bcdefghij", TipoUsuario.ESTANDAR, null));
  }

  @Test
  public void claveTieneSecuenciaDeNumeros() {
    assertThrows(PasswordDebilException.class, () ->
        creadorUsuario.nuevoUsuario("uPassSecuenciaNumeros", "23456789", TipoUsuario.ESTANDAR, null));
  }

  @Test
  public void claveDeCaracteresRepetidos() {
    assertThrows(PasswordDebilException.class, () ->
        creadorUsuario.nuevoUsuario("uPassrepeticion", "bbbbbbbb", TipoUsuario.ESTANDAR, null));
  }

  @Test
  public void claveTienePalabraProhibida() {
    assertThrows(PasswordDebilException.class, () ->
        creadorUsuario.nuevoUsuario("uPassPalabraProhibida", "rescatedemascotas", TipoUsuario.ESTANDAR, null));
  }

  @Test
  public void claveMasCortaQueLoPermitido() {
    assertThrows(PasswordDebilException.class, () ->
        creadorUsuario.nuevoUsuario("uClaveCorta", "clave", TipoUsuario.ESTANDAR, null));
  }

  @Test
  public void claveIgualAlUsuario() {
    assertThrows(PasswordDebilException.class, () ->
        creadorUsuario.nuevoUsuario("uClaveIgualUsuario", "uClaveIgualUsuario", TipoUsuario.ESTANDAR, null));
  }

  @Test
  public void testPasswordEstaEnTop10000() {
    assertThrows(PasswordDebilException.class, () ->
        creadorUsuario.nuevoUsuario("uClaveTop10000", "password", TipoUsuario.ESTANDAR, null));
  }

  @Test
  public void claveCorrecta() {
    Usuario usuarioCorrecto = creadorUsuario.nuevoUsuario("uCorrecto", "administradorcorrecto", TipoUsuario.ADMINISTRADOR, null);
    assertEquals(TipoUsuario.ADMINISTRADOR, usuarioCorrecto.getTipoUsuario());
  }
}
