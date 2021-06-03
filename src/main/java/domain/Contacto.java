package domain;

import javax.mail.MessagingException;
import java.util.Objects;

import static domain.exception.Mensajes.NOT_NULO;

public class Contacto {
  private String nombre;
  private String apellido;
  private String telefono;
  private String mail;
  private Vinculo vinculo;
  private MedioNotificacion medioNotificacion;

  public Contacto(String nombre, String apellido, String telefono, String mail, Vinculo vinculo, MedioNotificacion medioNotificacion) {
    this.nombre = Objects.requireNonNull(nombre, NOT_NULO.mensaje("nombre"));
    this.apellido = Objects.requireNonNull(apellido, NOT_NULO.mensaje("apellido"));
    this.telefono = telefono;
    this.mail = mail;
    this.vinculo = vinculo;
    this.medioNotificacion = medioNotificacion;
  }

  //  Getters y setters
  public String getNombre() {
    return nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getMail() {
    return mail;
  }

  public void setMail(String mail) {
    this.mail = mail;
  }

  public Vinculo getVinculo() {
    return vinculo;
  }
  public void setMedioNotificacion(MedioNotificacion medioNotificacion) {
    this.medioNotificacion = medioNotificacion;
  }
  public void notificar(String mensage) throws MessagingException {
    medioNotificacion.notificar(this, mensage);
  }
}
