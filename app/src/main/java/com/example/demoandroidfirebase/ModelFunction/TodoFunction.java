package com.example.demoandroidfirebase.ModelFunction;

import android.content.Context;
import android.os.Debug;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.demoandroidfirebase.Model.Todo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class TodoFunction {
    // Declare firebase database
    FirebaseFirestore database = FirebaseFirestore.getInstance();

    public void addFunction(Todo obj, String id, Context context){
        if(obj != null){
            Log.d("abc", obj.getId());

            database.collection("todo")
                    .document(id)
                    .set(obj.convertHashMap())
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context, "Error: " + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    public void updateFunction(Todo newObj, String id, Context context){
        // Update data by id
        database.collection("todo")
                .document(id)
                .update(newObj.convertHashMap())
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Lỗi sửa: " + e.toString(), Toast.LENGTH_SHORT).show();
                        Log.e("abc", e.toString());
                        Log.d("def", "ID todo: " + id);
                    }
                });
    }

    public void deleteFunction(String id, Context context){
        // Delete object by id
        database.collection("todo")
                .document(id)
                .delete()
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Xóa Thất Bại: " + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public ArrayList<Todo> getDataFunction(){
        // To-do list
        ArrayList<Todo> listTodo = new ArrayList<>();

        // Return to-do list data
        database.collection("todo")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Todo newObj = document.toObject(Todo.class);
                                listTodo.add(newObj);
                            }
                        }
                    }
                });

        return listTodo;
    }

    public void updateStatusFunction(String id, boolean newStatus){
        // Update new status by id
    }
}
