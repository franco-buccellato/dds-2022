package domain;

import static domain.exception.Mensajes.NOT_NULO;

import domain.exception.RespuestaInvalidaException;
import java.util.Objects;

public class MascotaCaracteristica {

  Caracteristica caracteristica;

  String respuesta;

  public MascotaCaracteristica() {
  }

  public MascotaCaracteristica(Caracteristica caracteristica, String respuesta) {
    this.caracteristica = Objects.requireNonNull(
        caracteristica,
        NOT_NULO.mensaje("caracteristica")
    );
    this.respuesta = Objects.requireNonNull(respuesta, NOT_NULO.mensaje("respuesta"));
    this.chequearValidezRespuesta(respuesta);
  }

  public Caracteristica getCaracteristica() {
    return this.caracteristica;
  }

  public String getRespuesta() {
    return this.respuesta;
  }

  public void setRespuesta(String respuesta) {
    this.respuesta = Objects.requireNonNull(respuesta, NOT_NULO.mensaje("respuesta"));
    this.chequearValidezRespuesta(respuesta);
  }

  public void chequearValidezRespuesta(String respuesta) {
    if (!this.getCaracteristica().esRespuestaValida(respuesta)) {
      throw new RespuestaInvalidaException("La respuesta " + respuesta + " no es valida");
    }
  }
}
