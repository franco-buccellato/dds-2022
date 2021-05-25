package domain;

import servicio.impl.ValidadorClave;

public class Usuario {
  private String nombreUsuario;
  private String contrasenia;
  private TipoUsuario tipoUsuario;
  private ValidadorClave validadorClave = new ValidadorClave();

  public Usuario(String nombreUsuario, String contrasenia, TipoUsuario tipoUsuario) {
    validadorClave.validarClave(nombreUsuario, contrasenia);
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
