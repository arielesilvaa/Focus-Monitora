import controller.FocusController;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Iniciando Projeto Monitor de Foco ---");
        FocusController controller = new FocusController();
        controller.start();
    }
}