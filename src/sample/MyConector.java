package sample;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyConector {

    private JSONObject autoryzacja;

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
    }

    public void getCats() throws IOException {


        String token = autoryzacja.getString("access_token");
        url = new URL("http://smieszne-koty.herokuapp.com/api/kittens?access_token=" + token);

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setReadTimeout(30000);

        input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        response = new StringBuilder();

        while ((inputLine = input.readLine()) != null)
            response.append(inputLine);
        input.close();
        System.out.println(response);

        JSONArray kotki = new JSONArray(response.toString());
        for (int i = 0; i < kotki.length(); i++) {
            JSONObject kotek = kotki.getJSONObject(i);
            System.out.println(kotek.getString("name"));
        }
    }
}
