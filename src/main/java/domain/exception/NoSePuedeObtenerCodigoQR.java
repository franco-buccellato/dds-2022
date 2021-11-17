package domain.exception;

public class NoSePuedeObtenerCodigoQR extends RuntimeException {
  public NoSePuedeObtenerCodigoQR(String message) {
    super(message);
  }
}
