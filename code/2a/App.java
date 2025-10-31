public class App {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Uso: java App <archivo_entrada> <archivo_salida>");
            return;
        }
        Logic logic = new Logic();
        logic.logic2a(args[0], args[1]);
    }
}