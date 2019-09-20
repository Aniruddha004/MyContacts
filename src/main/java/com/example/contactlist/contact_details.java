package com.example.contactlist;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class contact_details extends AppCompatActivity {
    DatabaseHelper SQLite;
    TextView name;
    Button number, email;
    ImageButton btnnumber,btnmail,btnmessage;
    int pos;

    private List<model_class> model_classList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        SQLite = new DatabaseHelper(this);
        SQLite.getWritableDatabase();

        final Cursor cursor = SQLite.getAllData();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Information");
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        name = findViewById(R.id.tV_name);
        number = findViewById(R.id.btn_number);
        email = findViewById(R.id.btn_mail);
        btnnumber = findViewById(R.id.call);
        btnmail = findViewById(R.id.mail);
        btnmessage = findViewById(R.id.message);

        Intent intent = getIntent();
        pos=intent.getIntExtra("pos",0);

        cursor.moveToPosition(pos);
            {
                name.setText(cursor.getString(0));
                number.setText(cursor.getString(1));
                final String phone = cursor.getString(1);
                number.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:" + phone));
                        startActivity(intent);
                    }
                });
                btnnumber.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:" + phone));
                        startActivity(intent);
                    }
                });
                btnmessage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("smsto:" + phone));
                        if (intent.resolveActivity(getPackageManager()) != null) {
                            startActivity(intent);
                        }
                    }
                });
                email.setText(cursor.getString(2));
                final String gmail = cursor.getString(2);
                email.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("mailto:" + gmail));
                        intent.putExtra(Intent.EXTRA_EMAIL, gmail);
                        if (intent.resolveActivity(getPackageManager()) != null) {
                            startActivity(intent);
                        }
                    }
                });
                btnmail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("mailto:" + gmail));
                        intent.putExtra(Intent.EXTRA_EMAIL, gmail);
                        if (intent.resolveActivity(getPackageManager()) != null) {
                            startActivity(intent);
                        }
                    }
                });
            }
        }

    public void deleteData () {
        Integer deletedRows = SQLite.deleteData(name.getText().toString());
        if (deletedRows > 0) {
            Toast.makeText(contact_details.this, "Data Deleted", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(contact_details.this, MainActivity.class);
            startActivity(intent);
        } else
            Toast.makeText(contact_details.this, "Data not Deleted", Toast.LENGTH_LONG).show();
    }

    public void showMessage (String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_view, menu);
        return true;
    }
    public boolean onSupportNavigateUp () {
        onBackPressed();
        return true;
    }

    @Override
            public boolean onOptionsItemSelected (@NonNull MenuItem item){
                switch (item.getItemId()) {
                    case R.id.btn_edit:
                        Intent intent=new Intent(contact_details.this,edit_contact.class);
                        intent.putExtra("name",name.getText().toString());
                        intent.putExtra("number",number.getText().toString());
                        intent.putExtra("mail",email.getText().toString());
                        startActivity(intent);
                        return true;
                    case R.id.btn_del:
                        deleteData();
                        return true;
                    default:
                        return super.onOptionsItemSelected(item);
                }
    }
}


