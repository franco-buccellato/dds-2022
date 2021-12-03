package domain.exception;

public class TipoPreguntaInexistenteException extends RuntimeException {
  public TipoPreguntaInexistenteException(String mensaje) {
    super(mensaje);
  }
}
