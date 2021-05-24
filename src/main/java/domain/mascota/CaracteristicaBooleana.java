package domain.mascota;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static constants.Mensajes.NOT_NULO;
import static domain.mascota.TipoCaracteristica.BOOLEAN;

public class CaracteristicaBooleana implements Caracteristica{
  private String descripcion;
  private Boolean obligatoria;
  private Boolean seleccionada;

  public CaracteristicaBooleana(String descripcion, Boolean obligatoria) {
    this.descripcion = descripcion;
    this.obligatoria = Objects.requireNonNull(obligatoria, NOT_NULO.mensaje("obligatoria"));
    this.seleccionada = false;
  }
  @Override
  public void addOpcion(Object opcion) {}
  @Override
  public List<Boolean> getOpciones() { return Arrays.asList(true, false); }
  @Override
  public TipoCaracteristica getTipoCaracteristica() { return BOOLEAN; }

  public Boolean getSeleccionada() { return seleccionada; }
  public void setSeleccionada(Boolean seleccionada) { this.seleccionada = seleccionada; }
  public String getDescripcion() { return descripcion; }
  public Boolean getObligatoria() { return obligatoria; }
}
