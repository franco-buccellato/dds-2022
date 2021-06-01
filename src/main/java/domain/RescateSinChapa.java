package domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static domain.exception.Mensajes.NOT_NULO;

public class RescateSinChapa extends Rescate {

  public RescateSinChapa(List<String> fotos, String descripcion, Ubicacion lugarEncuentro, LocalDate fecha, Mascota mascota, Rescatista rescatista) {
    super(
        fotos,
        descripcion,
        lugarEncuentro,
        fecha,
        mascota,
        rescatista
    );
    //mascota.setSituacionMascota(SituacionMascota.EN_HOGAR_TRANSITORIO);
    //mascota.informarRescate(this);
    this.informaRescate();
    this.buscarHogarDeTransito();
  }

  public void informaRescate() {
    new Publicacion(this);
  }

  public void buscarHogarDeTransito() {

  }

  public Boolean encontradaEntre(LocalDate fechaInicio, LocalDate fechaFin) {
    return this.getFecha().isAfter(fechaInicio) && this.getFecha().isBefore(fechaFin);
  }

}
