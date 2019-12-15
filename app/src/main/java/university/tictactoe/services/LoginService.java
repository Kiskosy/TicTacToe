package university.tictactoe.services;

import android.os.AsyncTask;
import android.provider.SyncStateContract;
import android.util.Log;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import university.tictactoe.activities.LoginActivity;
import university.tictactoe.database.DatabaseClient;
import university.tictactoe.models.UserModel;

public class LoginService extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... strings) {
        try {

            URL url = new URL("https://jsonplaceholder.typicode.com/users");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            Log.d("status", String.valueOf(status));
            Reader streamReader = null;
            if (status != 200){
                streamReader = new InputStreamReader(connection.getErrorStream());
            }

            BufferedReader response = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String responseLine;
            StringBuffer responseString = new StringBuffer();
            while ((responseLine = response.readLine()) != null) {
                responseString.append(responseLine);
            }
            response.close();

            // Log.d("responseString", responseString.toString());

            return responseString.toString();

        } catch (MalformedURLException ex) { Log.d("MalformedURLException", ex.toString()); }
        catch (IOException ex) { Log.d("IOException", ex.toString()); }

        return "error";
    }

}
