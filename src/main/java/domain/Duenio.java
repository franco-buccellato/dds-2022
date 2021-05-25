package domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static domain.exception.Mensajes.NOT_NULO;

public class Duenio {
  private String nombre;
  private String apellido;
  private TipoIdentificacion tipoIdentificacion;
  private String nroIdentificacion;
  private List<Contacto> contactos;
  private List<Mascota> mascotas;
  private LocalDate fechaNacimiento;
  private Usuario usuario;

  public Duenio(String nombre, String apellido, TipoIdentificacion tipoIdentificacion, String nroIdentificacion, List<Contacto> contactos, List<Mascota> mascotas, LocalDate fechaNacimiento, Usuario usaurio) {
    this.nombre = Objects.requireNonNull(nombre, NOT_NULO.mensaje("nombre"));
    this.apellido = Objects.requireNonNull(apellido, NOT_NULO.mensaje("apellido"));
    this.tipoIdentificacion = Objects.requireNonNull(tipoIdentificacion, NOT_NULO.mensaje("tipoIdentificacion"));
    this.nroIdentificacion = Objects.requireNonNull(nroIdentificacion, NOT_NULO.mensaje("nroIdentificacion"));
    this.contactos = contactos;
    this.mascotas = mascotas;
    this.fechaNacimiento = Objects.requireNonNull(fechaNacimiento, NOT_NULO.mensaje("fechaNacimiento"));
    this.usuario = usuario;
  }

  //  Getters y setters
  public String getNombre() {
    return nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public TipoIdentificacion getTipoIdentificacion() {
    return tipoIdentificacion;
  }

  public String getNroIdentificacion() {
    return nroIdentificacion;
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

  public LocalDate getFechaNacimiento() {
    return fechaNacimiento;
  }

  public Usuario getUsuario() {
    return usuario;
  }
}
