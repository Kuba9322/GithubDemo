package pl.javastart;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPaneController implements Initializable {
    private boolean oTurn = true;
    @FXML
    public Label downInformation;
    @FXML
    public Label moveInformation;
    @FXML
    private Button button0x0;
    @FXML
    private Button button0x1;
    @FXML
    private Button button0x2;
    @FXML
    private Button button1x0;
    @FXML
    private Button button1x1;
    @FXML
    private Button button1x2;
    @FXML
    private Button button2x0;
    @FXML
    private Button button2x1;
    @FXML
    private Button button2x2;


    private char[][] fillTable() {
        char[][] ticTacTeoTable = new char[3][3];
        ticTacTeoTable[0][0] = button0x0.getText().charAt(0);
        ticTacTeoTable[0][1] = button0x1.getText().charAt(0);
        ticTacTeoTable[0][2] = button0x2.getText().charAt(0);
        ticTacTeoTable[1][0] = button1x0.getText().charAt(0);
        ticTacTeoTable[1][1] = button1x1.getText().charAt(0);
        ticTacTeoTable[1][2] = button1x2.getText().charAt(0);
        ticTacTeoTable[2][0] = button2x0.getText().charAt(0);
        ticTacTeoTable[2][1] = button2x1.getText().charAt(0);
        ticTacTeoTable[2][2] = button2x2.getText().charAt(0);
        for (char[] chars : ticTacTeoTable) {
            System.out.println(chars);
        }
        return ticTacTeoTable;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonAction(button0x0);
        buttonAction(button0x1);
        buttonAction(button0x2);
        buttonAction(button1x0);
        buttonAction(button1x1);
        buttonAction(button1x2);
        buttonAction(button2x0);
        buttonAction(button2x1);
        buttonAction(button2x2);
    }


    private void buttonAction(Button button) {
        button.setOnAction(actionEvent -> {
                    if (button.getText().equals(" ")) {
                        if (oTurn) {
                            button.setText("O");
                            oTurn = false;
                            moveInformation.setText("ruch: X");
                        } else {
                            button.setText("X");
                            oTurn = true;
                            moveInformation.setText("ruch: X");
                        }
                    }
                    char[][] ticTacTeoTable = fillTable();
                    check(ticTacTeoTable);
                    System.out.println("Sprwdzono wygraną");
                }
        );
    }

    private void check(char[][] table) {
        char lastMoveSign;
        if (oTurn)
            lastMoveSign = 'X';
        else
            lastMoveSign = 'O';

        boolean win1 = checkLine(table, lastMoveSign);
        boolean win2 = checkColumn(table, lastMoveSign);
        boolean win3 = checkFirstSlant(table, lastMoveSign);
        boolean win4 = checkSecondSlant(table, lastMoveSign);
        if (win1 || win2 || win3 || win4) {
            try {
                win();
            } catch (IOException | InterruptedException e) {
                System.out.println("Błąd");
                e.printStackTrace();
            }
        }
    }

    private void win() throws IOException, InterruptedException {

        WinPaneController winPaneController = new WinPaneController();
        winPaneController.winPane(oTurn, downInformation);
        clearField();

    }

    private void clearField() throws InterruptedException {
        button0x0.setText(" ");
        button0x1.setText(" ");
        button0x2.setText(" ");
        button1x0.setText(" ");
        button1x1.setText(" ");
        button1x2.setText(" ");
        button2x0.setText(" ");
        button2x1.setText(" ");
        button2x2.setText(" ");
    }

    private boolean checkFirstSlant(char[][] table, char lastMovieSing) {
        int tableLength = table.length;
        boolean win = true;
        for (int i = 0; i < tableLength; i++) {
            if (table[i][i] != lastMovieSing) {
                win = false;
                break;
            }
        }

        return win;
    }private boolean checkSecondSlant(char[][] table, char lastMovieSing) {
        int tableLength = table.length;
        boolean win = true;
        for (int i = 0; i < tableLength; i++) {
            if (table[2-i][i] != lastMovieSing) {
                win = false;
                break;
            }
        }

        return win;
    }

    private boolean checkColumn(char[][] table, char lastMovieSing) {
        int tableLength = table.length;
        for (int i = 0; i < tableLength; i++) {
            boolean win = true;
            for (int j = 0; j < tableLength; j++) {
                if (table[j][i] != lastMovieSing) {
                    win = false;
                    break;
                }
            }
            if (win) {
                return true;
            }
        }
        return false;
    }

    private boolean checkLine(char[][] table, char lastMovieSing) {
        int tableLength = table.length;
        for (int i = 0; i < tableLength; i++) {
            boolean win = true;
            for (int j = 0; j < tableLength; j++) {
                if (table[i][j] != lastMovieSing) {
                    win = false;
                    break;
                }
            }
            if (win) {
                return true;
            }
        }
        return false;
    }

    public boolean isoTurn() {
        return oTurn;
    }

    public void setoTurn(boolean oTurn) {
        this.oTurn = oTurn;
    }

    public Label getDownInformation() {
        return downInformation;
    }

    public void setDownInformation(Label downInformation) {
        this.downInformation = downInformation;
    }

    public Label getMoveInformation() {
        return moveInformation;
    }

    public void setMoveInformation(Label moveInformation) {
        this.moveInformation = moveInformation;
    }

    public Button getButton0x0() {
        return button0x0;
    }

    public void setButton0x0(Button button0x0) {
        this.button0x0 = button0x0;
    }

    public Button getButton0x1() {
        return button0x1;
    }

    public void setButton0x1(Button button0x1) {
        this.button0x1 = button0x1;
    }

    public Button getButton0x2() {
        return button0x2;
    }

    public void setButton0x2(Button button0x2) {
        this.button0x2 = button0x2;
    }

    public Button getButton1x0() {
        return button1x0;
    }

    public void setButton1x0(Button button1x0) {
        this.button1x0 = button1x0;
    }

    public Button getButton1x1() {
        return button1x1;
    }

    public void setButton1x1(Button button1x1) {
        this.button1x1 = button1x1;
    }

    public Button getButton1x2() {
        return button1x2;
    }

    public void setButton1x2(Button button1x2) {
        this.button1x2 = button1x2;
    }

    public Button getButton2x0() {
        return button2x0;
    }

    public void setButton2x0(Button button2x0) {
        this.button2x0 = button2x0;
    }

    public Button getButton2x1() {
        return button2x1;
    }

    public void setButton2x1(Button button2x1) {
        this.button2x1 = button2x1;
    }

    public Button getButton2x2() {
        return button2x2;
    }

    public void setButton2x2(Button button2x2) {
        this.button2x2 = button2x2;
    }
}
