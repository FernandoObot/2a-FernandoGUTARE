public class Data {
    /**
     * Convierte un bloque de texto en un arreglo de tokens.
     * @param data texto de entrada
     * @return arreglo de tokens (puede ser vacío)
     */
    public String[] saveData(String data) {
        if (data == null || data.isEmpty()) return new String[0];
        return data.trim().split("\\s+");
    }
}