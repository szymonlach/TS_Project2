package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class KittenController implements Initializable {

    @FXML
    private Label name, voteCount;
    @FXML
    private ImageView image;

    private MyConector myConector;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            myConector = new MyConector("szymom.sp1@wp.pl", "1q2w3e");
            List<Object> data = myConector.getCat();
            name.setText((String) data.get(0));
            voteCount.setText(String.valueOf(data.get(1)));
            image.setImage(new Image((String) data.get(2)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void next(ActionEvent event) {
        try {
            myConector.getCat();
            List<Object> data = myConector.getCat();
            name.setText((String) data.get(0));
            voteCount.setText(String.valueOf(data.get(1)));
            image.setImage(new Image((String) data.get(2)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
