package domain;

import javax.persistence.*;

import static domain.exception.Mensajes.NOT_NULO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
  @OneToMany
  @JoinColumn(name = "caracteristica_id")
  protected List<Opcion> opciones;
  //protected Boolean obligatoria;
  @Transient
  protected Set<AlcanceCaracteristica> alcanceCaracteristica;

  public Caracteristica(TipoCaracteristica tipoCaracteristica,
      String descripcion, Set<AlcanceCaracteristica> alcanceCaracteristica
  ) {
    this.tipoCaracteristica = Objects.requireNonNull(
        tipoCaracteristica,
        NOT_NULO.mensaje("tipoCaracteristica")
    );
    this.descripcion = descripcion;
    this.opciones = new ArrayList<>();
    this.alcanceCaracteristica = Objects.requireNonNull(
            alcanceCaracteristica, NOT_NULO.mensaje("alcanceCaracteristica"
            ));
  }

  public int getId () {
    return this.id;
  }

  public TipoCaracteristica getTipoCaracteristica() {
    return tipoCaracteristica;
  }

  public String getDescripcion() {
    return descripcion;
  }

  /*
  public Boolean getObligatoria() {
    return obligatoria;
  }
*/

  public Set<AlcanceCaracteristica> getAlcanceCaracteristica() {
    return this.alcanceCaracteristica;
  }

  public abstract List<Opcion> getOpciones();

  public abstract List<String> getOpcionesSeleccionas();

  public abstract Boolean tienenMismasOpciones(Caracteristica caracteristica);
}
