package domain.usuario;

import domain.servicio.Funcion;
import java.util.Arrays;
import java.util.List;

public enum TipoUsuario {
  ESTANDAR(null),
  ADMINISTRADOR(Arrays.asList(Funcion.AGREGAR_CARACTERISTICAS)
  );

  private List<Funcion> funcionesDisponibles;

  TipoUsuario(List<Funcion> funcionesDisponibles) {
    this.funcionesDisponibles = funcionesDisponibles;
  }

  public List<Funcion> getFuncionesDisponibles() {
    return this.funcionesDisponibles;
  }
}
