import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends Frame implements ActionListener {
    private final Button[][] buttons = new Button[3][3]; 
    private boolean isXTurn = true; 
    private Label statusLabel; 
    private Button newGameButton; 

    public TicTacToe() {
        super("Tic Tac Toe"); 
        setLayout(new BorderLayout());
        Panel gridPanel = new Panel();
        gridPanel.setLayout(new GridLayout(3, 3)); 
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new Button(); 
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 40)); 
                buttons[i][j].addActionListener(this); 
                gridPanel.add(buttons[i][j]); 
            }
        }

        add(gridPanel, BorderLayout.CENTER);
        statusLabel = new Label("X's turn");
        add(statusLabel, BorderLayout.NORTH); 
        newGameButton = new Button("New Game");
        newGameButton.addActionListener(e -> resetGame()); 
        add(newGameButton, BorderLayout.SOUTH); 

        setSize(500, 500); 
        setVisible(true); 
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose(); 
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Button clickedButton = (Button) e.getSource(); 

       
        if (clickedButton.getLabel().isEmpty()) {
            clickedButton.setLabel(isXTurn ? "X" : "O"); 
            isXTurn = !isXTurn; 
            statusLabel.setText(isXTurn ? "X Player's turn" : "O Player's turn"); 

            String winner = checkWinner();
            if (winner != null) {
                statusLabel.setText(winner + " wins!"); 
                disableButtons(); 
            } else if (isTie()) { 
                statusLabel.setText("It's a tie!"); 
                disableButtons(); 
            }
        }
    }

    private String checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getLabel().equals(buttons[i][1].getLabel()) &&
                buttons[i][1].getLabel().equals(buttons[i][2].getLabel()) &&
                !buttons[i][0].getLabel().isEmpty()) {
                return buttons[i][0].getLabel(); 
            }
        }

        for (int j = 0; j < 3; j++) {
            if (buttons[0][j].getLabel().equals(buttons[1][j].getLabel()) &&
                buttons[1][j].getLabel().equals(buttons[2][j].getLabel()) &&
                !buttons[0][j].getLabel().isEmpty()) {
                return buttons[0][j].getLabel(); 
            }
        }

        if (buttons[0][0].getLabel().equals(buttons[1][1].getLabel()) &&
            buttons[1][1].getLabel().equals(buttons[2][2].getLabel()) &&
                !buttons[0][0].getLabel().isEmpty()) {
            return buttons[0][0].getLabel(); 
        }

        if (buttons[0][2].getLabel().equals(buttons[1][1].getLabel()) &&
            buttons[1][1].getLabel().equals(buttons[2][0].getLabel()) &&
                !buttons[0][2].getLabel().isEmpty()) {
            return buttons[0][2].getLabel(); 
        }

        return null; 
    }

    private boolean isTie() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getLabel().isEmpty()) { 
                    return false;
                }
            }
        }
        return true; 
    }

    private void disableButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false); 
            }
        }
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setLabel("");
                buttons[i][j].setEnabled(true); 
            }
        }

        isXTurn = true; 
        statusLabel.setText("X's turn"); 
    }

    public static void main(String[] args) {
        new TicTacToe(); 
    }
}
