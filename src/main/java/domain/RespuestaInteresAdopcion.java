package domain;

import domain.exception.RespuestaInvalidaException;

import static domain.exception.Mensajes.NOT_NULO;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import javax.persistence.*;

@Entity(name = "respuestas_interes_adopcion")
public class RespuestaInteresAdopcion {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "respuesta_interes_adopcion_id", nullable = false)
  private Long id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "pregunta_id")
  private Pregunta pregunta;
  @ManyToMany
  @JoinTable(
      name = "interes_opciones_seleccionadas",
      joinColumns = @JoinColumn(name = "respuesta_interes_adopcion_id"),
      inverseJoinColumns = @JoinColumn(name = "opcion_id")
  )
  private List<Opcion> opcionesSeleccionadas;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public RespuestaInteresAdopcion() {
  }

  public RespuestaInteresAdopcion(Pregunta pregunta, List<Opcion> opcionesSeleccionadas) {
    this.id = new Random().nextLong();
    this.chequearValidezOpcionesSeleccionadas(pregunta, opcionesSeleccionadas);
    this.pregunta = Objects.requireNonNull(
        pregunta,
        NOT_NULO.mensaje("pregunta")
    );
    this.opcionesSeleccionadas = Objects.requireNonNull(opcionesSeleccionadas, NOT_NULO.mensaje("opcionesSeleccionadas"));
  }

  public Pregunta getPregunta() {
    return this.pregunta;
  }

  public List<Opcion> getOpcionesSeleccionadas() {
    return this.opcionesSeleccionadas;
  }

  public void chequearValidezOpcionesSeleccionadas(Pregunta pregunta, List<Opcion> opcionesSeleccionadas) throws RespuestaInvalidaException {
    if (!pregunta.esRespuestaValida(opcionesSeleccionadas)) {
      throw new RespuestaInvalidaException("Las opciones seleccionadas no son validas");
    }
  }

  public Boolean tieneMismaRespuesta(RespuestaPublicacionAdopcion respuestaPublicacionAdopcion) {
    return this.getPregunta().esMismaPregunta(respuestaPublicacionAdopcion.getPregunta())
      && this.opcionesSeleccionadas
        .stream()
        .allMatch(opcionSeleccionada -> respuestaPublicacionAdopcion.getOpcionesSeleccionadas().stream()
            .anyMatch(pregunta -> pregunta.getId().equals(opcionSeleccionada.getId()))
        );
  }
}
