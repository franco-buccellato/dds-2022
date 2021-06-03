package domain;

import static domain.exception.Mensajes.NOT_NULO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class Caracteristica {
  protected TipoCaracteristica tipoCaracteristica;
  protected String descripcion;
  protected List<Opcion> opciones;
  protected Boolean obligatoria;

  public Caracteristica(TipoCaracteristica tipoCaracteristica, String descripcion, Boolean obligatoria) {
    this.tipoCaracteristica = Objects.requireNonNull(
        tipoCaracteristica,
        NOT_NULO.mensaje("tipoCaracteristica")
    );
    this.descripcion = descripcion;
    this.opciones = new ArrayList<>();
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

  public List<Opcion> getOpciones() {
    return opciones;
  }
  public List<String> getOpcionesSeleccionas() {
    return opciones
        .stream()
        .filter(Opcion::getSeleccionada)
        .map(Opcion::getDescripcion)
        .collect(Collectors.toList());
  }
}
