import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class Logic {
    // Campos del diagrama (Logic2)
    private String programName;
    private String pad;
    private int counter;
    private int finalLOC;
    private String finOut;

    private String data;
    private String[] arrData;
    // ahora usamos LineCounter y MethodCounter
    private int linesCount;
    private int methodsCount;

    public void logical(String inFile, String outFile) {
        process(inFile, outFile);
    }

    /**
     * Método con el nombre del diagrama. Mantiene exactamente la misma
     * funcionalidad que `logical(...)` pero además actualiza los campos
     * del diagrama (`programName`, `finOut`, `counter`, `finalLOC`).
     */
    public void logic2a(String inFile, String outFile) {
    this.programName = inFile;
    this.finOut = outFile;
    process(inFile, outFile);
    // actualizar campos derivados
    this.counter = this.methodsCount;
    this.finalLOC = this.linesCount;
    }

    // Implementación real del procesamiento (compartida por ambos métodos)
    private void process(String inFile, String outFile) {
        // Leer datos: intentar leer preservando saltos de línea. Si falla, usar Input existente.
        try {
            data = new String(Files.readAllBytes(Paths.get(inFile)));
        } catch (IOException e) {
            Input input = new Input();
            data = input.readDataInFile(inFile);
        }

        // Ahora contamos líneas de código y métodos en el archivo Java leído
        if (data == null || data.isEmpty()) {
            System.out.println("Archivo de entrada vacío o no leído correctamente.");
            return;
        }

    // preparar arrData (líneas)
    arrData = data.split("\\r?\\n");

        LineCounter lc = new LineCounter();
        double lines = lc.count(arrData); // adaptador UML
        linesCount = (int) lines;

        MethodCounter mc = new MethodCounter();
        double methods = mc.count(arrData); // adaptador UML
        methodsCount = (int) methods;

    // media y desv fueron eliminados por ser campos no usados según el diagrama

        String resultado = String.format("Lineas de codigo: %d\nMetodos: %d\n", linesCount, methodsCount);

        // Escribir resultados
        OutPut out = new OutPut();
        out.writeData(outFile, resultado);
    }
}