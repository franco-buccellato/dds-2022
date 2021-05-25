package domain;

public class Usuario {
  private String nombreUsuario;
  private String contrasenia;
  private TipoUsuario tipoUsuario;

  public Usuario(String nombreUsuario, String contrasenia, TipoUsuario tipoUsuario) {
    this.nombreUsuario = nombreUsuario;
    this.contrasenia = contrasenia;
    this.tipoUsuario = tipoUsuario;
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
}
