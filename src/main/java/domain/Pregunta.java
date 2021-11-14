package domain;

import static domain.exception.Mensajes.NOT_NULO;

import java.util.List;
import java.util.Objects;
import java.util.Random;

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
  private Long id;

  @ElementCollection(targetClass = ObjetivoPregunta.class, fetch = FetchType.EAGER)
  @CollectionTable(name = "preguntas_objetivos", joinColumns = @JoinColumn(name = "pregunta_id"))
  @Column(name = "objetivo", nullable = false)
  @Enumerated(EnumType.STRING)
  private List<ObjetivoPregunta> objetivos;

  private String descripcion;

  @OneToMany
  @JoinColumn(name = "pregunta_id")
  private List<Opcion> opciones;

  private Boolean obligatoria;

  public Pregunta() {
  }

  public Pregunta(List<ObjetivoPregunta> objetivos, String descripcion, List<Opcion> opciones, Boolean obligatoria) {
    this.id = new Random().nextLong();
    this.objetivos = Objects.requireNonNull(objetivos, NOT_NULO.mensaje("objetivos"));
    this.descripcion = Objects.requireNonNull(descripcion, NOT_NULO.mensaje("descripcion"));
    this.opciones = Objects.requireNonNull(opciones, NOT_NULO.mensaje("opciones"));
    this.obligatoria = Objects.requireNonNull(obligatoria, NOT_NULO.mensaje("obligatoria"));
  }

  public Pregunta(List<ObjetivoPregunta> objetivos, String descripcion, Boolean obligatoria) {
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
    this.descripcion = descripcion;
  }

  public List<Opcion> getOpciones() {
    return opciones;
  }

  public void setOpciones(List<Opcion> opciones) {
    this.opciones = opciones;
  }

  public void addOpcion(Opcion opcion) {
    this.getOpciones().add(opcion);
  }

  public Boolean getObligatoria() {
    return obligatoria;
  }

  public void setObligatoria(Boolean obligatoria) {
    this.obligatoria = obligatoria;
  }

  public abstract Boolean esMismaPregunta(Pregunta pregunta);

  public abstract Boolean sonMismasSelecciones(Opcion seleccion1, Opcion seleccion2);

  public abstract Boolean sonSeleccionesValidas(List<Opcion> selecciones);

  public Boolean cumpleObjetivo(ObjetivoPregunta objetivo) {
    return this.getObjetivos().contains(objetivo);
  }
}
