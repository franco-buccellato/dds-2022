package domain.usuario;

public class Usuario {
  private String nombreUsuario;
  private String contrasenia;
  private TipoUsuario tipoUsuario;
  private Persona persona;

  public Usuario(String nombreUsuario, String contrasenia,
                 TipoUsuario tipoUsuario, Persona persona) {

    this.nombreUsuario = nombreUsuario;
    this.contrasenia = contrasenia;
    this.tipoUsuario = tipoUsuario;
    this.persona = persona;
  }

  public String getNombreUsuario() {
    return nombreUsuario;
  }

  public String getContrasenia() {
    return contrasenia;
  }

  public TipoUsuario getTipoUsuario() {
    return tipoUsuario;
  }

  public Persona getPersona() {
    return persona;
  }

}
