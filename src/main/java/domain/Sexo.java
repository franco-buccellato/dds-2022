package domain;

public enum Sexo {
  MACHO("MACHO"),
  HEMBRA("HEMBRA");

  private String label;

  Sexo(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
}
