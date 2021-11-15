package domain;

public class Notificacion {
  TemplateNotificacion templateNotificacion;

  public Notificacion(TemplateNotificacion templateNotificacion) {
    this.templateNotificacion = templateNotificacion;
  }

  public TemplateNotificacion getTemplateNotificacion() {
    return this.templateNotificacion;
  }

  public String getMensaje() {
    return this.getTemplateNotificacion().aplicarTemplate();
  }
}
