package domain;

public class RescatistaContactaDuenioTemplate extends TemplateNotificacion<Rescate> {
  Rescate rescate;

  public RescatistaContactaDuenioTemplate(Rescate rescate) {
    super("contacto_rescatista");
    this.rescate = rescate;
  }

  @Override
  protected Rescate generarContexto() {
    return rescate;
  }
}
