package pl.javastart;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WinPaneController implements Initializable{

    public Text textField;

    public void winPane(boolean oTurn, Label downInformation) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane anchorPane = fxmlLoader.load(getClass().getResource("/winPane.fxml").openStream());
        stage.setScene(new Scene(anchorPane));
        stage.setTitle("TicTacToe - Winner");
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        stage.show();
        if (oTurn){
            downInformation.setText("Gratulacje X - wygrałeś!");
        }else {
            downInformation.setText("Gratulacje O - wygrałeś!");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        }

}
