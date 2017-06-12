package com.efpro.iak20;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView todoList;
    private List<Todo> todos = new ArrayList<>();
    private TodoAdapter todoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoList = (ListView) findViewById(R.id.todoList);

        todoAdapter = new TodoAdapter(this, todos);
        todoList.setAdapter(todoAdapter);
        setData();

        todoList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Delete Confirmation")
                        .setMessage("Anda yakin ingin menghapus Todo " + todos.get(position).getTitle()+ " ?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //menghapus todolist
                                todos.remove(position);
                                todoAdapter.notifyDataSetChanged();
                                dialog.dismiss(); //menutup dialog
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
                return true;
            }
        });

        todoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detail = new Intent(MainActivity.this, DetailActivity.class);
                detail.putExtra("title",todos.get(position).getTitle());
                detail.putExtra("description",todos.get(position).getDescription());
                detail.putExtra("date",todos.get(position).getDate());
                startActivity(detail);
            }
        });

    }

    //default to dolist (masih statis, belum pakai database)
    private void setData(){
        for (int i=0; i<5; i++){
            Todo todo = new Todo();
            todo.setTitle("Makan ayam");
            todo.setDescription("ayam nya belum ditangkap bang");
            todo.setDate("25/05/2017");

            todos.add(todo);
        }
        todoAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_add){
            newItemDialog();
        }
        return true;
    }

    private void newItemDialog(){
        //memanggil layout untuk menambah todolist
        LinearLayout layout;
        layout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.new_item_dialog, null);
        final EditText edtTitle = (EditText) findViewById(R.id.edtTitle);
        final EditText edtDescription = (EditText) findViewById(R.id.edtDescription);
        final EditText edtDate = (EditText) findViewById(R.id.edtDate);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Todo");
        builder.setView(layout);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Todo todo = new Todo();
                todo.setTitle(edtTitle.getText().toString());
                todo.setDescription(edtDescription.getText().toString());
                todo.setDate(edtDate.getText().toString());
                todos.add(todo);
                dialog.dismiss();
                todoAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }
}
