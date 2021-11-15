package domain;

import java.util.List;
import javax.persistence.*;

@Entity(name = "respuestas_interes_adopcion")
public class RespuestaInteresAdopcion extends RespuestaPregunta {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "respuesta_interes_adopcion_id")
  private Long id;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "pregunta_id")
  private Pregunta pregunta;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinTable(
      name = "opciones_interes_adopcion",
      joinColumns = @JoinColumn(name = "respuesta_interes_adopcion_id"),
      inverseJoinColumns = @JoinColumn(name = "opcion_id")
  )
  private List<Opcion> selecciones;

  public RespuestaInteresAdopcion() {
  }

  public RespuestaInteresAdopcion(Pregunta pregunta, List<Opcion> selecciones) {
    super(pregunta, selecciones);
  }

  public Boolean tieneMismoObjetivo(ObjetivoPregunta objetivo) {
    return this.getPregunta().cumpleObjetivo(objetivo);
  }
}
