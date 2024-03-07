package com.example.demoandroidfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.demoandroidfirebase.Adapter.TodoListAdapter;
import com.example.demoandroidfirebase.Model.Todo;
import com.example.demoandroidfirebase.ModelFunction.TodoFunction;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button switchToAdd;
    RecyclerView recyclerView;
    ArrayList<Todo> todoArrayList;
    TodoListAdapter todoListAdapter;

    FirebaseFirestore dataFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchToAdd = findViewById(R.id.switchToAdd);
        recyclerView = findViewById(R.id.listTodoView);
        todoArrayList = new ArrayList<>();
        dataFirestore = FirebaseFirestore.getInstance();

        getData(dataFirestore);
        todoListAdapter = new TodoListAdapter(MainActivity.this, todoArrayList);

        // Switch to add button
        switchToAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddActivity.class));
            }
        });
    }

    void getData(FirebaseFirestore database){
        database.collection("todo")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Todo newObj = document.toObject(Todo.class);
                                todoArrayList.add(newObj);

                                // List controller
                                recyclerView.setAdapter(todoListAdapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Tải thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("abc", "Error data: " + e.toString());
                    }
                });
    }
}