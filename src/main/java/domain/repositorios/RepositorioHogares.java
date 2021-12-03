package domain.repositorios;

import static domain.TipoMascota.GATO;
import static domain.TipoMascota.PERRO;

import domain.*;
import servicio.HogarTransitoServicio;
import servicio.viewmodel.HogarTransitoVM;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RepositorioHogares {
  private List<HogarTransito> hogares;
  private static RepositorioHogares INSTANCE = new RepositorioHogares();

  public static RepositorioHogares getInstance() {
    if(INSTANCE == null) {
      return new RepositorioHogares();
    } else {
      return INSTANCE;
    }
  }

  private List<HogarTransito> hogaresDisponibles() {
    return new HogarTransitoServicio().hogaresDisponibles();
  }

  public void setHogares(List<HogarTransito> hogares) {
    this.hogares = hogares;
  }

  public List<HogarTransito> getHogaresParaRescate(Rescate rescate, double radioBusqueda) {
    Mascota mascota = rescate.getMascota();
    return this.hogaresDisponibles()
        .stream()
        .filter(hayLugar()
                    .and(tipoMascota(mascota))
                    .and(tamanioMascota(mascota))
                    .and(caracteristicasMascota(mascota))
                    .and(distanciaEnRadio(rescate.getLugarEncuentro(), radioBusqueda)))
        .collect(Collectors.toList());
  }

  private Predicate<HogarTransito> hayLugar() {
    return hogarTransito -> (hogarTransito.getLugaresDisponibles() > 0);
  }

  private Predicate<HogarTransito> tamanioMascota(Mascota mascota) {
    return hogarTransito ->
        hogarTransito.aceptaTamanioMascota(mascota.getTamanio());
  }

  private Predicate<HogarTransito> tipoMascota(Mascota mascota) {
    return hogarTransito ->
        (hogarTransito.getAdmisiones().getGatos() && mascota.getTipoMascota().equals(GATO))
        || (hogarTransito.getAdmisiones().getPerros() && mascota.getTipoMascota().equals(PERRO));
  }

  private Predicate<HogarTransito> caracteristicasMascota(Mascota mascota) {
    return hogarTransito -> mascota.getCaracteristicas()
        .stream()
        .allMatch(caracteristica ->
                      hogarTransito.getCaracteristicas().isEmpty()
                      || hogarTransito.getCaracteristicas()
                          .stream()
                          .anyMatch(caracteristica::estaEnRespuestasPosibles)
        );
  }

  private Predicate<HogarTransito> distanciaEnRadio(
      Ubicacion lugarEncuentro, double kilometrosRadioBusqueda
  ) {
    return hogarTransito ->
        hogarTransito.getUbicacion().distanciaA(lugarEncuentro) <= kilometrosRadioBusqueda;
  }

  public List<HogarTransitoVM> getHogaresTransitoVM(List<HogarTransito> hogares) {
    List<HogarTransitoVM> hogaresVM = new ArrayList<>();
    for (HogarTransito hogar : hogares) {
      hogaresVM.add(new HogarTransitoVM(hogar));
    }
    return hogaresVM;
  }
}
