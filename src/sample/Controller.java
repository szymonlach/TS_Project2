package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class Controller {


    @FXML
    private Label status;
    @FXML
    private TextField email, password;

    public void login(ActionEvent event) throws IOException {
        if (email.getText().equals("szymom.sp1@wp.pl") && password.getText().equals("1q2w3e")) {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Kitten.fxml"));
            primaryStage.setTitle("Kittens");
            primaryStage.setResizable(false);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
            ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();


        } else
            status.setText("Login Fail");
    }


}
