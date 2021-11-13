package domain;

import static domain.exception.Mensajes.NOT_NULO;

import domain.exception.RespuestaInvalidaException;

import java.util.Objects;
import javax.persistence.*;

@Entity(name = "mascotas_caracteristicas")
public class MascotaCaracteristica {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "mascota_caracteristica_id", nullable = false)
  private Long id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "caracteristica_id")
  Caracteristica caracteristica;

  String respuesta;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

//  public Boolean tienenMismasOpciones(Caracteristica caracteristica) {
//    return !this.getOpcionesSeleccionas().isEmpty()
//           && caracteristica.getOpcionesSeleccionas().containsAll(this.getOpcionesSeleccionas());
//  }

  public Boolean tienenMismasOpciones(Pregunta pregunta) {
    return this.getCaracteristica().getDescripcion().equals(pregunta.getDescripcion())
           && pregunta.getOpcionesSeleccionas().stream()
               .anyMatch(opcion -> opcion.getDescripcion().matches(this.getRespuesta()));


    return this.getCaracteristica().tieneMismasOpciones(pregunta);

    return !this.getCaracteristica().getOpciones().isEmpty()
           && this.getOpcionesSeleccionas()
               .stream()
               .allMatch(cOpcion -> pregunta.getOpcionesSeleccionas()
                   .stream()
                   .anyMatch(pOpcion -> cOpcion.getDescripcion()
                       .matches(pOpcion.getDescripcion())));
  }
}
