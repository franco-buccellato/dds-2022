package domain;

import javax.persistence.Embeddable;

@Embeddable
public enum SituacionMascota {
  EN_HOGAR_PROPIO,
  PERDIDA,
  EN_HOGAR_TRANSITORIO;
}
