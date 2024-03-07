package com.example.demoandroidfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demoandroidfirebase.Model.Todo;
import com.example.demoandroidfirebase.ModelFunction.TodoFunction;

import java.util.UUID;

public class AddActivity extends AppCompatActivity {
    EditText inputTitle, inputContent;
    Button addButton, backButton;

    TodoFunction todoFunction = new TodoFunction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        inputTitle = findViewById(R.id.inTitle);
        inputContent = findViewById(R.id.inContent);
        addButton = findViewById(R.id.addButton);
        backButton = findViewById(R.id.backButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = UUID.randomUUID().toString();
                String title = inputTitle.getText().toString();
                String content = inputContent.getText().toString();

                if (title.isEmpty() && content.isEmpty()){
                    Toast.makeText(AddActivity.this, "Thiếu thông tin", Toast.LENGTH_SHORT).show();
                }
                else{
                    Todo newObj = new Todo(id, title, content, false);
                    todoFunction.addFunction(newObj, id, AddActivity.this);
                    Toast.makeText(AddActivity.this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddActivity.this, MainActivity.class));
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddActivity.this, MainActivity.class));
            }
        });
    }
}