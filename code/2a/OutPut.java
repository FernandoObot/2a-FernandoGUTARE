
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class OutPut {
    /**
     * Escribe el texto proporcionado en el archivo de salida.
     * @param outFile ruta del archivo de salida
     * @param outText texto a escribir
     */
    public void writeData(String outFile, String outText) {
        try (PrintWriter out = new PrintWriter(new FileWriter(outFile))) {
            out.print(outText);
            System.out.println("Resultados guardados en " + outFile);
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo de resultados: " + e.getMessage());
        }
    }
}