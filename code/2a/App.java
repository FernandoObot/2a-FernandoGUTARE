/**
 * App - punto de entrada de la aplicación.
 *
 * Uso: java App <archivo_entrada> <archivo_salida>
 *
 * Esta clase delega el trabajo a `Logic.logic2a(...)` tras validar los
 * argumentos de entrada. Los códigos de salida usados son:
 *  - 1 : argumentos insuficientes
 *  - 2 : error durante el procesamiento
 */
public class App {
    /**
     * Método main: valida argumentos y llama a la lógica principal.
     * @param args [0]=archivo entrada, [1]=archivo salida
     */
    public static void main(String[] args) {
        // Validación defensiva: comprobamos null y longitud mínima
        if (args == null || args.length < 2) {
            System.out.println("Uso: java App <archivo_entrada> <archivo_salida>");
            // Salida con código 1 para indicar uso incorrecto
            System.exit(1);
        }

        // Nombres descriptivos para los parámetros
        String inputFile = args[0];
        String outputFile = args[1];

        // Crear la instancia que contiene la lógica del programa
        Logic logic = new Logic();
        try {
            // Delegar el procesamiento principal a Logic
            logic.logic2a(inputFile, outputFile);
        } catch (Exception ex) {
            // Mostrar mensaje amigable y salir con código de error
            System.out.println("Ocurrió un error al procesar los archivos: " + ex.getMessage());
            System.exit(2);
        }
    }
}