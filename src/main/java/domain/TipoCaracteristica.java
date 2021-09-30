package domain;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Enumerated;

@Embeddable
public enum TipoCaracteristica {
  BOOLEAN,
  TEXT,
  CHECKBOX,
  BULLET,
  NUMBER
}
