package domain;

import static domain.exception.Mensajes.NOT_NULO;

import java.util.Objects;
import java.util.UUID;

public class PublicacionAdopcion {
  private final UUID idPublicacion;
  private Duenio duenio;
  private Mascota mascota;
  private Boolean estaActiva;
  private Asociacion asociacion;

  public PublicacionAdopcion(Duenio duenio, Mascota mascota, Asociacion asociacion) {
    this.idPublicacion = UUID.randomUUID();
    this.duenio = Objects.requireNonNull(duenio, NOT_NULO.mensaje("duenio"));
    this.mascota = Objects.requireNonNull(mascota, NOT_NULO.mensaje("mascota"));
    this.asociacion = asociacion;
  }

  public Mascota getMoscota() {
    return this.mascota;
  }

  public void anularPublicacion() {
    this.estaActiva = false;
  }

  public Boolean getEstaActiva() {
    return estaActiva;
  }
}
