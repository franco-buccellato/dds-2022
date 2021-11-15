package domain;

import javax.persistence.*;
import java.util.List;

@Entity(name = "respuestas_publicaciones_adopciones")
public class RespuestaPublicacionAdopcion extends RespuestaPregunta {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "respuesta_publicacion_adopcion_id")
  private Long id;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "pregunta_id")
  private Pregunta pregunta;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinTable(
      name = "opciones_adopciones",
      joinColumns = @JoinColumn(name = "respuesta_publicacion_adopcion_id"),
      inverseJoinColumns = @JoinColumn(name = "opcion_id")
  )
  private List<Opcion> selecciones;

  public RespuestaPublicacionAdopcion() {
  }

  public RespuestaPublicacionAdopcion(Pregunta pregunta, List<Opcion> selecciones) {
    super(pregunta, selecciones);
  }


}
