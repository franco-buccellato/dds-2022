package domain.mascota;

import java.util.List;
import java.util.Objects;

import static constants.Mensajes.NOT_NULO;

public class CaracteristicaChoice implements Caracteristica{
  private TipoCaracteristica tipoCaracteristica;
  private String descripcion;
  private Boolean obligatoria;
  private List<Opcion> opciones;

  // Aplica para tipos CHECKBOX y BULLET donde los constraints van a ser resueltos en otra capa
  public CaracteristicaChoice(TipoCaracteristica tipoCaracteristica, String descripcion, List<Opcion> opciones, Boolean obligatoria) {
    this.tipoCaracteristica = Objects.requireNonNull(tipoCaracteristica, NOT_NULO.mensaje("tipoCaracteristica"));
    this.descripcion = descripcion;
    this.opciones = opciones;
    this.obligatoria = Objects.requireNonNull(obligatoria, NOT_NULO.mensaje("obligatoria"));
  }
  @Override
  public List<Opcion> getOpciones() {
    return opciones;
  }

  @Override
  public TipoCaracteristica getTipoCaracteristica() { return tipoCaracteristica; }

  @Override
  public void addOpcion(Object opcion) { this.opciones.add((Opcion) opcion); }

  public void addOpciones(Opcion opcion) {
    opciones.add(opcion);
  }
  public void removeOpciones(Opcion opcion) {
    opciones.remove(opcion);
  }
  public String getDescripcion() { return descripcion; }
  public Boolean getObligatoria() { return obligatoria; }
}
