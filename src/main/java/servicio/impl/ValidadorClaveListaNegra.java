package servicio.impl;

import domain.exception.PasswordDebilException;
import java.io.IOException;
import servicio.RutasSistema;
import servicio.ValidacionClave;
import servicio.exception.BaseCredencialesNoDisponibleException;
import utilidades.LectorArchivos;

public class ValidadorClaveListaNegra implements ValidacionClave {
  private final String path = RutasSistema.listaNegraClaves();
  private LectorArchivos lector = new LectorArchivos(path);

  public void validarClave(String usuario, String clave) {
    validarClaveDistintaUsuario(usuario, clave);
    validarClaveDistintaPalabraProhibida(clave);
  }

  private void validarClaveDistintaUsuario(String usuario, String clave) {
    if (usuario.equals(clave)) {
      throw new PasswordDebilException("La clave es igual al usuario ingresado");
    }
  }

  private void validarClaveDistintaPalabraProhibida(String clave) {
    try {
      if (lector.existeEnArchivo(clave)) {
        throw new PasswordDebilException("La clave ingresada no esta permitida");
      }
    } catch (IOException e) {
      throw new BaseCredencialesNoDisponibleException(e.getMessage());
    }

  }
}
