package utilidades;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import jdk.nashorn.internal.parser.JSONParser;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Hashtable;

public class QRCodeGenerator {
  public static String generarQRCode(JSONObject jsonObject, String idQR) throws WriterException, IOException {
    String pathOut = "src/main/resources/public/imagenes/QR_out/QR_Code-" + idQR + ".png";
    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    BitMatrix bitMatrix = qrCodeWriter.encode(jsonObject.toString(), BarcodeFormat.QR_CODE, 350, 350);
    Path path = FileSystems.getDefault().getPath(pathOut);
    MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    return pathOut;
  }

  public static JSONObject decodificarQRCode(BufferedImage qrCode) throws NotFoundException, IOException {
    //TODO ver libreria para escanear QR desde la app
    LuminanceSource source = new BufferedImageLuminanceSource(qrCode);
    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
    Result result = new MultiFormatReader().decode(bitmap);
    return new ObjectMapper().readValue(result.getText(), JSONObject.class);
  }
}