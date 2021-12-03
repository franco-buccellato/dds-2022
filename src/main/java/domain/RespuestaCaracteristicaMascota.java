package domain;

import java.util.List;
import javax.persistence.Entity;

@Entity(name = "caracteristicas_mascotas")
public class RespuestaCaracteristicaMascota extends RespuestaPregunta {
  public RespuestaCaracteristicaMascota() {
  }

  public RespuestaCaracteristicaMascota(Pregunta pregunta, List<Opcion> selecciones) {
    super(pregunta, selecciones);
  }
}
