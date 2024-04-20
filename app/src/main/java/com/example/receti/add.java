package com.example.receti;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import java.util.regex.Pattern;
import android.util.Patterns;

public class add extends AppCompatActivity {

    private EditText emailEditText, originEditText, recipeEditText;
    private RecipeDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        dbHelper = new RecipeDatabaseHelper(this);

        emailEditText = findViewById(R.id.editTextTextEmailAddress);
        originEditText = findViewById(R.id.editTextText);
        recipeEditText = findViewById(R.id.recipeadded);

        Button addButton = findViewById(R.id.button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRecipe();
            }
        });
    }

    private void addRecipe() {
        String email = emailEditText.getText().toString().trim();
        String origin = originEditText.getText().toString().trim();
        String recipe = recipeEditText.getText().toString().trim();

        if (!email.isEmpty() && isValidEmail(email) && !origin.isEmpty() && !recipe.isEmpty()) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(RecipeDatabaseHelper.COLUMN_EMAIL, email);
            values.put(RecipeDatabaseHelper.COLUMN_ORIGIN, origin);
            values.put(RecipeDatabaseHelper.COLUMN_RECIPE, recipe);
            long newRowId = db.insert(RecipeDatabaseHelper.TABLE_RECIPE, null, values);
            if (newRowId != -1) {
                Toast.makeText(this, "Recipe added successfully", Toast.LENGTH_SHORT).show();
                clearEditTexts();
            } else {
                Toast.makeText(this, "Failed to add recipe", Toast.LENGTH_SHORT).show();
            }
            db.close();
        } else {
            Toast.makeText(this, "Please fill in all fields with valid email", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearEditTexts() {
        emailEditText.setText("");
        originEditText.setText("");
        recipeEditText.setText("");
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}
