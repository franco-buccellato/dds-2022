package domain;

import java.util.Objects;

import static domain.exception.Mensajes.NOT_NULO;

public class Asociacion {
  String nombre;
  Ubicacion ubicacion;

  Asociacion(String nombre,Ubicacion ubicacion) {
    this.nombre = Objects.requireNonNull(nombre, NOT_NULO.mensaje("nombre"));
    this.ubicacion = ubicacion;
  }

  String getNombre(){
    return this.nombre;
  }

}
