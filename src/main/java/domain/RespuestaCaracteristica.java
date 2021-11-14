package domain;

import static domain.exception.Mensajes.NOT_NULO;

import domain.exception.RespuestaInvalidaException;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import javax.persistence.*;

@Entity(name = "respuestas_caracteristicas")
public class RespuestaCaracteristica {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "respuesta_caracteristica_id", nullable = false)
  private Long id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "caracteristica_id")
  private Caracteristica caracteristica;
  @ManyToMany
  @JoinTable(
      name = "caracteristicas_opciones_seleccionadas",
      joinColumns = @JoinColumn(name = "respuesta_caracteristica_id"),
      inverseJoinColumns = @JoinColumn(name = "opcion_id")
  )
  private List<Opcion> opcionesSeleccionadas;
  String respuesta;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public RespuestaCaracteristica() {
  }

  public RespuestaCaracteristica(Caracteristica caracteristica, List<Opcion> opcionesSeleccionadas) {
    this.id = new Random().nextLong();
    this.chequearValidezOpcionesSeleccionadas(caracteristica, opcionesSeleccionadas);
    this.caracteristica = Objects.requireNonNull(
        caracteristica,
        NOT_NULO.mensaje("caracteristica")
    );
    this.opcionesSeleccionadas = Objects.requireNonNull(opcionesSeleccionadas, NOT_NULO.mensaje("opcionesSeleccionadas"));
    this.respuesta = caracteristica.setRespuesta(opcionesSeleccionadas);
  }

  public Caracteristica getCaracteristica() {
    return this.caracteristica;
  }

  public String getRespuesta() {
    return this.respuesta;
  }

  public List<Opcion> getOpcionesSeleccionadas() {
    return this.opcionesSeleccionadas;
  }

  public void chequearValidezOpcionesSeleccionadas(Caracteristica caracteristica, List<Opcion> opcionesSeleccionadas) throws RespuestaInvalidaException {
    if (!caracteristica.esRespuestaValida(opcionesSeleccionadas)) {
      throw new RespuestaInvalidaException("Las opciones seleccionadas no son validas");
    }
  }

  public Boolean tieneMismaRespuesta(RespuestaInteresAdopcion respuestaInteresAdopcion) {
    return this.opcionesSeleccionadas
        .stream()
        .allMatch(opcionSeleccionada -> respuestaInteresAdopcion.getOpcionesSeleccionadas().stream()
            .anyMatch(pregunta -> pregunta.getId().equals(opcionSeleccionada.getId()))
        );
  }
}
