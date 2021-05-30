package servicio.impl;

import java.io.IOException;

import domain.exception.PasswordDebilException;
import servicio.RutasSistema;
import servicio.ValidacionClave;
import utilidades.LectorArchivos;

public class ValidadorClaveTop10000 implements ValidacionClave {
  private final String path = RutasSistema.listaTop10000Claves();
  private LectorArchivos lector = new LectorArchivos(path);

  public void validarClave(String usuario, String password) throws IOException {
    if (lector.existeEnArchivo(password)) {
      throw new PasswordDebilException("La password ingresada es debil");
    }
  }
}
