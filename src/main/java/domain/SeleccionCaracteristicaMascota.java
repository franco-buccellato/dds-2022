package domain;

import java.util.List;
import javax.persistence.*;

@Entity(name = "selecciones_caracteristicas_mascotas")
public class SeleccionCaracteristicaMascota extends SeleccionPregunta {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "seleccion_caracteristica_mascota_id")
  private Long id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "pregunta_id")
  private Pregunta pregunta;

  @ManyToMany
  @JoinTable(
      name = "selecciones_caracteristicas_mascotas_opciones",
      joinColumns = @JoinColumn(name = "seleccion_caracteristica_mascota_id"),
      inverseJoinColumns = @JoinColumn(name = "opcion_id")
  )
  private List<Opcion> selecciones;

  public SeleccionCaracteristicaMascota() {
  }

  public SeleccionCaracteristicaMascota(Pregunta pregunta, List<Opcion> selecciones) {
    super(pregunta, selecciones);
  }
}
