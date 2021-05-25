package servicio.impl;

import domain.exception.PasswordDebilException;
import servicio.RutasSistema;
import servicio.ValidacionClave;
import servicio.exception.BaseCredencialesNoDisponibleException;
import utilidades.LectorArchivos;
import java.io.IOException;

public class ValidadorClaveTop10000 implements ValidacionClave {
  private final String path = RutasSistema.listaTop10000Claves();
  private LectorArchivos lector = new LectorArchivos(path);

  public void validarClave(String usuario, String password) {
    try {
      if (lector.existeEnArchivo(password)) {
        throw new PasswordDebilException("La clave ingresada es debil");
      }
    } catch (IOException e) {
      throw new BaseCredencialesNoDisponibleException(e.getMessage());
    }
  }
}
