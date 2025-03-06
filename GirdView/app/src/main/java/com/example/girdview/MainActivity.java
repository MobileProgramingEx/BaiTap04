package com.example.girdview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    EditText editText;
    Button btnAdd;
    Button btnUpdate;
    ArrayList<String> data = new ArrayList<>(Arrays.asList("Java", "Kotlin", "Python", "PHP", "JavaScript", "C#"));
    ArrayList<Integer> images = new ArrayList<>(Arrays.asList(R.drawable.java, R.drawable.kotlin, R.drawable.python, R.drawable.php, R.drawable.javascript, R.drawable.c_sharp));
    int defaultImage = R.drawable.java;
    int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);
        editText = findViewById(R.id.editText);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);

        LanguageAdapter adapter = new LanguageAdapter(this, data, images);
        gridView.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> {
            String newItem = editText.getText().toString().trim();
            if (newItem.isEmpty()) {
                Toast.makeText(MainActivity.this, "Vui lòng nhập tên ngôn ngữ!", Toast.LENGTH_SHORT).show();
            } else if (data.contains(newItem)) {
                Toast.makeText(MainActivity.this, "Ngôn ngữ đã tồn tại!", Toast.LENGTH_SHORT).show();
            } else {
                data.add(newItem);
                images.add(defaultImage);
                adapter.notifyDataSetChanged();
                editText.setText("");
                Toast.makeText(MainActivity.this, "Đã thêm: " + newItem, Toast.LENGTH_SHORT).show();
            }
        });

        btnUpdate.setOnClickListener(v -> {
            String updatedItem = editText.getText().toString().trim();
            if (selectedPosition == -1) {
                Toast.makeText(MainActivity.this, "Vui lòng chọn một ngôn ngữ để cập nhật!", Toast.LENGTH_SHORT).show();
            } else if (updatedItem.isEmpty()) {
                Toast.makeText(MainActivity.this, "Vui lòng nhập tên ngôn ngữ!", Toast.LENGTH_SHORT).show();
            } else if (data.contains(updatedItem) && !data.get(selectedPosition).equals(updatedItem)) {
                Toast.makeText(MainActivity.this, "ngôn ngữ đã tồn tại!", Toast.LENGTH_SHORT).show();
            } else {
                data.set(selectedPosition, updatedItem);
                images.set(selectedPosition, defaultImage);
                adapter.notifyDataSetChanged();
                editText.setText("");
                selectedPosition = -1;
                Toast.makeText(MainActivity.this, "Đã cập nhật: " + updatedItem, Toast.LENGTH_SHORT).show();
            }
        });

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = data.get(position);
            editText.setText(selectedItem);
            selectedPosition = position;
        });
    }
}
