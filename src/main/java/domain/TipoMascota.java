package domain;

public enum TipoMascota {
  PERRO("PERRO"),
  GATO("GATO");

  private String label;

  TipoMascota(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
}
