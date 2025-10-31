
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class OutPut {
    public void WriteData(String outFile, String outText) {
        try (PrintWriter out = new PrintWriter(new FileWriter(outFile))) {
            out.print(outText);
            System.out.println("Resultados guardados en " + outFile);
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo de resultados: " + e.getMessage());
        }
    }
}