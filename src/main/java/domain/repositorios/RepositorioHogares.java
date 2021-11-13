package domain.repositorios;

import static domain.TipoMascota.GATO;
import static domain.TipoMascota.PERRO;

import domain.*;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RepositorioHogares {
  private List<HogarTransito> hogares;

  public RepositorioHogares(List<HogarTransito> hogares) {
    this.hogares = hogares;
  }

  public List<HogarTransito> getHogaresParaRescate(Rescate rescate, double radioBusqueda) {
    Mascota mascota = rescate.getMascota();
    return hogares
        .stream()
        .filter(hayLugar()
            .and(tipoMascota(mascota))
//            .and(tamanioMascota(mascota))
//            .and(caracteristicasMascota(mascota))
            .and(distanciaEnRadio(rescate.getLugarEncuentro(), radioBusqueda)))
        .collect(Collectors.toList());
  }

  private Predicate<HogarTransito> hayLugar() {
    return hogarTransito ->  (hogarTransito.getLugaresDisponibles() > 0);
  }

//  private Predicate<HogarTransito> tamanioMascota(Mascota mascota) {
//   return hogarTransito ->
//        hogarTransito.aceptaTamanioMascota(mascota.getTamanio());
//  }

  private Predicate<HogarTransito> tipoMascota(Mascota mascota) {
    return hogarTransito ->
        (hogarTransito.getAdmisiones().getGatos() && mascota.getTipoMascota().equals(GATO))
      || (hogarTransito.getAdmisiones().getPerros() && mascota.getTipoMascota().equals(PERRO));
  }

//  private Predicate<HogarTransito> caracteristicasMascota(Mascota mascota) {
//    return hogarTransito -> mascota.getCaracteristicasSeleccionadas()
//        .stream()
//        .allMatch(caracteristica ->
//            hogarTransito.getCaracteristicas().isEmpty() || hogarTransito.getCaracteristicas()
//                .stream()
//                .anyMatch(caracteristica::tienenMismasOpciones)
//        );
//  }

  private Predicate<HogarTransito> distanciaEnRadio(
          Ubicacion lugarEncuentro, double kilometrosRadioBusqueda
  ) {
    return hogarTransito ->
        hogarTransito.getUbicacion().distanciaA(lugarEncuentro) <= kilometrosRadioBusqueda;
  }
}
