package domain.exception;

import java.io.IOException;

public class NoSePuedenObtenerEmailCredentials extends RuntimeException {
  public NoSePuedenObtenerEmailCredentials(String mensaje) {
    super(mensaje);
  }
}
