package domain.servicio.impl;

import domain.exception.PasswordDebilException;
import domain.servicio.RutasSistema;
import domain.servicio.ValidacionClave;
import domain.servicio.exception.BaseCredencialesNoDisponibleException;
import domain.utilidades.LectorArchivos;
import java.io.IOException;

public class ValidadorClaveTop10000 implements ValidacionClave {
  private final String path = RutasSistema.listaTop10000Claves();
  private LectorArchivos lector = new LectorArchivos(path);

  public void validarClave(String usuario, String password) {
    try {
      if (lector.existeEnArchivo(password)) {
        throw new PasswordDebilException("La password ingresada es debil");
      }
    } catch (IOException e) {
      throw new BaseCredencialesNoDisponibleException(e.getMessage());
    }
  }
}
