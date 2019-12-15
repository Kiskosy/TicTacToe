package university.tictactoe.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;
import org.json.JSONArray;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import university.tictactoe.R;
import university.tictactoe.database.DatabaseClient;
import university.tictactoe.databinding.ActivityMainBinding;
import university.tictactoe.listeners.RecycleAdapter;
import university.tictactoe.models.UserModel;
import university.tictactoe.services.LoginService;

public class LoginActivity extends Activity implements RecycleAdapter.ItemClickListener {

    private RecyclerView recyclerView;
    private RecycleAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<UserModel> userModels = new ArrayList<>();
    private List<String> userModelNames = new ArrayList<>();

    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;

        callAndParseResponse();
        SaveUsers saveUsers = new SaveUsers();
        saveUsers.execute();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RecycleAdapter(this, userModelNames);
        mAdapter.setClickListener(this);
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onItemClick(View view, int position){

        Intent intent = new Intent(view.getContext(), MainActivity.class);

        intent.putExtra("username", mAdapter.getItem(position));
        startActivity(intent);
    }

    private void callAndParseResponse(){
        try {
            String response = new LoginService().execute("test").get();

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response);

            for(JsonNode node : jsonNode){
                String id = node.get("id").toString().replaceAll("\"", "");
                String name = node.get("name").toString().replaceAll("\"", "");
                String username = node.get("username").toString().replaceAll("\"", "");
                String email = node.get("email").toString().replaceAll("\"", "");

                JsonNode addressJson = node.get("address");
                String street = addressJson.get("street").toString().replaceAll("\"", "");
                String suite = addressJson.get("suite").toString().replaceAll("\"", "");
                String city = addressJson.get("city").toString().replaceAll("\"", "");
                String zipcode = addressJson.get("zipcode").toString().replaceAll("\"", "");

                JsonNode geoJson = addressJson.get("geo");
                String lat = geoJson.get("lat").toString().replaceAll("\"", "");
                String lng = geoJson.get("lng").toString().replaceAll("\"", "");

                String phone = node.get("phone").toString().replaceAll("\"", "");
                String website = node.get("website").toString().replaceAll("\"", "");

                JsonNode companyJson = node.get("company");
                String companyName = companyJson.get("name").toString().replaceAll("\"", "");
                String catchPhrase = companyJson.get("catchPhrase").toString().replaceAll("\"", "");
                String bs = companyJson.get("bs").toString().replaceAll("\"", "");

                UserModel userModel = new UserModel(Integer.valueOf(id), name, username, email, phone, website, street, suite, city, zipcode, lat, lng, companyName, catchPhrase, bs);

                userModels.add(userModel);
                userModelNames.add(userModel.getUsername());

            }

        } catch (Exception ex){
            Log.d("ex", ex.toString());
        }
    }

    class SaveUsers extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids){

            for(UserModel userModel : userModels){
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().userModelDAO().insert(userModel);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
            //Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
        }

    }
}

