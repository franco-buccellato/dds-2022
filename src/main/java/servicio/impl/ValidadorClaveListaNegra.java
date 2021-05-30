package servicio.impl;

import java.io.IOException;

import domain.exception.PasswordDebilException;
import servicio.RutasSistema;
import servicio.ValidacionClave;
import utilidades.LectorArchivos;

public class ValidadorClaveListaNegra implements ValidacionClave {
  private final String path = RutasSistema.listaNegraClaves();
  private LectorArchivos lector = new LectorArchivos(path);

  public void validarClave(String usuario, String clave) throws IOException {
    validarClaveDistintaUsuario(usuario, clave);
    validarClaveDistintaPalabraProhibida(clave);
  }

  private void validarClaveDistintaUsuario(String usuario, String clave) {
    if (usuario.equals(clave)) {
      throw new PasswordDebilException("La clave es igual al usuario ingresado");
    }
  }

  private void validarClaveDistintaPalabraProhibida(String clave) throws IOException {
    if (lector.existeEnArchivo(clave)) {
      throw new PasswordDebilException("La password ingresada no es permitida");
    }
  }
}
