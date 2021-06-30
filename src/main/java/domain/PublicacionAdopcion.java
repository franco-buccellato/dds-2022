package domain;

import java.util.List;

public class PublicacionAdopcion {
  private Mascota mascotaEnAdopcion;
  private Duenio duenioMascotaEnAdopcion;
  private Asociacion asociacionVinculada;
  private List<Caracteristica> caracteristicasAdopcion;
  private Boolean estaActiva;

  public PublicacionAdopcion(Mascota mascotaEnAdopcion, Duenio duenioMascotaEnAdopcion, Asociacion asociacionVinculada, List<Caracteristica> caracteristicasAdopcion) {
    this.mascotaEnAdopcion = mascotaEnAdopcion;
    this.duenioMascotaEnAdopcion = duenioMascotaEnAdopcion;
    this.asociacionVinculada = asociacionVinculada;
    this.caracteristicasAdopcion = caracteristicasAdopcion;
    this.estaActiva = Boolean.TRUE;
  }

  public void notificarInteresDe(Duenio adoptante) {
    this.duenioMascotaEnAdopcion.contactoTitular().notificar(new Notificacion(new InteresadoEnAdoptarTemplate(adoptante)));
  }

  public Mascota getMascota() {
    return this.mascotaEnAdopcion;
  }

  public List<Caracteristica> getCaracteristicasAdopcion() {
    return this.caracteristicasAdopcion;
  }

  public void finalizarPublicacion() {
    this.estaActiva = Boolean.FALSE;
  }

  public Boolean estaActiva() {
    return this.estaActiva;
  }
}
