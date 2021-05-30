package domain;

import static domain.exception.Mensajes.NOT_NULO;

import java.util.Objects;

public abstract class Caracteristica {
  private TipoCaracteristica tipoCaracteristica;
  private String descripcion;
  private Boolean obligatoria;

  public Caracteristica(TipoCaracteristica tipoCaracteristica, String descripcion, Boolean obligatoria) {
    this.tipoCaracteristica = Objects.requireNonNull(tipoCaracteristica, NOT_NULO.mensaje("tipoCaracteristica"));
    this.descripcion = descripcion;
    this.obligatoria = Objects.requireNonNull(obligatoria, NOT_NULO.mensaje("obligatoria"));
  }

  public TipoCaracteristica getTipoCaracteristica(){
    return tipoCaracteristica;
  }

  public String getDescripcion(){
    return descripcion;
  }

  public Boolean getObligatoria() {
    return obligatoria;
  }

  abstract Object getOpciones();
}
