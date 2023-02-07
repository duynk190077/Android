package com.example.inforapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemOnClick {

    List<UserModel> items;

    UserAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        items = new ArrayList<>();
        adapter = new UserAdapter(items, this);
        ListView listUser = findViewById(R.id.list_view);
        listUser.setAdapter(adapter);
        new GetDataTask().execute();
    }

    @Override
    public void OnItemClick(int position) {
        UserModel user = items.get(position);
        Intent itent = new Intent(MainActivity.this, DetailActivity.class);
        Bundle data = new Bundle();
        data.putString("name", user.getName());
        data.putString("phone", user.getPhone());
        data.putString("avatar", user.getAvatar());
        data.putString("email", user.getEmail());
        data.putString("username", user.getUsername());
        data.putString("address", user.getAddress());
        data.putString("lat", user.getLat());
        data.putString("lng", user.getLng());
        itent.putExtras(data);
        startActivity(itent);
    }

    class GetDataTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                URL url = new URL("https://lebavui.github.io/jsons/users.json");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                String result = "";
                while ((line = reader.readLine()) != null)
                    result += line + "\n";
                reader.close();
                Log.v("TAG", result);
                JSONArray users = new JSONArray(result);
                for (int i = 0 ; i < users.length(); i++) {
                    JSONObject user = users.getJSONObject(i);
                    JSONObject address = user.getJSONObject("address");
                    JSONObject geo = address.getJSONObject("geo");
                    items.add(new UserModel(
                            user.getLong("id"),
                            user.getString("name"),
                            user.getJSONObject("avatar").getString("photo"),
                            user.getString("username"),
                            user.getString("email"),
                            user.getString("phone"),
                            address.getString("street") + ' ' + address.getString("city"),
                            geo.getString("lat"),
                            geo.getString("lng")
                    ));
                }
                return true;
            }catch (Exception ex) {
                ex.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean s) {
            try {
                if (s) {
                    adapter.notifyDataSetChanged();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}