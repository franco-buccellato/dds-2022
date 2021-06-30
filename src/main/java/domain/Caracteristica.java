package domain;

import static domain.exception.Mensajes.NOT_NULO;

import java.util.List;
import java.util.Objects;

public abstract class Caracteristica {
  protected TipoCaracteristica tipoCaracteristica;
  protected String descripcion;
  protected Boolean obligatoria;

  public Caracteristica(TipoCaracteristica tipoCaracteristica, String descripcion, Boolean obligatoria) {
    this.tipoCaracteristica = Objects.requireNonNull(tipoCaracteristica, NOT_NULO.mensaje("tipoCaracteristica"));
    this.descripcion = descripcion;
    this.obligatoria = Objects.requireNonNull(obligatoria, NOT_NULO.mensaje("obligatoria"));
  }

  public TipoCaracteristica getTipoCaracteristica() {
    return tipoCaracteristica;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public Boolean getObligatoria() {
    return obligatoria;
  }

  public abstract List getOpciones();

  public abstract List<String> getOpcionesSeleccionas();

  public Boolean tienenMismasOpciones(Caracteristica caracteristica) {
    return caracteristica.getOpcionesSeleccionas().containsAll(this.getOpcionesSeleccionas());
  }
}
