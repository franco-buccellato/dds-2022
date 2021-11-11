package domain;

public enum TipoIdentificacion {
  DNI("DNI"),
  LE("LE"),
  CEDULA("CEDULA");

  private String label;

  TipoIdentificacion(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
}
