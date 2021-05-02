package domain.usuario;

import constants.TipoIdentificacion;
import domain.mascota.Mascota;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static constants.Mensajes.NOT_NULO;

public class Persona {
  private String nombre;
  private String apellido;
  private TipoIdentificacion tipoIdentificacion;
  private String nroIdentificacion;
  private Contacto contacto;
  private List<Mascota> mascotas;
  private LocalDate fechaNacimiento;

  public Persona(String nombre, String apellido, TipoIdentificacion tipoIdentificacion, String nroIdentificacion, Contacto contacto, List<Mascota> mascotas, LocalDate fechaNacimiento) {
    this.nombre = Objects.requireNonNull(nombre, NOT_NULO.mensaje("nombre"));
    this.apellido =  Objects.requireNonNull(apellido, NOT_NULO.mensaje("apellido"));
    this.tipoIdentificacion =  Objects.requireNonNull(tipoIdentificacion, NOT_NULO.mensaje("tipoIdentificacion"));
    this.nroIdentificacion =  Objects.requireNonNull(nroIdentificacion, NOT_NULO.mensaje("nroIdentificacion"));
    this.contacto = contacto;
    this.mascotas = mascotas;
    this.fechaNacimiento =  Objects.requireNonNull(fechaNacimiento, NOT_NULO.mensaje("fechaNacimiento"));
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
  public Contacto getContacto() {
    return contacto;
  }
  public void setContacto(Contacto contacto) {
    this.contacto = contacto;
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
}
