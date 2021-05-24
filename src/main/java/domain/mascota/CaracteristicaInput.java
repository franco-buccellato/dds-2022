package domain.mascota;

import java.util.Objects;

import static domain.exception.Mensajes.NOT_NULO;

public class CaracteristicaInput implements Caracteristica{
  private TipoCaracteristica tipoCaracteristica;
  private String descripcion;
  private String texto;
  private Boolean obligatoria;

  // Aplica para tipos TEXTO y NUMERO donde los constraints van a ser resueltos en otra capa
  public CaracteristicaInput(TipoCaracteristica tipoCaracteristica, String descripcion, Boolean obligatoria) {
    this.tipoCaracteristica = Objects.requireNonNull(tipoCaracteristica, NOT_NULO.mensaje("tipoCaracteristica"));
    this.descripcion = descripcion;
    this.texto = "";
    this.obligatoria = Objects.requireNonNull(obligatoria, NOT_NULO.mensaje("obligatoria"));
  }
  @Override
  public String getOpciones() {
    return texto;
  }

  @Override
  public Object getSeleccionada() {
    return null;
  }

  @Override
  public TipoCaracteristica getTipoCaracteristica() { return tipoCaracteristica; }

  @Override
  public void addOpcion(Object texto) { this.texto = texto.toString(); }

  public String getDescripcion() { return descripcion; }
  public Boolean getObligatoria() { return obligatoria; }
}
