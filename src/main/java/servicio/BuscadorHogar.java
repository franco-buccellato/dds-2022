package servicio;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import domain.HogarTransito;

public interface BuscadorHogar {
  HogarTransito buscarHogares(int offset)
      throws NoSuchAlgorithmException, KeyManagementException;
}
