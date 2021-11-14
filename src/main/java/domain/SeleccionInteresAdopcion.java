package domain;

import java.util.List;
import java.util.Objects;
import javax.persistence.*;

import static domain.exception.Mensajes.NOT_NULO;

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

  @Enumerated(EnumType.STRING)
  @Column(name = "alcance")
  private AlcanceRespuesta alcance;

  public SeleccionInteresAdopcion() {
  }

  public SeleccionInteresAdopcion(Pregunta pregunta, List<Opcion> selecciones, AlcanceRespuesta alcance) {
    super(pregunta, selecciones);
    this.alcance = Objects.requireNonNull(alcance, NOT_NULO.mensaje("alcance"));
  }

  public AlcanceRespuesta getAlcance() {
    return alcance;
  }

  public Boolean tieneMismoAlcance(AlcanceRespuesta alcance) {
    return this.getAlcance().equals(alcance);
  }
}
