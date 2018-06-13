package sample;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MyConector {

    private JSONObject autoryzacja;
    private int number;

    MyConector(String email, String password) throws Exception {
        URL url = new URL("http://smieszne-koty.herokuapp.com/oauth/token?grant_type=password&email=" + email + "&password=" + password);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setReadTimeout(30000);
        connection.setDoInput(true);
        connection.setDoOutput(true);

        BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = input.readLine()) != null)
            response.append(inputLine);
        input.close();
        System.out.println(response);

        autoryzacja = new JSONObject(response.toString());
        number = 0;
    }

    public List<Object> getCat() throws IOException {


        String token = autoryzacja.getString("access_token");
        URL url = new URL("http://smieszne-koty.herokuapp.com/api/kittens?access_token=" + token);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setReadTimeout(30000);

        BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = input.readLine()) != null)
            response.append(inputLine);
        input.close();
        System.out.println(response);

        JSONArray kotki = new JSONArray(response.toString());

        JSONObject kotek = kotki.getJSONObject(number);
        number++;

        List<Object> data = new ArrayList<>();
        data.add(kotek.getString("name"));
        data.add(kotek.getInt("vote_count"));
        data.add(kotek.getString("url"));
        return data;
    }
}
