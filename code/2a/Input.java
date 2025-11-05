
import java.io.*;

public class Input {
    private String data;
    private BufferedReader br = null;

    public String readDataInFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                // conservar saltos de línea para análisis de código fuente
                sb.append(line).append(System.lineSeparator());
            }
            data = sb.toString();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            data = "";
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException e) {
                // ignore
            }
        }
        return data;
    }
}