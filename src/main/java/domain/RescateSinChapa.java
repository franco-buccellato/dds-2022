package domain;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import servicio.HogarTransitoServicio;

public class RescateSinChapa extends Rescate {
  List<HogarTransito> hogaresTransito;

  public RescateSinChapa(
      List<String> fotos,
      String descripcion,
      Ubicacion lugarEncuentro,
      LocalDate fecha, Mascota mascota,
      Rescatista rescatista
  ) {
    super(fotos, descripcion, lugarEncuentro, fecha, mascota, rescatista);
    this.hogaresTransito = new ArrayList<>();
  }

  @Override
  public void informaRescate() {
    new Publicacion(this);
  }

  public List<HogarTransito> buscarHogarDeTransito(double radioBusqueda) throws NoSuchAlgorithmException, KeyManagementException {
    // TODO: Tech-debt to remove new RepositorioHogares cuando este persistencia
    hogaresTransito = new RepositorioHogares(new HogarTransitoServicio().hogaresDisponibles()).getHogaresParaRescate(this, radioBusqueda);
    return hogaresTransito;
  }

  public List<HogarTransito> getHogaresTransito() {
    return hogaresTransito;
  }
}
