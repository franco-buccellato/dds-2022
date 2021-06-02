package domain;

import javax.mail.MessagingException;

public interface MedioNotificacion {
  void notificar(Contacto contacto, String mensage) throws MessagingException;
}
