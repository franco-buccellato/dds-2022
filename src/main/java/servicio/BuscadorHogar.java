package servicio;

import domain.HogarTransito;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface BuscadorHogar {
  List<HogarTransito> buscarHogares(int offset)
      throws NoSuchAlgorithmException, KeyManagementException;
}
