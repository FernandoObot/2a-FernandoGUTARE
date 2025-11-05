
/**
 * LineCounter: cuenta líneas de código en un archivo fuente Java.
 *
 * Esta clase es independiente y reutilizable. Proporciona un método
 * público `countLines(String source)` que recibe el contenido entero del
 * archivo y devuelve el número de líneas de código (excluye comentarios
 * y líneas en blanco).
 */
public class LineCounter {

    private int totalLines;
    private String[] arrData;

    public LineCounter() {
        this.totalLines = 0;
    }

    /**
     * Cuenta las líneas de código no vacías eliminando comentarios y literales de cadena.
     * Firma del diagrama: count(arrData : String[]) : double
     * @param arrData arreglo de líneas de texto
     * @return número de líneas de código (double por compatibilidad UML)
     */
    public double count(String[] arrData) {
        if (arrData == null) return 0.0;
        this.arrData = arrData;
        String source = String.join("\n", arrData);
        if (source.isEmpty()) return 0.0;

        // Eliminar comentarios de bloque y literales de cadena
        String noBlockComments = source.replaceAll("(?s)/\\*.*?\\*/", "");
        String noStrings = noBlockComments.replaceAll("\"(\\\\.|[^\"\\\\])*\"", "\"\"");

        String[] lines = noStrings.split("\\r?\\n");
        int count = 0;
        for (String line : lines) {
            String noLineComment = line.replaceAll("//.*", "").trim();
            if (!noLineComment.isEmpty()) count++;
        }
        this.totalLines = count;
        return (double) count;
    }

}
