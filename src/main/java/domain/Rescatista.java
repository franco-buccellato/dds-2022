package domain;

import static domain.exception.Mensajes.NOT_NULO;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Rescatistas")
public class Rescatista {
  @Id
  @Column(name = "rescatista_id")
  @GeneratedValue
  Long id;
  @Embedded
  private DatoPersonal datoPersonal;
  @OneToOne
  @JoinColumn(name = "contacto_id")
  private Contacto contacto;
  @OneToOne
  @JoinColumn(name = "ubicacion_id")
  private Ubicacion ubicacion;
  @OneToOne
  @JoinColumn(name = "usuario_id")
  private Usuario usuario;

  public Rescatista() {
  }

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

  //  public void notificarMatchDuenio(Rescate rescate, Duenio duenio) throws MessagingException {
  //    // Conpletar mensaje para que use datos del rescate
  //    String mensaje = "";
  //    duenio.getContactos().get(0).notificar(mensaje);
  //  }
}