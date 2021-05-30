package domain;

import java.util.List;

public class TipoCaracteristicaSeleccionMultiple implements TipoCaracteristica{
  private String denominacion;
  private List<Caracteristica> caracteristicasAdmitidas;
  public TipoCaracteristicaSeleccionMultiple(String denominacion, List<Caracteristica> caracteristicasAdmitidas){
    this.denominacion = denominacion;
    this.caracteristicasAdmitidas = caracteristicasAdmitidas;
  }

  @Override
  public boolean validarCriterio(Caracteristica caracteristica) {
    return caracteristicasAdmitidas.stream().anyMatch(caracteristica::equals);
  }

  @Override
  public String getDescripcion() {
    return this.denominacion;
  }
}