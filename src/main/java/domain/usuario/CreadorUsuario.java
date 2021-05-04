package domain.usuario;

import domain.servicio.impl.ValidadorClave;

public class CreadorUsuario {

  public CreadorUsuario() {
  }

  private ValidadorClave validadorClave = new ValidadorClave();

  public Usuario nuevoUsuario(String usuario, String clave,
                              TipoUsuario tipoUsuario, Persona persona) {

    validadorClave.validarClave(usuario, clave);

    return new Usuario(usuario, clave, tipoUsuario, persona);
  }
}
