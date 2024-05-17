import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {
    private Game game;
    private StartFrame startFrame;
    private JTextArea eventLog;
    private JLabel[] playerPositions;
    private JLabel currentPlayerLabel;
    private JButton rollButton;
    private JButton resetButton;
    private JButton backButton;
    private JPanel boardPanel;
    private String[] playerNames; // Almacenar los nombres de los jugadores

    public GameFrame(Game game, StartFrame startFrame) {
        this.game = game;
        this.startFrame = startFrame;
        this.playerNames = new String[game.getPlayers().size()];
        for (int i = 0; i < game.getPlayers().size(); i++) {
            this.playerNames[i] = game.getPlayers().get(i).getName();
        }
        this.eventLog = new JTextArea(10, 30);
        this.eventLog.setEditable(false);
        this.playerPositions = new JLabel[game.getPlayers().size()];
        this.currentPlayerLabel = new JLabel("Jugador actual: " + game.getCurrentPlayer().getName());
        this.rollButton = new JButton("Lanzar Dado");
        this.resetButton = new JButton("Reiniciar");
        this.backButton = new JButton("Regresar al Menú");

        setupUI();
        setupActions();
    }

    private void setupUI() {
        setTitle("Serpientes y Escaleras");
        setSize(800, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        int boardSize = game.getBoard().getSize();
        boardPanel = new JPanel(new GridLayout(boardSize, boardSize));
        for (int i = 1; i <= boardSize * boardSize; i++) {
            JLabel label = new JLabel(String.valueOf(i), SwingConstants.CENTER);
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            label.setOpaque(true);

            if (game.getBoard().getSnakes().containsKey(i)) {
                label.setBackground(new Color(255, 192, 203)); // Color rosado para las serpientes
                label.setText("<html><center>" + i + "<br>&#128013;</center></html>"); // Icono de serpiente
            } else if (game.getBoard().getLadders().containsKey(i)) {
                label.setBackground(new Color(144, 238, 144)); // Color verde claro para las escaleras
                label.setText("<html><center>" + i + "<br>&#128640;</center></html>"); // Icono de escalera
            } else {
                label.setBackground(i % 2 == 0 ? new Color(173, 216, 230) : new Color(240, 255, 255));
            }

            boardPanel.add(label);
        }

        JPanel playerPanel = new JPanel();
        for (int i = 0; i < game.getPlayers().size(); i++) {
            playerPositions[i] = new JLabel(game.getPlayers().get(i).getName() + ": 1");
            playerPanel.add(playerPositions[i]);
        }

        JPanel controlPanel = new JPanel();
        controlPanel.add(currentPlayerLabel);
        controlPanel.add(rollButton);
        controlPanel.add(resetButton);
        controlPanel.add(backButton);

        add(boardPanel, BorderLayout.CENTER);
        add(playerPanel, BorderLayout.NORTH);
        add(new JScrollPane(eventLog), BorderLayout.EAST);
        add(controlPanel, BorderLayout.SOUTH);
    }

    private void setupActions() {
        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int roll = game.rollDice();
                boolean won = game.movePlayer(roll);
                eventLog.append(game.getCurrentPlayer().getName() + " lanzó un " + roll + " y se movió a " + game.getCurrentPlayer().getPosition() + "\n");
                updatePlayerPositions();
                if (won) {
                    JOptionPane.showMessageDialog(GameFrame.this, game.getCurrentPlayer().getName() + " ha ganado! Felicitaciones!", "Ganador", JOptionPane.INFORMATION_MESSAGE);
                    rollButton.setEnabled(false);
                } else {
                    game.nextTurn();
                    currentPlayerLabel.setText("Jugador actual: " + game.getCurrentPlayer().getName());
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game = null;
                GameFrame.this.setVisible(false);
                startFrame.setVisible(true);
            }
        });
    }

    private void updatePlayerPositions() {
        for (int i = 0; i < game.getPlayers().size(); i++) {
            playerPositions[i].setText(game.getPlayers().get(i).getName() + ": " + game.getPlayers().get(i).getPosition());
        }
    }

    private void resetGame() {
        for (Player player : game.getPlayers()) {
            player.setPosition(1);
        }
        eventLog.setText("");
        rollButton.setEnabled(true);
        updatePlayerPositions();
        game = new Game(game.getBoard(), game.getPlayers().size(), playerNames); // Ajustar aquí el constructor
        currentPlayerLabel.setText("Jugador actual: " + game.getCurrentPlayer().getName());
    }
}
