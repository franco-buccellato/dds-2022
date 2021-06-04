package domain;

public class Notificacion {
  TemplateNotificacion templateNotificacion;

  public Notificacion(TemplateNotificacion templateNotificacion) {
    this.templateNotificacion = templateNotificacion;
  }

  public String getMensaje() {
    return templateNotificacion.aplicarTemplate();
  }
}
