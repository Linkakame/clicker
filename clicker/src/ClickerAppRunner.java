import javax.swing.SwingUtilities;

public class ClickerAppRunner {

    public static void main(String[] args) {
        // Exécution de l'application Swing dans un thread d'interface utilisateur
        SwingUtilities.invokeLater(() -> {
            ClickerApp clickerApp = new ClickerApp();
            clickerApp.setVisible(true);
        });
    }
}