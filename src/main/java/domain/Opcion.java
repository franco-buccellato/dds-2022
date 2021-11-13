package domain;

import static domain.exception.Mensajes.NOT_NULO;

import java.util.Objects;
import javax.persistence.*;

@Entity(name = "opciones_nuevas")
public class Opcion {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "opcion_nueva_id")
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

  public String getDescripcion() {
    return descripcion;
  }
}
