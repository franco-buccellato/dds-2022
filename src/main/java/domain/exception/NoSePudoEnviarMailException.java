package domain.exception;

public class NoSePudoEnviarMailException extends RuntimeException {
  public NoSePudoEnviarMailException(String message) {
    super(message);
  }
}