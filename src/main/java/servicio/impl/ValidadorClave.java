package servicio.impl;

import servicio.ValidacionClave;
import java.util.ArrayList;
import java.util.List;

public class ValidadorClave {

  public List<ValidacionClave> validadores = new ArrayList<ValidacionClave>();

  public ValidadorClave() {
    validadores.add(new ValidadorClaveLargo());
    validadores.add(new ValidadorClaveListaNegra());
    validadores.add(new ValidadorClaveSecuencias());
    validadores.add(new ValidadorClaveTop10000());
  }

  public void validarClave(String usuario, String clave) {
    validadores.forEach(v -> v.validarClave(usuario, clave));
  }

}