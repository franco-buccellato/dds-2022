package domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RescateSinChapa extends Rescate {
  List<HogarTransito> hogaresTransito;
  RepositorioHogares repositorioHogares;

  public RescateSinChapa(
      List<String> fotos,
      String descripcion,
      Ubicacion lugarEncuentro,
      LocalDate fecha, Mascota mascota,
      Rescatista rescatista
  ) {
    super(fotos, descripcion, lugarEncuentro, fecha, mascota, rescatista);
    repositorioHogares = new RepositorioHogares(Collections.emptyList());
    this.hogaresTransito = new ArrayList<>();
    this.informaRescate();
  }

  @Override
  public void informaRescate() {
    new Publicacion(this);
  }

  public List<HogarTransito> buscarHogarDeTransito(double radioBusqueda) {
    // TODO: Tech-debt to remove new RepositorioHogares cuando este persistencia
    this.hogaresTransito = repositorioHogares.getHogaresParaRescate(this, radioBusqueda);
    return hogaresTransito;
  }

  public List<HogarTransito> getHogaresTransito() {
    return hogaresTransito;
  }
}

// TODO: instanciar y guardar
// TODO api can be in repo