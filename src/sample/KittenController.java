package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class KittenController implements Initializable {

    @FXML
    private Label name, voteCount, labNumber;
    @FXML
    private ImageView image;
    @FXML
    private Button next;

    private MyConector myConector;
    private int number, page;
    private JSONArray data;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            number = 0;
            page = 1;
            myConector = new MyConector("szymom.sp1@wp.pl", "1q2w3e");
            data = myConector.getCats(page);
            if (number < data.length())
                getCatInfo(number);
            System.out.println(data.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void next(ActionEvent event) {
        if (number < data.length())
            getCatInfo(number);
        else {
            try {
                next.setDisable(true);
                number = 0;
                page++;
                data = myConector.getCats(page);
                getCatInfo(number);
                next.setDisable(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void getCatInfo(int number) {
        JSONObject kotek = data.getJSONObject(number);
        this.number++;
        labNumber.setText(String.valueOf(this.number));
        name.setText(kotek.getString("name"));
        voteCount.setText(String.valueOf(kotek.getInt("vote_count")));
        image.setImage(new Image(kotek.getString("url")));

    }
}
