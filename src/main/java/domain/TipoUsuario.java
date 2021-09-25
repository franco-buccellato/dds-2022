package domain;

import java.util.Arrays;
import java.util.List;
import servicio.Funcion;

import javax.persistence.Enumerated;

public enum TipoUsuario {
  ESTANDAR(null),
  VOLUNTARIO(Arrays.asList(Funcion.APROBAR_PUBLICACION)),
  ADMINISTRADOR(Arrays.asList(Funcion.AGREGAR_CARACTERISTICAS));

  private List<Funcion> funcionesDisponibles;

  TipoUsuario(List<Funcion> funcionesDisponibles) {
    this.funcionesDisponibles = funcionesDisponibles;
  }

  public List<Funcion> getFuncionesDisponibles() {
    return this.funcionesDisponibles;
  }
}
