package domain;

import org.codehaus.jackson.annotate.JsonProperty;

public class AdmisionMascota {
  public AdmisionMascota() {}

  @JsonProperty("perros")
  public boolean getPerros() {
    return this.perros;
  }

  public void setPerros(boolean perros) {
    this.perros = perros;
  }

  boolean perros;

  @JsonProperty("gatos")
  public boolean getGatos() {
    return this.gatos;
  }

  public void setGatos(boolean gatos) {
    this.gatos = gatos;
  }

  boolean gatos;
}
