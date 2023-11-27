import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickerApp extends JFrame {

    private int clickCount = 0;
    private int autoClickRate = 0;
    private int coins = 0; // Monnaie virtuelle
    private int maison = 0;
    private int coutMaison = 10;
    private int maissonette = 0;
    private int coutMaisonnette = 20;
    private JButton clickButton;
    private JButton clickAmelioration1;

    private JButton clickAmelioration2;
    private JLabel clickLabel;
    private JLabel coinsLabel;
    private JLabel amelioration1;
    private JLabel amelioration2;
    private Timer autoClickTimer;

    public ClickerApp() {
        // Configuration de la fenêtre
        setTitle("Clicker App");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Création des composants
        clickButton = new JButton("Click Me!");
        clickLabel = new JLabel("Click Count: 0");
        coinsLabel = new JLabel("Coins: 0");
        clickAmelioration1 = new JButton("une maison ( 10 coins) ");
        amelioration1 = new JLabel();
        clickAmelioration2 = new JButton("une maissonette ( 20 coins ) ");
        amelioration2 = new JLabel();


        // Configuration du gestionnaire de disposition (Layout Manager)
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Ajout des composants à la fenêtre
        add(clickButton);
        add(clickLabel);
        add(coinsLabel);
        add(clickAmelioration1);
        add(amelioration1);
        add(clickAmelioration2);
        add(amelioration2);


        clickButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Incrémentation du compteur de clics
                clickCount++;
                // Mise à jour du libellé avec le nouveau nombre de clics
                clickLabel.setText("Click Count: " + clickCount);

                // Ajoute une pièce chaque fois que le bouton est cliqué
                coins++;
                coinsLabel.setText("Coins: " + coins);
            }
        });
        clickAmelioration1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (coins >= coutMaison) {
                    coins -= coutMaison;
                    maison++;
                    amelioration1.setText("Maison" + maison);
                    updateCoinsLabel();
                    if (maison == 1) {
                        initializeAutoClicker(); // Initialise l'auto-clicker après l'achat de la première maison
                    }
                }
            }
        });
        clickAmelioration2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(coins >= coutMaisonnette) {
                    coins -= coutMaisonnette;
                    maissonette++;
                    amelioration2.setText("Maisonnette" + maissonette);
                    updateCoinsLabel();
                    if(maissonette == 1 ){
                        initializeAutoClicker();
                    }
                }

            }
        });
    }
    private void updateCoinsLabel () {
        coinsLabel.setText("Coins: " + coins);
    }
    private void initializeAutoClicker() {
        autoClickRate = 1; // Initialisation du nombre de clics automatiques par intervalle
        autoClickTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performAutoClickMaison();
            }
        });
        startAutoClicker(); // Démarre l'auto-clicker après l'achat de la première maison
    }

    private void startAutoClicker() {
        if (!autoClickTimer.isRunning()) {
            autoClickTimer.start();
        }
    }
    private void performAutoClickMaison() {
        // Exécutez ici le code que vous voulez exécuter automatiquement
        // Par exemple, simuler un clic sur le bouton
        for (int i = 0; i < autoClickRate; i++) {
            clickButton.doClick();
            // Ajoute des pièces en fonction du nombre de maisons
            coins += maison;
            updateCoinsLabel();
        }

    }
    private void performAutoClickMaisonnette() {
        // Exécutez ici le code que vous voulez exécuter automatiquement
        // Par exemple, simuler un clic sur le bouton
        for (int i = 0; i < autoClickRate; i++) {
            clickButton.doClick();
            // Ajoute des pièces en fonction du nombre de maisons
            coins += maissonette;
            updateCoinsLabel();
        }

    }


    // Assurez-vous de bien arrêter le Timer lorsque l'application se ferme
    public void stopAutoClicker() {
        if (autoClickTimer != null && autoClickTimer.isRunning()) {
            autoClickTimer.stop();
        }
    }

    // Assurez-vous d'arrêter le Timer lors de la fermeture de l'application
    @Override
    public void dispose() {
        super.dispose();
        stopAutoClicker();
    }


}