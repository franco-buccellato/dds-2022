package domain;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.json.simple.JSONObject;

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
    return this.equals(opcion)
           || this.getId().equals(opcion.getId())
           || this.getDescripcion().equals(opcion.getDescripcion());
  }

  public Boolean esOpcionTamanio() {
    List<String> tamanios = Arrays.asList("Chico", "Mediano", "Grande");

    return tamanios.stream().anyMatch(tamanio -> tamanio.equals(this.getDescripcion()));
  }

  public JSONObject toJson() {
    JSONObject jsonOpcion = new JSONObject();
    jsonOpcion.put("descripcion", this.descripcion);
    jsonOpcion.put("seleccionada", this.seleccionada.toString());
    return jsonOpcion;
  }
}