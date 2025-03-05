package com.example.listview;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    ListView listView;
    EditText editText;
    Button btnAdd;
    Button btnUpdate;
    ArrayList<String> data = new ArrayList<>(Arrays.asList("ASP.NET", "Java", "C#", "Python", "Kotlin", "Dart", "C++"));
    int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        editText = findViewById(R.id.editText);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                data
        );

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = data.get(position);
            editText.setText(selectedItem);
            selectedPosition = position;
            Toast.makeText(MainActivity.this, "Bạn đã chọn: " + selectedItem, Toast.LENGTH_SHORT).show();
        });

        listView.setOnItemLongClickListener((adapterView, view, position, id) -> {
            String selectedItem = data.get(position);
            Toast.makeText(MainActivity.this, "Bạn đang nhấn giữ: " + position + " - " + selectedItem, Toast.LENGTH_SHORT).show();
            return true;
        });

        btnAdd.setOnClickListener(v -> {
            String newItem = editText.getText().toString().trim();
            if (newItem.isEmpty()) {
                Toast.makeText(MainActivity.this, "Vui lòng nhập tên môn học!", Toast.LENGTH_SHORT).show();
            } else if (data.contains(newItem)) {
                Toast.makeText(MainActivity.this, "Môn học đã tồn tại!", Toast.LENGTH_SHORT).show();
            } else {
                data.add(newItem);
                adapter.notifyDataSetChanged();
                editText.setText("");
                Toast.makeText(MainActivity.this, "Đã thêm: " + newItem, Toast.LENGTH_SHORT).show();
            }
        });

        btnUpdate.setOnClickListener(v -> {
            String updatedItem = editText.getText().toString().trim();
            if (selectedPosition == -1) {
                Toast.makeText(MainActivity.this, "Vui lòng chọn một môn học để cập nhật!", Toast.LENGTH_SHORT).show();
            } else if (updatedItem.isEmpty()) {
                Toast.makeText(MainActivity.this, "Vui lòng nhập tên môn học!", Toast.LENGTH_SHORT).show();
            } else if (data.contains(updatedItem) && !data.get(selectedPosition).equals(updatedItem)) {
                Toast.makeText(MainActivity.this, "Môn học đã tồn tại!", Toast.LENGTH_SHORT).show();
            } else {
                data.set(selectedPosition, updatedItem);
                adapter.notifyDataSetChanged();
                editText.setText("");
                selectedPosition = -1;
                Toast.makeText(MainActivity.this, "Đã cập nhật: " + updatedItem, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
