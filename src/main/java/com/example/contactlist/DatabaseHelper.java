package com.example.contactlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;
import android.widget.ImageView;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Contact.db";
    public static final String TABLE_NAME = "Contact_table";
    public static final String col_0= "ID";
    public static final String col_1 = "NAME";
    public static final String col_2 = "NUMBER";
    public static final String col_3 = "MAIL";
    public static int i=1;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create Table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY, NAME TEXT, NUMBER TEXT, MAIL TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData (String name, String number , String mail) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(col_0,i);
        contentValues.put(col_1, name);
        contentValues.put(col_2, number);
        contentValues.put(col_3, mail);

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        if (result == -1)
            return false;
        else {
            i++;
            return true;
        }
    }
    public Cursor getAllData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res =sqLiteDatabase.rawQuery("Select * from " + TABLE_NAME,null);
        return  res;
    }

    public boolean updateData(String name,String number,String mail){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col_1,name);
        contentValues.put(col_2,number);
        contentValues.put(col_3,mail);
        sqLiteDatabase.update(TABLE_NAME,contentValues,"NAME = ?",new String[] { name });
        return true;
    }

    public Integer deleteData(String name){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        i--;
        return sqLiteDatabase.delete(TABLE_NAME,"NAME = ?",new String[] { name });
    }
}
