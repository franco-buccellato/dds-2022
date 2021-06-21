package domain;

import domain.exception.MascotaSinDuenioException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositorioDuenio {
  private List<Duenio> duenios = new ArrayList<>();
  private static RepositorioDuenio INSTANCE = new RepositorioDuenio();

  public RepositorioDuenio() {
  }

  public static RepositorioDuenio getInstance() {
    return INSTANCE;
  }

  public List<Duenio> getDuenios() {
    return duenios;
  }

  public void addDuenio(Duenio duenio) {
    duenios.add(duenio);
  }

  public void informarMascotaRescatada(Rescate rescate) {
    Optional<Duenio> elDuenio = findDuenioMascota(rescate.getMascota());

    if (!elDuenio.isPresent()) {
      throw new MascotaSinDuenioException("La mascota buscada no tiene duenio");
    }

    elDuenio.ifPresent(duenio -> duenio.notificarMascotaEncontrada(rescate));
  }

  public Optional<Duenio> findDuenioMascota(Mascota mascota) {
    return duenios.stream().filter(duenio -> duenio.getMascotas().contains(mascota)).findFirst();
  }
}

// TODO: Logica no va