package domain;

import domain.exception.MascotaSinDuenioException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
    Optional<Duenio> elDuenio = RepositorioDuenio.getInstance().findDuenioMascota(this.getMascota());
    if (!elDuenio.isPresent()) {
      throw new MascotaSinDuenioException("La mascota buscada no tiene duenio");
    }
    RepositorioDuenio.getInstance().informarMascotaRescatada(this, elDuenio.get());
  }
}
