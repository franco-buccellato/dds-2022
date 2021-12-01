package domain;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import domain.exception.NoSePudoAccederAlTemplate;
import java.io.IOException;

public abstract class TemplateNotificacion<T> {
  private String templateAUtilizar;
  private static final String directorioTemplates = "/email/templates";
  private static final String extensionTemplates = ".html";

  public TemplateNotificacion(String templateAUtilizar) {
    this.templateAUtilizar = templateAUtilizar;
  }

  protected Template obtenerTemplate() {
    TemplateLoader loader = new ClassPathTemplateLoader(directorioTemplates, extensionTemplates);
    Handlebars handlebars = new Handlebars(loader);
    try {
      return handlebars.compile(templateAUtilizar);
    } catch (IOException e) {
      e.printStackTrace();
      throw new NoSePudoAccederAlTemplate(e.getMessage());
    }
  }

  public String aplicarTemplate() {
    try {
      return obtenerTemplate().apply(generarContexto());
    } catch (IOException e) {
      throw new NoSePudoAccederAlTemplate(e.getMessage());
    }
  }

  protected abstract T generarContexto();
}