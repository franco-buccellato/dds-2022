package domain;

import static domain.exception.Mensajes.NOT_NULO;

import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name = "preguntas")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_input")
public abstract class Pregunta {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "pregunta_id")
  protected Long id;

  @Column(name = "tipo", nullable = false)
  @Enumerated(EnumType.STRING)
  protected TipoPregunta tipoPregunta;

  @ElementCollection(targetClass = ObjetivoPregunta.class, fetch = FetchType.EAGER)
  @CollectionTable(name = "preguntas_objetivos", joinColumns = @JoinColumn(name = "pregunta_id"))
  @Column(name = "objetivo", nullable = false)
  @Enumerated(EnumType.STRING)
  protected List<ObjetivoPregunta> objetivos;

  protected String descripcion;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "pregunta_id")
  protected List<Opcion> opciones;

  protected Boolean obligatoria;

  public Pregunta() {
  }

  public Pregunta(TipoPregunta tipoPregunta, List<ObjetivoPregunta> objetivos, String descripcion, Boolean obligatoria) {
    this.tipoPregunta = Objects.requireNonNull(tipoPregunta, NOT_NULO.mensaje("tipoPregunta"));
    this.objetivos = Objects.requireNonNull(objetivos, NOT_NULO.mensaje("objetivos"));
    this.descripcion = Objects.requireNonNull(descripcion, NOT_NULO.mensaje("descripcion"));
    this.obligatoria = Objects.requireNonNull(obligatoria, NOT_NULO.mensaje("obligatoria"));
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public TipoPregunta getTipoPregunta() {
    return this.tipoPregunta;
  }

  public List<ObjetivoPregunta> getObjetivos() {
    return objetivos;
  }

  public void addObjetivo(ObjetivoPregunta objetivo) {
    this.getObjetivos().add(objetivo);
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = Objects.requireNonNull(descripcion, NOT_NULO.mensaje("descripcion"));
  }

  public List<Opcion> getOpciones() {
    return opciones;
  }

  public void setOpciones(List<Opcion> opciones) {
    if (this.tieneOpcionesSeteables()) {
      this.opciones = Objects.requireNonNull(opciones, NOT_NULO.mensaje("opciones"));
    }
  }

  public void addOpcion(Opcion opcion) {
    this.getOpciones().add(opcion);
  }

  public Boolean getObligatoria() {
    return obligatoria;
  }

  public void setObligatoria(Boolean obligatoria) {
    this.obligatoria = Objects.requireNonNull(obligatoria, NOT_NULO.mensaje("obligatoria"));
  }

  public Boolean esMismaPregunta(Pregunta pregunta) {
    return this.getId().equals(pregunta.getId());
  }

  public abstract Boolean sonMismasSelecciones(Opcion seleccion1, Opcion seleccion2);

  public abstract Boolean sonSeleccionesValidas(List<Opcion> selecciones);

  public Boolean cumpleObjetivo(ObjetivoPregunta objetivo) {
    return this.getObjetivos().contains(objetivo);
  }

  public Boolean incluyeAlgunaSeleccion(List<Opcion> selecciones) {
    return this.getOpciones().stream().anyMatch(
        opcion -> selecciones.stream().anyMatch(
            seleccion -> seleccion.esMismaOpcion(opcion)
        )
    );
  }

  public Boolean tieneOpcionesSeteables() {
    return this.getTipoPregunta().equals(TipoPregunta.BULLET)
           || this.getTipoPregunta().equals(TipoPregunta.CHECKBOX);
  }
}
