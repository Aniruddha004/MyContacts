package com.example.contactlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class add_contact extends AppCompatActivity {

    DatabaseHelper SQLite;
    EditText editname,editnumber,editmail;
    FloatingActionButton addphoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add new contact");
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        SQLite=new DatabaseHelper(this );
        editname = (EditText) findViewById(R.id.eT_name);
        editnumber = (EditText) findViewById(R.id.eT_number);
        editmail = (EditText) findViewById(R.id.eT_mail);
        addphoto=findViewById(R.id.add_photo);
        addphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    public void  addData(){

                boolean isInserted = SQLite.insertData(editname.getText().toString(),editnumber.getText().toString(),editmail.getText().toString());
                if(isInserted==true) {
                    Toast.makeText(add_contact.this, "Data Inserted", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(add_contact.this, MainActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(add_contact.this,"Data not Inserted",Toast.LENGTH_LONG).show();
            }

   @Override
    public boolean onCreateOptionsMenu(Menu menu1) {
        getMenuInflater().inflate(R.menu.menu_add,menu1);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.btn_add:
                addData();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
