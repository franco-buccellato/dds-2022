package domain.utilidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LectorArchivos {
  private String path;

  public LectorArchivos(String path) {
    this.path = path;
  }

  public Boolean pathArchivoValido() {
    File test = new File(path);
    return test.exists();
  }

  private FileReader abrirArchivo(String path) throws FileNotFoundException {
    File file = new File(path).getAbsoluteFile();
    return new FileReader(file);
  }

  public Boolean existeEnArchivo(String cadena) throws IOException {
    String linea;
    FileReader fileReader = abrirArchivo(this.path);
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    while ((linea = bufferedReader.readLine()) != null) {
      if (linea.equals(cadena)) {
        return Boolean.TRUE;
      }
    }
    return Boolean.FALSE;
  }
}
