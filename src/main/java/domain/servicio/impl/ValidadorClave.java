package domain.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import servicio.ValidacionClave;
import servicio.impl.ValidadorClaveLargo;
import servicio.impl.ValidadorClaveListaNegra;
import servicio.impl.ValidadorClaveSecuencias;
import servicio.impl.ValidadorClaveTop10000;

public class ValidadorClave {

  public List<ValidacionClave> validadores = new ArrayList<>();

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