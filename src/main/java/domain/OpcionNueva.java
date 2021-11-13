package domain;

import javax.persistence.*;

@Entity(name = "opciones_nuevas")
public class OpcionNueva {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "opcion_nueva_id")
  private Long id;

  private String descripcion;

  public OpcionNueva() {
  }

  public OpcionNueva(String descripcion) {
    this.descripcion = descripcion;
  }

  public Long getId() {
    return this.id;
  }

  public String getDescripcion() {
    return descripcion;
  }
}
