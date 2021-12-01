package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import servicio.impl.ValidadorClave;

@Entity
@Table(name = "usuarios")
public class Usuario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "usuario_id")
  Long id;

  @Column(name = "nombre_usuario")
  private String nombreUsuario;
  private String contrasenia;

  @Enumerated(EnumType.STRING)
  @Column(name = "tipo_usuario")
  private TipoUsuario tipoUsuario;

  @Transient
  private ValidadorClave validadorClave = new ValidadorClave();

  public Usuario() {
  }

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

  public Long getId() {
    return id;
  }
}
