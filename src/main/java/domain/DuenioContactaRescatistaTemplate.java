package domain;

import java.util.HashMap;

public class DuenioContactaRescatistaTemplate extends TemplateNotificacion<Duenio> {
  private Duenio duenio;
  public DuenioContactaRescatistaTemplate(Duenio duenio) {
    super("contacto_duenio");
    this.duenio = duenio;
  }

  @Override
  protected Duenio generarContexto() {
    return this.duenio;
  }
}
