package domain.servicio;

public enum Funcion {
  AGREGAR_CARACTERISTICAS("Agregar Caracteristicas");

  private String denominacion;

  Funcion(String denominacion) {
    this.denominacion = denominacion;
  }

  public String getDenominacion() {
    return this.denominacion;
  }
}
