package domain;

import static domain.exception.Mensajes.NOT_NULO;

import java.util.Objects;
import javax.mail.MessagingException;

public class Rescatista {
  private DatoPersonal datoPersonal;
  private Contacto contacto;
  private Ubicacion ubicacion;
  private Usuario usuario;

  public Rescatista(
      DatoPersonal datoPersonal, Contacto contacto, Ubicacion ubicacion, Usuario usuario
  ) {
    this.datoPersonal = Objects.requireNonNull(datoPersonal, NOT_NULO.mensaje("datoPersonal"));
    this.contacto = Objects.requireNonNull(contacto, NOT_NULO.mensaje("contacto"));
    this.ubicacion = Objects.requireNonNull(ubicacion, NOT_NULO.mensaje("ubicacion"));
    this.usuario = Objects.requireNonNull(usuario, NOT_NULO.mensaje("usuario"));
  }

  public DatoPersonal datoPersonal() {
    return datoPersonal;
  }

  public Contacto getContacto() {
    return contacto;
  }

  public Ubicacion getUbicacion() {
    return ubicacion;
  }

  public void notificarMatchDuenio(Rescate rescate, Duenio duenio) throws MessagingException {
    // Conpletar mensaje para que use datos del rescate
    String mensaje = "";
    duenio.getContactos().get(0).notificar(mensaje);
  }
}