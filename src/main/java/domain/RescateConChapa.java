package domain;

import java.time.LocalDate;
import java.util.List;

public class RescateConChapa extends Rescate {
  public RescateConChapa(
      List<String> fotos,
      String descripcion,
      Ubicacion lugarEncuentro,
      LocalDate fecha,
      Mascota mascota,
      Rescatista rescatista
  ) {
    super(fotos, descripcion, lugarEncuentro, fecha, mascota, rescatista);
  }

  @Override
  public void informaRescate() {
    RepositorioDuenio.getInstance().informarMascotaRescatada(getMascota());
  }
}
