package domain;

import domain.exception.TipoPreguntaInexistenteException;

import java.util.List;

public class TipoPreguntaFactory {
  public TipoPreguntaFactory() {
  }

  public static Pregunta makePregunta(
      TipoPregunta tipoPregunta,
      List<ObjetivoPregunta> objetivos,
      String descripcion,
      Boolean obligatoria,
      List<Opcion> opciones
  ) throws TipoPreguntaInexistenteException {
    Pregunta pregunta;

    switch (tipoPregunta) {
      case NUMBER: {
        pregunta = new PreguntaNumber(objetivos, descripcion, obligatoria);
        break;
      }

      case TEXT: {
        pregunta = new PreguntaText(objetivos, descripcion, obligatoria);
        break;
      }

      case BULLET: {
        pregunta = new PreguntaBullet(objetivos, descripcion, opciones, obligatoria);
        break;
      }

      case CHECKBOX: {
        pregunta = new PreguntaCheckBox(objetivos, descripcion, opciones, obligatoria);
        break;
      }

      default:
        throw new TipoPreguntaInexistenteException("No existe el tipo de pregunta seleccionado");
    }

    return pregunta;
  }
}
