package domain;

import static domain.exception.Mensajes.NOT_NULO;

import domain.repositorios.RepositorioAsociaciones;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "asociaciones")
public class Asociacion {
  @Id
  @Column(name = "asociacion_id")
  @GeneratedValue
  private int id;
  @Column(name = "asociacion_nombre")
  private String nombre;
  @OneToOne
  @JoinColumn(name = "ubicacion_id")
  private Ubicacion ubicacion;
  @Transient
  private List<Caracteristica> preguntasAdopcion;

  public Asociacion(String nombre, Ubicacion ubicacion) {
    this.nombre = Objects.requireNonNull(nombre, NOT_NULO.mensaje("nombre"));
    this.ubicacion = Objects.requireNonNull(ubicacion, NOT_NULO.mensaje("ubicacion"));
    this.preguntasAdopcion = Collections.emptyList();
    RepositorioAsociaciones.getRepositorioAsociaciones().addAsociacion(this);
  }

  public int getId() {
    return this.id;
  }

  public String getNombre() {
    return nombre;
  }

  public Ubicacion getUbicacion() {
    return ubicacion;
  }

  public List<Caracteristica> getPreguntasAdopcion() {
    return preguntasAdopcion;
  }

  public void setPreguntasAdopcion(List<Caracteristica> preguntasAdopcion) {
    this.preguntasAdopcion = preguntasAdopcion;
  }

  public void addPreguntasAdopcion(Caracteristica caracteristica) {
    this.preguntasAdopcion.add(caracteristica);
  }
}
