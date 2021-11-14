package domain;

import java.util.List;
import javax.persistence.*;

@Entity(name = "selecciones_interes_adopcion")
public class SeleccionInteresAdopcion extends SeleccionPregunta {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "seleccion_interes_adopcion_id")
  private Long id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "pregunta_id")
  private Pregunta pregunta;

  @ManyToMany
  @JoinTable(
      name = "selecciones_interes_adopcion_opciones",
      joinColumns = @JoinColumn(name = "seleccion_interes_adopcion_id"),
      inverseJoinColumns = @JoinColumn(name = "opcion_id")
  )
  private List<Opcion> selecciones;

  public SeleccionInteresAdopcion() {
  }

  public SeleccionInteresAdopcion(Pregunta pregunta, List<Opcion> selecciones) {
    super(pregunta, selecciones);
  }
}
