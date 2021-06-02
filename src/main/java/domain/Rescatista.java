package domain;

import static domain.exception.Mensajes.NOT_NULO;

import java.util.Objects;

public class Rescatista {
  private DatoPersonal datoPersonal;
  private Contacto contacto;
  private Ubicacion ubicacion;

  public Rescatista(DatoPersonal datoPersonal, Contacto contacto, Ubicacion ubicacion) {
    this.datoPersonal = Objects.requireNonNull(datoPersonal, NOT_NULO.mensaje("datoPersonal"));
    this.contacto = Objects.requireNonNull(contacto, NOT_NULO.mensaje("contacto"));
    this.ubicacion = Objects.requireNonNull(ubicacion, NOT_NULO.mensaje("ubicacion"));
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

  public void notificarMatchDuenio(Rescate rescate, Duenio duenio) {
    //Comunicar con el dueño mediante sms o mail
  }
}