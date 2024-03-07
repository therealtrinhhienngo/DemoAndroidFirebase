package com.example.demoandroidfirebase.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoandroidfirebase.Model.Todo;
import com.example.demoandroidfirebase.ModelFunction.TodoFunction;
import com.example.demoandroidfirebase.R;

import java.util.ArrayList;
import java.util.UUID;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Todo> todoArrayList;

    public TodoListAdapter(Context context, ArrayList<Todo> todoArrayList) {
        this.context = context;
        this.todoArrayList = todoArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View heroView = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(heroView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TodoFunction todoFunction = new TodoFunction();
        Todo itemObj = todoArrayList.get(position);

        holder.titleView.setText(itemObj.getTitle());
        holder.contentView.setText(itemObj.getContent());

        holder.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Todo newObj = new Todo(UUID.randomUUID().toString(), "abc", "def", false);

                todoFunction.updateFunction(newObj, itemObj.getId(), context);

                holder.titleView.setText(newObj.getTitle());
                holder.contentView.setText(newObj.getContent());

                Toast.makeText(context, "Sửa Thành Công", Toast.LENGTH_SHORT).show();
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todoFunction.deleteFunction(itemObj.getId(), context);
                todoArrayList.remove(position);
                holder.titleView.setText("");
                Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return todoArrayList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleView, contentView;
        Button updateButton, deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.item_title);
            contentView = itemView.findViewById(R.id.item_content);
            updateButton = itemView.findViewById(R.id.updateButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
