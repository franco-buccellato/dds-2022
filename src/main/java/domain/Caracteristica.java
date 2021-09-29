package domain;

import static domain.exception.Mensajes.NOT_NULO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Caracteristica {
  protected TipoCaracteristica tipoCaracteristica;
  protected String descripcion;
  protected List<Opcion> opciones;

  public Caracteristica(TipoCaracteristica tipoCaracteristica,
      String descripcion) {
    this.tipoCaracteristica = Objects.requireNonNull(
        tipoCaracteristica,
        NOT_NULO.mensaje("tipoCaracteristica")
    );
    this.descripcion = descripcion;
    this.opciones = new ArrayList<>();
  }

  public Boolean tienenMismasOpciones(Pregunta pregunta) {
    return !this.getOpcionesSeleccionas().isEmpty()
        && pregunta.getOpcionesSeleccionas().containsAll(this.getOpcionesSeleccionas());
  }

  public TipoCaracteristica getTipoCaracteristica() {
    return tipoCaracteristica;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public abstract List<Opcion> getOpciones();

  public abstract List<Opcion> getOpcionesSeleccionas();
}
