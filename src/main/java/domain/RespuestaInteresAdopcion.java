package domain;

import java.util.List;
import javax.persistence.Entity;

@Entity(name = "respuestas_interes_adopcion")
public class RespuestaInteresAdopcion extends RespuestaPregunta {
  public RespuestaInteresAdopcion() {
  }

  public RespuestaInteresAdopcion(Pregunta pregunta, List<Opcion> selecciones) {
    super(pregunta, selecciones);
  }

  public Boolean tieneMismoObjetivo(ObjetivoPregunta objetivo) {
    return this.getPregunta().cumpleObjetivo(objetivo);
  }
}
