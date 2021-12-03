package domain;

import domain.exception.MascotaSinDuenioException;
import domain.repositorios.RepositorioDuenio;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("C")
public class RescateConChapa extends Rescate {

  public RescateConChapa() {
  }

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
    Duenio elDuenio = RepositorioDuenio.getInstance()
        .getDuenioByIdMascota(this.getMascota().getId());
    if (elDuenio == null) {
      throw new MascotaSinDuenioException("La mascota buscada no tiene duenio");
    } else {
      elDuenio.notificarMascotaEncontrada(this);
    }
  }
}
