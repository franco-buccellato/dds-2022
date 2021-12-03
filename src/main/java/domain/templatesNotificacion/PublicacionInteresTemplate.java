package domain.templatesNotificacion;

import domain.PublicacionAdopcion;
import domain.TemplateNotificacion;
import java.util.List;

public class PublicacionInteresTemplate extends TemplateNotificacion<List<PublicacionAdopcion>> {
  private final List<PublicacionAdopcion> contenido;

  public PublicacionInteresTemplate(String template, List<PublicacionAdopcion> contenido) {
    super(template);
    this.contenido = contenido;
  }

  @Override
  protected List<PublicacionAdopcion> generarContexto() {
    return contenido;
  }

}
