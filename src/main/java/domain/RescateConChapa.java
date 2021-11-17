package domain;

import domain.exception.MascotaSinDuenioException;
import domain.repositorios.RepositorioDuenio;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Objects;
import javax.persistence.*;

@Entity
@DiscriminatorValue("C")
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
    this.informaRescate();
  }

  @Override
  public void informaRescate() {
    Optional<Duenio> elDuenio = RepositorioDuenio.getInstance().findDuenioMascota(this.getMascota());
    if (!elDuenio.isPresent()) {
      throw new MascotaSinDuenioException("La mascota buscada no tiene duenio");
    } else {
      elDuenio.get().notificarMascotaEncontrada(this);
    }
  }
}
