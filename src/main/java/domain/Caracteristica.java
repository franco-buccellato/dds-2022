package domain;

import org.json.simple.JSONObject;

import javax.persistence.*;

import static domain.exception.Mensajes.NOT_NULO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity(name = "caracteristicas")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class Caracteristica {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected int id;
  @Embedded
  @Column(name = "tipo_caracteristica")
  protected TipoCaracteristica tipoCaracteristica;
  protected String descripcion;
  @OneToMany()
  @JoinColumn(name = "opcion_id")
  protected List<Opcion> opciones;
  //protected Boolean obligatoria;

  public Caracteristica(TipoCaracteristica tipoCaracteristica, String descripcion) {
    this.tipoCaracteristica = Objects.requireNonNull(
        tipoCaracteristica,
        NOT_NULO.mensaje("tipoCaracteristica")
    );
    this.descripcion = descripcion;
    this.opciones = new ArrayList<>();
  }

  public int getId () {
    return this.id;
  }

  public TipoCaracteristica getTipoCaracteristica() {
    return tipoCaracteristica;
  }

  public Boolean tienenMismasOpciones(Caracteristica caracteristica) {
    return !this.getOpcionesSeleccionas().isEmpty()
        && caracteristica.getOpcionesSeleccionas().containsAll(this.getOpcionesSeleccionas());
  }

  public Boolean tienenMismasOpciones(Pregunta pregunta) {
    return !this.getOpcionesSeleccionas().isEmpty()
        && this.getOpcionesSeleccionas()
          .stream()
          .allMatch(cOpcion -> pregunta.getOpcionesSeleccionas().stream().anyMatch(pOpcion -> cOpcion.getDescripcion().matches(pOpcion.getDescripcion())));
  }

  public String getDescripcion() {
    return descripcion;
  }

  public abstract List<Opcion> getOpciones();

  public abstract List<Opcion> getOpcionesSeleccionas();

  public JSONObject toJson() {
    JSONObject jsonCaracteristica = new JSONObject();
    jsonCaracteristica.put("tipo_caracteristica", this.tipoCaracteristica.toString());
    jsonCaracteristica.put("descripcion", this.descripcion);
    jsonCaracteristica.put("opciones", this.getOpciones().stream().map(opcion -> opcion.toJson()).collect(Collectors.toList()));
    return jsonCaracteristica;
  }
}
