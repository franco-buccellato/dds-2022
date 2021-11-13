package domain;

import static domain.exception.Mensajes.NOT_NULO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

@Entity(name = "caracteristicas")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_caracteristica")
public abstract class Caracteristica {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "caracteristica_id")
  protected int id;

  @Enumerated(EnumType.STRING)
  @Column(name = "tipo_caracteristica")
  protected TipoCaracteristica tipoCaracteristica;

  protected String descripcion;

  @OneToMany
  @JoinColumn(name = "caracteristica_id")
  protected List<Opcion> opciones;

  protected Boolean obligatoria;

  public Caracteristica() {
  }

  public Caracteristica(TipoCaracteristica tipoCaracteristica, String descripcion, Boolean obligatoria) {
    this.tipoCaracteristica = Objects.requireNonNull(
        tipoCaracteristica,
        NOT_NULO.mensaje("tipoCaracteristica")
    );
    this.descripcion = Objects.requireNonNull(descripcion, NOT_NULO.mensaje("descripcion"));
    this.opciones = new ArrayList<>();
    this.obligatoria = Objects.requireNonNull(obligatoria, NOT_NULO.mensaje("obligatoria"));
  }

  public int getId() {
    return this.id;
  }

  public TipoCaracteristica getTipoCaracteristica() {
    return tipoCaracteristica;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public abstract List<Opcion> getOpciones();

  public abstract Boolean esRespuestaValida(String respuesta);

  public abstract Boolean tieneMismasOpciones(Pregunta pregunta);
}
