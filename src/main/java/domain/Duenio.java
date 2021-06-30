package domain;

import static domain.exception.Mensajes.NOT_NULO;

import domain.repositorios.RepositorioDuenio;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Duenio {
  private DatoPersonal datoPersonal;
  private List<Contacto> contactos;
  private List<Mascota> mascotas;
  private Usuario usuario;

  public Duenio(
      DatoPersonal datoPersonal,
      List<Contacto> contactos,
      List<Mascota> mascotas,
      Usuario usuario
  ) {
    this.datoPersonal = Objects.requireNonNull(datoPersonal, NOT_NULO.mensaje("datoPersonal"));
    this.contactos = Objects.requireNonNull(contactos, NOT_NULO.mensaje("contactos"));
    this.mascotas = Objects.isNull(mascotas) ? new ArrayList<>() : mascotas;
    this.usuario = usuario;

    RepositorioDuenio.getInstance().addDuenio(this);
  }

  //  Getters y setters
  public DatoPersonal getDatoPersonal() {
    return datoPersonal;
  }

  public List<Contacto> getContactos() {
    return contactos;
  }

  public void setContactos(List<Contacto> contactos) {
    this.contactos = contactos;
  }

  public List<Mascota> getMascotas() {
    return mascotas;
  }

  public void setMascotas(List<Mascota> mascotas) {
    this.mascotas = mascotas;
  }

  public void addMascota(Mascota mascota) {
    this.mascotas.add(mascota);
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void notificarMascotaEncontrada(Rescate rescate) {
    contactoTitular().notificar(new Notificacion(new RescatistaContactaDuenioTemplate(rescate)));
  }

  public Contacto contactoTitular() {
    return this.getContactos()
               .stream()
               .filter(contacto -> contacto.getVinculo().equals(Vinculo.TITULAR))
               .findFirst()
               .get();
  }
}
