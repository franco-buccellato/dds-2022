package domain.servicio.impl;

import domain.exception.PasswordDebilException;
import domain.servicio.RutasSistema;
import domain.servicio.ValidacionClave;
import domain.utilidades.LectorArchivos;
import java.io.IOException;

public class ValidadorClaveListaNegra implements ValidacionClave {
  private final String path = RutasSistema.listaNegraClaves();
  private LectorArchivos lector = new LectorArchivos(path);

  public void validarClave(String usuario, String clave) throws IOException {
    validarClaveDistintaUsuario(usuario, clave);
    validarClaveDistintaPalabraProhibida(clave);
  }

  private void validarClaveDistintaUsuario(String usuario, String clave) {
    if (usuario.equals(clave)) {
      throw new PasswordDebilException("La password es igual al usuario ingresado");
    }
  }

  private void validarClaveDistintaPalabraProhibida(String clave) throws IOException {
    if (lector.existeEnArchivo(clave)) {
      throw new PasswordDebilException("La password ingresada no es permitida");
    }
  }
}
