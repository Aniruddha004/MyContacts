
package com.example.contactlist;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    private RecyclerView recyclerView;
    private DatabaseHelper SQLite;

    ArrayAdapter adapter;

    public ArrayList<model_class> model_classList = new ArrayList<>();
    FloatingActionButton add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLite = new DatabaseHelper(this);
        add = findViewById(R.id.addButton);
        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Contacts");
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView.setLayoutManager(linearLayoutManager);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, add_contact.class);
                startActivity(intent);
            }
        });

        Cursor cursor = SQLite.getAllData();
        while (cursor.moveToNext()) {
            model_classList.add(new model_class(R.drawable.ic_launcher_background, cursor.getString(0)));
        }
        adapter adapter = new adapter(model_classList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        if(model_classList.size()==0)
        {
            showMessage("No contacts","Add some contacts.");
        }
        adapter.setOnItemClickListener(new adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MainActivity.this, contact_details.class);
                intent.putExtra("pos", position);
                startActivity(intent);
            }
        });
    }

    public void showMessage (String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}

