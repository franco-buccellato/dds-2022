package domain;

import domain.TipoUsuario;
import domain.Usuario;
import domain.exception.PasswordDebilException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UsuarioYClaveTest {

  @Test
  public void claveTieneSecuenciaDeLetrasYNumeros() {
    assertThrows(PasswordDebilException.class, () ->
        new Usuario("uPassSecuenciaMixta", "abc12345", TipoUsuario.ESTANDAR));
  }

  @Test
  public void claveTieneSecuenciaDeLetras() {
    assertThrows(PasswordDebilException.class, () ->
        new Usuario("uPassSecuenciaLetras", "bcdefghij", TipoUsuario.ESTANDAR));
  }

  @Test
  public void claveTieneSecuenciaDeNumeros() {
    assertThrows(PasswordDebilException.class, () ->
        new Usuario("uPassSecuenciaNumeros", "23456789", TipoUsuario.ESTANDAR));
  }

  @Test
  public void claveDeCaracteresRepetidos() {
    assertThrows(PasswordDebilException.class, () ->
        new Usuario("uPassrepeticion", "bbbbbbbb", TipoUsuario.ESTANDAR));
  }

  @Test
  public void claveTienePalabraProhibida() {
    assertThrows(PasswordDebilException.class, () ->
        new Usuario("uPassPalabraProhibida", "rescatedemascotas", TipoUsuario.ESTANDAR));
  }

  @Test
  public void claveMasCortaQueLoPermitido() {
    assertThrows(PasswordDebilException.class, () ->
        new Usuario("uClaveCorta", "clave", TipoUsuario.ESTANDAR));
  }

  @Test
  public void claveIgualAlUsuario() {
    assertThrows(PasswordDebilException.class, () ->
        new Usuario("uClaveIgualUsuario", "uClaveIgualUsuario", TipoUsuario.ESTANDAR));
  }

  @Test
  public void testPasswordEstaEnTop10000() {
    assertThrows(PasswordDebilException.class, () ->
        new Usuario("uClaveTop10000", "password", TipoUsuario.ESTANDAR));
  }

  @Test
  public void claveCorrecta() {
    Usuario usuarioCorrecto = new Usuario("uCorrecto", "administradorcorrecto", TipoUsuario.ADMINISTRADOR);
    assertEquals(TipoUsuario.ADMINISTRADOR, usuarioCorrecto.getTipoUsuario());
  }
}
