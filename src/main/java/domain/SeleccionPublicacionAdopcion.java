package domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

import static domain.exception.Mensajes.NOT_NULO;

@Entity(name = "selecciones_publicaciones_adopciones")
public class SeleccionPublicacionAdopcion extends SeleccionPregunta {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "seleccion_publicacion_adopcion_id")
  private Long id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "pregunta_id")
  private Pregunta pregunta;

  @ManyToMany
  @JoinTable(
      name = "selecciones_adopciones_opciones",
      joinColumns = @JoinColumn(name = "seleccion_publicacion_adopcion_id"),
      inverseJoinColumns = @JoinColumn(name = "opcion_id")
  )
  private List<Opcion> selecciones;

  public SeleccionPublicacionAdopcion() {
  }

  public SeleccionPublicacionAdopcion(Pregunta pregunta, List<Opcion> selecciones) {
    super(pregunta, selecciones);
  }


}
