package domain;

public class Opcion {
  private String descripcion;
  private Boolean seleccionada;

  public Opcion(String descripcion) {
    this.descripcion = descripcion;
    this.seleccionada = false;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public Boolean getSeleccionada() {
    return seleccionada;
  }

  public void setSeleccionada(Boolean seleccionada) {
    this.seleccionada = seleccionada;
  }
}
