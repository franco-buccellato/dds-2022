package domain.exception;

public enum Mensajes {
  NOT_NULO("El campo %s no puede ser nulo");

  private String mensaje;

  Mensajes(String mensaje) {
    this.mensaje = mensaje;
  }

  public String mensaje(String campo) {
    return String.format(this.mensaje, campo);
  }
}