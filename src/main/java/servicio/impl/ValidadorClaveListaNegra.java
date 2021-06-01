package servicio.impl;

import domain.exception.PasswordDebilException;
import domain.servicio.exception.BaseCredencialesNoDisponibleException;
import servicio.RutasSistema;
import servicio.ValidacionClave;
import utilidades.LectorArchivos;

import java.io.IOException;

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