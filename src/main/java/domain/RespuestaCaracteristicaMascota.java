package domain;

import java.util.List;
import javax.persistence.*;

@Entity(name = "caracteristicas_mascotas")
public class RespuestaCaracteristicaMascota extends RespuestaPregunta {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "caracteristica_mascota_id")
  private Long id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "pregunta_id")
  private Pregunta pregunta;

  @ManyToMany
  @JoinTable(
      name = "opciones_caracteristicas_mascotas",
      joinColumns = @JoinColumn(name = "caracteristica_mascota_id"),
      inverseJoinColumns = @JoinColumn(name = "opcion_id")
  )
  private List<Opcion> selecciones;

  public RespuestaCaracteristicaMascota() {
  }

  public RespuestaCaracteristicaMascota(Pregunta pregunta, List<Opcion> selecciones) {
    super(pregunta, selecciones);
  }
}
