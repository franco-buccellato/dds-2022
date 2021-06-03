package domain;

import java.time.LocalDate;
import java.util.List;

public class RescateSinChapa extends Rescate {

  public RescateSinChapa(
      List<String> fotos,
      String descripcion,
      Ubicacion lugarEncuentro,
      LocalDate fecha, Mascota mascota,
      Rescatista rescatista
  ) {
    super(fotos, descripcion, lugarEncuentro, fecha, mascota, rescatista);
  }

  @Override
  public void informaRescate() {
    new Publicacion(this);
  }

  public void buscarHogarDeTransito() {

  }
}
