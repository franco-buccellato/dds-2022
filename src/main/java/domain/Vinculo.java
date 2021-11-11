package domain;

public enum Vinculo {
  TITULAR("TITULAR"),
  FAMILIAR("FAMILIAR"),
  AMISTAD("AMISTAD");

  private String label;

  Vinculo(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
}
