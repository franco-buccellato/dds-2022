package domain;

import domain.exception.MascotaSinDuenioException;
import domain.repositorios.RepositorioDuenio;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
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
    }
    RepositorioDuenio.getInstance().informarMascotaRescatada(this, elDuenio.get());
  }
}
