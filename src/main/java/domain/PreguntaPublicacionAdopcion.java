package domain;

import static domain.exception.Mensajes.NOT_NULO;

import domain.exception.RespuestaInvalidaException;

import java.util.Objects;
import javax.persistence.*;

@Entity(name = "preguntas_adopcion")
public class PreguntaPublicacionAdopcion {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "pregunta_adopcion_id")
  private Long id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "pregunta_id")
  Pregunta pregunta;

  String respuesta;


  public PreguntaPublicacionAdopcion() {
  }

  public PreguntaPublicacionAdopcion(Pregunta pregunta, String respuesta) {
    this.pregunta = Objects.requireNonNull(pregunta, NOT_NULO.mensaje("pregunta"));
    this.respuesta = Objects.requireNonNull(respuesta, NOT_NULO.mensaje("respuesta"));
    this.chequearValidezRespuesta(respuesta);
  }

  public Pregunta getPregunta() {
    return this.pregunta;
  }

  public String getRespuesta() {
    return this.respuesta;
  }

  public void setRespuesta() {
    this.respuesta = Objects.requireNonNull(respuesta, NOT_NULO.mensaje("respuesta"));
    this.chequearValidezRespuesta(respuesta);
  }

  public void chequearValidezRespuesta(String respuesta) throws RespuestaInvalidaException {
    if (!this.pregunta.esRespuestaValida(respuesta)) {
      throw new RespuestaInvalidaException("La respuesta " + respuesta + " no es valida");
    }
  }

  public Boolean esMismaPregunta(Pregunta pregunta) {
    return this.getPregunta().getDescripcion().equals(pregunta.getDescripcion());
  }

  public Boolean tieneMismaRespuesta(PreguntaInteresAdopcion pregunta) {
    return this.getRespuesta().equals(pregunta.getRespuesta());
  }

}
