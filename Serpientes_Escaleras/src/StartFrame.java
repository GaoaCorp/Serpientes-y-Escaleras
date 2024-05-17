import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame extends JFrame {
    private JComboBox<String> boardSizeComboBox;
    private JComboBox<Integer> playerCountComboBox;
    private JTextField[] playerNames;
    private JButton startButton;

    public StartFrame() {
        setTitle("Configuración del Juego de Serpientes y Escaleras");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        add(new JLabel("Tamaño del Tablero:"));
        boardSizeComboBox = new JComboBox<>(new String[]{"10x10", "13x13", "15x15"});
        add(boardSizeComboBox);

        add(new JLabel("Número de Jugadores:"));
        playerCountComboBox = new JComboBox<>(new Integer[]{2, 3, 4});
        add(playerCountComboBox);

        playerNames = new JTextField[4];
        for (int i = 0; i < 4; i++) {
            add(new JLabel("Nombre del Jugador " + (i + 1) + ":"));
            playerNames[i] = new JTextField();
            playerNames[i].setEnabled(i < 2);
            add(playerNames[i]);
        }

        startButton = new JButton("Iniciar Juego");
        add(startButton);

        setupActions();
    }

    private void setupActions() {
        playerCountComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int playerCount = (Integer) playerCountComboBox.getSelectedItem();
                for (int i = 0; i < 4; i++) {
                    playerNames[i].setEnabled(i < playerCount);
                }
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String boardSizeStr = (String) boardSizeComboBox.getSelectedItem();
                int boardSize = Integer.parseInt(boardSizeStr.split("x")[0]);
                int playerCount = (Integer) playerCountComboBox.getSelectedItem();

                GameBoard board = new GameBoard(boardSize);
                setupDefaultSnakesAndLadders(board);

                String[] names = new String[playerCount];
                for (int i = 0; i < playerCount; i++) {
                    names[i] = playerNames[i].getText().trim().isEmpty() ? "Jugador " + (i + 1) : playerNames[i].getText().trim();
                }

                Game game = new Game(board, playerCount, names);
                new GameFrame(game, StartFrame.this).setVisible(true);
                StartFrame.this.setVisible(false);
            }
        });
    }

    private void setupDefaultSnakesAndLadders(GameBoard board) {
        int size = board.getSize();
        // Configurar serpientes y escaleras por defecto según el tamaño del tablero
        if (size == 10) {
            // Serpientes
            board.addSnake(16, 6);
            board.addSnake(47, 26);
            board.addSnake(49, 11);
            board.addSnake(56, 53);
            board.addSnake(62, 19);
            board.addSnake(64, 60);
            board.addSnake(87, 24);
            board.addSnake(93, 73);
            board.addSnake(95, 75);
            board.addSnake(98, 78);

            // Escaleras
            board.addLadder(1, 38);
            board.addLadder(4, 14);
            board.addLadder(9, 31);
            board.addLadder(21, 42);
            board.addLadder(28, 84);
            board.addLadder(36, 44);
            board.addLadder(51, 67);
            board.addLadder(71, 91);
            board.addLadder(80, 100);
        } else if (size == 13) {
            // Añadir serpientes y escaleras específicas para 13x13
            // Serpientes y escaleras adicionales para un tablero de 13x13
            board.addSnake(23, 5);
            board.addSnake(45, 24);
            board.addSnake(67, 40);
            board.addSnake(89, 51);
            board.addSnake(112, 93);
            board.addSnake(128, 106);

            board.addLadder(2, 22);
            board.addLadder(15, 38);
            board.addLadder(34, 57);
            board.addLadder(44, 68);
            board.addLadder(76, 98);
            board.addLadder(102, 124);
        } else if (size == 15) {
            // Añadir serpientes y escaleras específicas para 15x15
            // Serpientes y escaleras adicionales para un tablero de 15x15
            board.addSnake(30, 10);
            board.addSnake(50, 26);
            board.addSnake(76, 45);
            board.addSnake(99, 70);
            board.addSnake(125, 85);
            board.addSnake(143, 115);

            board.addLadder(3, 25);
            board.addLadder(20, 40);
            board.addLadder(37, 60);
            board.addLadder(52, 78);
            board.addLadder(82, 104);
            board.addLadder(110, 135);
        }
    }
}
