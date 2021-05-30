package domain.servicio;

import java.io.IOException;

public interface ValidacionClave {
  void validarClave(String usuario, String clave) throws IOException;
}
