package domain;

import domain.exception.NoSePudoEnviarMailException;

import static domain.exception.Mensajes.NOT_NULO;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Contactos")
public class Contacto {
  @Id
  @Column(name = "contacto_id")
  @GeneratedValue
  Long id;
  private String nombre;
  private String apellido;
  private String telefono;
  private String mail;
  @Enumerated(EnumType.STRING)
  private Vinculo vinculo;

  public Contacto() {
  }

  public Contacto(
      String nombre,
      String apellido,
      String telefono,
      String mail,
      Vinculo vinculo
  ) {
    this.nombre = Objects.requireNonNull(nombre, NOT_NULO.mensaje("nombre"));
    this.apellido = Objects.requireNonNull(apellido, NOT_NULO.mensaje("apellido"));
    this.telefono = telefono;
    this.mail = mail;
    this.vinculo = vinculo;
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

  public void notificar(Notificacion notificacion) {
    try {
      MedioNotificacionEmail.getInstance().notificar(this, notificacion.getMensaje());
    } catch(NoSePudoEnviarMailException e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public String toString() {
    return "Nombre: " + this.nombre
        + "\nApellido: " + this.apellido
        + "\nTel: " + this.telefono
        + "\nMail: " + this.mail;
  }
}
