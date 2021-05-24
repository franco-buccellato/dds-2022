package domain.mascota;

public class Opcion {
  private Object descripcion;
  private Boolean seleccionada;

  public Opcion(String descripcion) {
    this.descripcion = descripcion;
    this.seleccionada = false;
  }

  public Object getDescripcion() {
    return descripcion;
  }

  public Boolean getSeleccionada() {
    return seleccionada;
  }

  public void setSeleccionada(Boolean seleccionada) {
    this.seleccionada = seleccionada;
  }
}
