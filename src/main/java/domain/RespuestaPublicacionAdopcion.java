package domain;

import java.util.List;
import javax.persistence.Entity;

@Entity(name = "respuestas_publicaciones_adopciones")
public class RespuestaPublicacionAdopcion extends RespuestaPregunta {
  public RespuestaPublicacionAdopcion() {
  }

  public RespuestaPublicacionAdopcion(Pregunta pregunta, List<Opcion> selecciones) {
    super(pregunta, selecciones);
  }


}
