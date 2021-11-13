package domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

import static domain.exception.Mensajes.NOT_NULO;

@Entity
@Table(name = "preguntas")
public class Pregunta {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "pregunta_id")
  protected int id;

  private String descripcion;

  @OneToMany
  @JoinColumn(name = "opcion_id")
  private List<Opcion> opciones;

  @Enumerated(EnumType.STRING)
  @Column(name = "alcance_pregunta")
  private AlcancePregunta alcancePregunta;

  private Boolean obligatoria;

  private Pregunta() {
  }

  public Pregunta(String descripcion, List<Opcion> opciones, AlcancePregunta alcancePregunta, Boolean obligatoria) {
    this.descripcion = Objects.requireNonNull(descripcion, NOT_NULO.mensaje("descripcion"));
    this.opciones = Objects.requireNonNull(opciones, NOT_NULO.mensaje("opciones"));
    this.alcancePregunta = Objects.requireNonNull(
        alcancePregunta, NOT_NULO.mensaje("alcancePregunta")
    );
    this.obligatoria = Objects.requireNonNull(obligatoria, NOT_NULO.mensaje("obligatoria"));
  }

  public String getDescripcion() {
    return descripcion;
  }

  public List<Opcion> getOpciones() {
    return opciones;
  }

  public AlcancePregunta getAlcancePregunta() {
    return alcancePregunta;
  }

  public Boolean getObligatoria() {
    return obligatoria;
  }

  public void setObligatoria(Boolean obligatoria) {
    this.obligatoria = obligatoria;
  }

//  public Boolean tienenMismasOpciones(Pregunta pregunta) {
//    return !this.getOpcionesSeleccionas().isEmpty()
//           && this.getOpcionesSeleccionas().containsAll(pregunta.getOpcionesSeleccionas());
//  }

//  public List<OpcionNueva> getOpcionesSeleccionas() {
//    return opciones
//        .stream()
//        .filter(Opcion::getSeleccionada)
//        .collect(Collectors.toList());
//  }

  public Boolean esRespuestaValida(List<Opcion> opcionesSeleccionadas) {
    return opcionesSeleccionadas
        .stream()
        .allMatch(
            opcionSeleccionada -> this.getOpciones()
                .stream()
                .anyMatch(
                    opcion -> opcion.getId().equals(opcionSeleccionada.getId())
                )
        );
  }
}
