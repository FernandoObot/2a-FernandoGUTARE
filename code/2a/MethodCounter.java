
/**
 * MethodCounter: cuenta métodos en un archivo fuente Java.
 *
 * Clase independiente y reutilizable. Proporciona `countMethods(String source)`
 * que devuelve el número estimado de métodos (incluye constructores).
 */
public class MethodCounter {

  /**
  * Cuenta métodos en el código fuente Java.
  * Procedimiento:
  * - Elimina comentarios y literales de cadena.
  * - Busca apariciones de `) {` que indiquen el inicio de un bloque de método
  *   y descarta estructuras de control (if, for, while, switch, catch) y
  *   clases anónimas (precedidas por `new`).
   *
   * @param source contenido del archivo Java
   * @return número de métodos detectados
   */
  private int totalMethods;
  private String[] arrData;

  public MethodCounter() {
    this.totalMethods = 0;
  }

  /**
   * Firma del diagrama: count(arrData : String[]) : double
   * Implementación: une las líneas, elimina comentarios y literales,
   * y busca patrones que representen métodos.
   */
  public double count(String[] arrData) {
    if (arrData == null) return 0.0;
    this.arrData = arrData;
    String source = String.join("\n", arrData);
    if (source.isEmpty()) return 0.0;

    // Quitar comentarios de bloque y literales de cadena
    String noBlock = source.replaceAll("(?s)/\\*.*?\\*/", "");
    String noStrings = noBlock.replaceAll("\"(\\\\.|[^\"\\\\])*\"", "\"\"");

    // Eliminar comentarios de línea
    String noLineComments = noStrings.replaceAll("//.*", "");

    String s = noLineComments;
    int count = 0;

    // Buscar patrones de ") {" y analizar lo que hay antes del '('
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == ')') {
        int j = i + 1;
        // saltar espacios
        while (j < s.length() && Character.isWhitespace(s.charAt(j))) j++;
        if (j < s.length() && s.charAt(j) == '{') {
          int open = findMatchingOpenParen(s, i);
          if (open == -1) continue;
          String wordBefore = getWordBefore(s, open);
          if (wordBefore == null) continue;
          String prevWord = getPreviousWord(s, open);
          String w = wordBefore.toLowerCase();
          if (w.equals("if") || w.equals("for") || w.equals("while") || w.equals("switch") || w.equals("catch") || w.equals("synchronized")) {
            continue;
          }
          if (prevWord != null && prevWord.equals("new")) continue;

          count++;
        }
      }
    }

    this.totalMethods = count;
    return (double) count;
  }

  /**
   * Adaptador: firma compatible con el diagrama UML.
   * Recibe un arreglo de líneas (String[]) y devuelve el conteo como double.
   */
  // (el método count(arrData) ya está implementado arriba; elimino duplicados y accesores no necesarios)

  // Encuentra la posición del paréntesis '(' que corresponde al ')' en posClose
  private int findMatchingOpenParen(String s, int posClose) {
    int depth = 1;
    for (int k = posClose - 1; k >= 0; k--) {
      char c = s.charAt(k);
      if (c == ')') depth++;
      else if (c == '(') {
        depth--;
        if (depth == 0) return k;
      }
    }
    return -1;
  }

  // Obtiene la palabra inmediatamente antes de la posición 'pos' (excluye espacios)
  private String getWordBefore(String s, int pos) {
    int i = pos - 1;
    while (i >= 0 && Character.isWhitespace(s.charAt(i))) i--;
    if (i < 0) return null;
    int end = i;
    while (i >= 0 && (Character.isJavaIdentifierPart(s.charAt(i)))) i--;
    return s.substring(i + 1, end + 1);
  }

  // Obtiene la palabra previa a la palabra antes de pos (por ejemplo para detectar 'new Type')
  private String getPreviousWord(String s, int pos) {
    int i = pos - 1;
    // saltar espacios
    while (i >= 0 && Character.isWhitespace(s.charAt(i))) i--;
    // saltar la palabra actual (tipo o nombre)
    while (i >= 0 && Character.isJavaIdentifierPart(s.charAt(i))) i--;
    // saltar espacios entre palabras
    while (i >= 0 && Character.isWhitespace(s.charAt(i))) i--;
    if (i < 0) return null;
    int end = i;
    while (i >= 0 && Character.isJavaIdentifierPart(s.charAt(i))) i--;
    int start = i + 1;
    return s.substring(start, end + 1);
  }

}
