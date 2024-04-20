package com.example.receti;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {

    Button koreanButton;
    Button arabButton;
    Button japButton;
    Button mexButton;
    Button italButton;
    Button spaButton;
    Button indButton;
    Button afButton;
    Button frenButton;
    Button addButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        koreanButton = findViewById(R.id.k);
        koreanButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, korean.class)));
        arabButton = findViewById(R.id.a);
        arabButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, arabian.class)));
        japButton = findViewById(R.id.j);
        japButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, japan.class)));
        mexButton = findViewById(R.id.m);
        mexButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, mex.class)));
        italButton = findViewById(R.id.i);
        italButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, italien.class)));
        spaButton = findViewById(R.id.s);
        spaButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, span.class)));
        indButton = findViewById(R.id.ind);
        indButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ind.class)));
        afButton = findViewById(R.id.af);
        afButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, afr.class)));
        frenButton = findViewById(R.id.f);
        frenButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, fren.class)));
        addButton = findViewById(R.id.add);
        addButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, add.class)));
    }
}

