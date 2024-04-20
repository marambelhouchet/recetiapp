package com.example.receti;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class mex extends AppCompatActivity {
    private static final String TAG = "mexActivity";
    private RecyclerView recyclerView;
    private RecipeAdapter adapter;
    private List<Recipe> recipeList;
    private Button addbtn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mex);
        recyclerView = findViewById(R.id.r);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recipeList = new ArrayList<>();
        adapter = new RecipeAdapter(recipeList, this);
        recyclerView.setAdapter(adapter);
        addbtn = findViewById(R.id.add);
        addbtn.setOnClickListener(v -> startActivity(new Intent(mex.this, add.class)));
        downloadAndParseJsonFile();
    }

    private void downloadAndParseJsonFile() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl("gs://receti-9e4a9.appspot.com");
        StorageReference jsonFileRef = storageRef.child("mex.json");

        final long ONE_MEGABYTE = 1024 * 1024;
        jsonFileRef.getBytes(ONE_MEGABYTE)
                .addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        new ParseJsonTask().execute(bytes);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "Error downloading JSON file: " + e.getMessage());
                    }
                });
    }

    private class ParseJsonTask extends AsyncTask<byte[], Void, String> {
        @Override
        protected String doInBackground(byte[]... bytes) {
            byte[] jsonBytes = bytes[0];
            return new String(jsonBytes, StandardCharsets.UTF_8);
        }

        @Override
        protected void onPostExecute(String jsonContent) {
            parseJsonData(jsonContent);
        }
    }

    private void parseJsonData(String jsonContent) {
        try {
            JSONObject jsonData = new JSONObject(jsonContent);
            JSONArray selection1Array = jsonData.getJSONArray("selection1");

            for (int i = 0; i < selection1Array.length(); i++) {
                JSONObject item = selection1Array.getJSONObject(i);

                String name = item.optString("name", "");
                String imageUrl = item.optString("imageUrl", "");
                String description = item.optString("description", "");

                recipeList.add(new Recipe(name, imageUrl, description));
            }
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            Log.e(TAG, "Error parsing JSON data: " + e.getMessage());
        }
    }
}
