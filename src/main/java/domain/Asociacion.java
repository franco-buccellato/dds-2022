package domain;

import static domain.exception.Mensajes.NOT_NULO;

import domain.repositorios.RepositorioAsociaciones;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "asociaciones")
public class Asociacion {
  @Id
  @Column(name = "asociacion_id")
  @GeneratedValue
  private Long id;

  @Column(name = "asociacion_nombre")
  private String nombre;

  @OneToOne
  @JoinColumn(name = "ubicacion_id")
  private Ubicacion ubicacion;

  @ManyToMany
  @JoinTable(
      name = "preguntas_adopcion",
      joinColumns = @JoinColumn(name = "asociacion_id"),
      inverseJoinColumns = @JoinColumn(name = "pregunta_id")
  )
  private List<Pregunta> preguntasAdopcion;

  public Asociacion() {
  }

  public Asociacion(String nombre, Ubicacion ubicacion) {
    this.nombre = Objects.requireNonNull(nombre, NOT_NULO.mensaje("nombre"));
    this.ubicacion = Objects.requireNonNull(ubicacion, NOT_NULO.mensaje("ubicacion"));
    this.preguntasAdopcion = Collections.emptyList();
    RepositorioAsociaciones.getRepositorioAsociaciones().addAsociacion(this);
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public Ubicacion getUbicacion() {
    return ubicacion;
  }

  public List<Pregunta> getPreguntasAdopcion() {
    return preguntasAdopcion;
  }

  public void setPreguntasAdopcion(List<Pregunta> preguntasAdopcion) {
    this.preguntasAdopcion = preguntasAdopcion;
  }

  public void addPreguntaAdopcion(Pregunta pregunta) {
    this.preguntasAdopcion.add(pregunta);
  }
}
