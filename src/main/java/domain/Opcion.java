package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "opciones")
public class Opcion {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String descripcion;
  private Boolean seleccionada;

  public Opcion(String descripcion) {
    this.descripcion = descripcion;
    this.seleccionada = false;
  }

  public int getId() {
    return this.id;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public Boolean getSeleccionada() {
    return seleccionada;
  }

  public void setSeleccionada(Boolean seleccionada) {
    this.seleccionada = seleccionada;
  }
}
