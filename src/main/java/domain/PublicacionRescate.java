package domain;

import static domain.exception.Mensajes.NOT_NULO;

import domain.repositorios.RepositorioPublicacionesRescate;
import domain.templatesNotificacion.DuenioContactaRescatistaTemplate;

import domain.repositorios.RepositorioAsociaciones;

import java.util.Objects;

public class PublicacionRescate {
  private Rescate rescate;
  private EstadoPublicacion estado;
  private Asociacion asociacion;

  public PublicacionRescate(Rescate rescate) {
    this.rescate = Objects.requireNonNull(rescate, NOT_NULO.mensaje("rescate"));
    this.estado = EstadoPublicacion.ESPERA;
    this.asociacion = this.buscarAsociacionCercana();
    RepositorioPublicacionesRescate.getRepositorioPublicaciones().agregarPublicacion(this);
  }

  public Rescate getRescate() {
    return rescate;
  }

  public EstadoPublicacion getEstado() {
    return this.estado;
  }

  public Asociacion getAsociacion() {
    return asociacion;
  }

  public void aprobar() {
    this.estado = EstadoPublicacion.ACEPTADA;
  }

  public void rechazar() {
    this.estado = EstadoPublicacion.RECHAZADA;
  }

  public Asociacion buscarAsociacionCercana() {
    return asociacion = RepositorioAsociaciones
        .getRepositorioAsociaciones()
        .encontrarMasCercana(rescate.getRescatista().getUbicacion());
  }

  // TODO asociar a la creacion
  public void notificarRescatista(Duenio duenio) {
    this.getRescate().getRescatista().getContacto().notificar(new Notificacion(new DuenioContactaRescatistaTemplate(duenio)));
    //        new StringBuilder()
    //            .append("<h1>El due&ntilde;io de la mascota ")
    //            .append("quiere contactar contigo</h1>\n")
    //            .append("<br>\n")
    //            .append("<p>El due&ntilde;io de la mascota ha visto ")
    //            .append("la publicaci&oacute;n y quere contactar contigo<br>\n")
    //            .append("Estos son sus datos de contacto:</p><br>")
    //            .append(duenio.contactoTitular().toString()   ).toString());
  }
}
