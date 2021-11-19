package domain;

import static domain.exception.Mensajes.NOT_NULO;

import domain.repositorios.RepositorioDuenio;
import domain.templatesNotificacion.PublicacionInteresTemplate;
import domain.templatesNotificacion.RescatistaContactaDuenioTemplate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "duenios")
public class Duenio {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "duenio_id")
  private Long id;

  @Embedded
  private DatoPersonal datoPersonal;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "duenio_id")
  private List<Contacto> contactos;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "duenio_id")
  private List<Mascota> mascotas;

  @OneToOne
  @JoinColumn(name = "usuario_id")
  private Usuario usuario;

  @Transient
  private List<PublicacionAdopcion> ultimasPublicacionesInteres;

  public Duenio() {
  }

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
    this.ultimasPublicacionesInteres = Collections.emptyList();
    RepositorioDuenio.getInstance().addDuenio(this);
  }

  //  Getters y setters
  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  public List<PublicacionAdopcion> getUltimasPublicacionesInteres() {
    return ultimasPublicacionesInteres;
  }

  public void notificarPublicacionesDeInteres(List<PublicacionAdopcion> publicaciones) {
    // Para testing
    this.ultimasPublicacionesInteres = publicaciones;

    PublicacionInteresTemplate template = publicaciones.isEmpty()
        ? new PublicacionInteresTemplate("lista_interes", publicaciones)
        : new PublicacionInteresTemplate("lista_interes_vacia", null);
    contactoTitular().notificar(new Notificacion(template));
  }

  public Contacto contactoTitular() {
    return this.getContactos()
        .stream()
        .filter(contacto -> contacto.getVinculo().equals(Vinculo.TITULAR))
        .findFirst()
        .get();
  }
}
