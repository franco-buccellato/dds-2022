package domain;


import java.util.List;

public class CaracteristicaChoice extends Caracteristica{
  private List<Opcion> opciones;

  // Aplica para tipos CHECKBOX y BULLET donde los constraints van a ser resueltos en otra capa
  public CaracteristicaChoice(TipoCaracteristica tipoCaracteristica, String descripcion, List<Opcion> opciones, Boolean obligatoria) {
    super(tipoCaracteristica, descripcion, obligatoria);
    this.opciones = opciones;
  }

  @Override
  public List<Opcion> getOpciones() {
    return opciones;
  }

  public void removeOpciones(List<Opcion> opciones) {
    this.opciones.removeAll(opciones);
  }
  public void addOpciones(List<Opcion> opciones) {
    this.opciones.addAll(opciones);
  }
}
