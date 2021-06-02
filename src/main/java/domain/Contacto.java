package domain;

public class Contacto {
  private String nombre;
  private String apellido;
  private String telefono;
  private String mail;
  private Vinculo vinculo;

  public Contacto(String nombre, String apellido, String telefono, String mail, Vinculo vinculo) {
    this.nombre = nombre;
    this.apellido = apellido;
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
}
