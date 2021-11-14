package domain;

import static domain.exception.Mensajes.NOT_NULO;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

@Entity(name = "opciones")
public class Opcion {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "opcion_id")
  private Long id;
  private String descripcion;

  public Opcion() {
  }

  public Opcion(String descripcion) {
    this.descripcion = Objects.requireNonNull(descripcion, NOT_NULO.mensaje("descripcion"));
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public Boolean esMismaOpcion(Opcion opcion) {
    return this.getId().equals(opcion.getId());
  }

  public Boolean esOpcionTamanio() {
    List<String> tamanios = Arrays.asList("Chico", "Mediano", "Grande");

    return tamanios.stream().anyMatch(tamanio -> tamanio.equals(this.getDescripcion()));
  }
}
