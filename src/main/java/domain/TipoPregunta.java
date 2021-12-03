package domain;

public enum TipoPregunta {
  TEXT(Values.TEXT),
  CHECKBOX(Values.CHECKBOX),
  BULLET(Values.BULLET),
  NUMBER(Values.NUMBER);

  private final String label;

  TipoPregunta(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

  public static class Values {
    public static final String TEXT = "TEXTO";
    public static final String CHECKBOX = "CHECKBOX";
    public static final String BULLET = "BULLET";
    public static final String NUMBER = "NUMERO";
  }
}
