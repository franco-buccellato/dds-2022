package domain;

import java.util.Objects;

public class TipoCaracteristicaTextoLibre implements TipoCaracteristica {
  private String denominacion;
  public TipoCaracteristicaTextoLibre ( String denominacion){
    this.denominacion = denominacion;
  }

  @Override
  public boolean validarCriterio (Caracteristica caracteristica) {
    return !Objects.isNull(caracteristica);
  }

  @Override
  public String getDescripcion(){
    return this.denominacion;
  }
}
