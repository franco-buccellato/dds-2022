package domain;

import domain.Caracteristica;

public interface TipoCaracteristica {
  public boolean validarCriterio(Caracteristica caracteristica);
  public String getDescripcion();
}