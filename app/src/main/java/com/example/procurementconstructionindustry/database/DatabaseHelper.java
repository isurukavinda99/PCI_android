package com.example.procurementconstructionindustry.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "pci_database.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = getWritableDatabase();
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {

            db.execSQL(DatabaseTable.User.CREATE_TABLE_STRING);
            db.execSQL(DatabaseTable.PurchaseOrder.CREATE_TABLE_STRING);
            db.execSQL(DatabaseTable.Item.CREATE_TABLE_STRING);
            db.execSQL(DatabaseTable.PurchaseOrderItem.CREATE_TABLE_STRING);

        }catch(Exception e){
            System.out.println("############ database error");
            e.printStackTrace();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean save(String TABLE_NAME , ContentValues contentValues){
        SQLiteDatabase db = getWritableDatabase();

        long result = db.insert(TABLE_NAME , null , contentValues);

        if (result == -1){
            return false;
        }else{
            return true;
        }

    }

    public Cursor view(String TABLE_NAME , String [] columns , String where , String whereArgs [] , String sortingOrder){

        /*
            * table name must be existing table name or names
            * columns is array ["col_name1" , "col_name2"...] if you want to select all then simply ["*"]
            * where close ex - "where id = 1" convert this to "id ?" !important dont put 'where' and values inside where closes
            * put null as the parameter when there is no any selection
            * whereArgs this contains values that map to '?' in whew close  ! please putt null when there is no selection args
            * sorting order look MAD Lab sheet this same as its sorting order
            * this method return cursor object
         */

        SQLiteDatabase db = getWritableDatabase();
        Cursor result = db.query(
                TABLE_NAME,
                columns,
                where,
                whereArgs,
                null,
                null,
                sortingOrder
        );

        return result;
    }

    public int update(String TABLE_NAME , ContentValues values , String where , String whereArgs[]){

        /*
            * Table name
            * values -> this is object of ContentValue , put column name and new value as key and value
            * where -> "where username like 10" this should be "username like ?" , dont put 'where' and values inside hear
            * whereArgs -> whereArgs this contains values that map to '?' in whew close
            * return effected row count
         */

        SQLiteDatabase db = getWritableDatabase();
        try{
            int effected_rows = db.update(
                    TABLE_NAME ,
                    values,
                    where,
                    whereArgs
            );

        }catch (Exception e){

        }


        return 1;
    }

    public void delete(String TABLE_NAME , String where , String whereArgs[]){

        /*
            *where -> "where username like 10" this should be "username like ?" , dont put 'where' and values inside hear
            *whereArgs -> whereArgs this contains values that map to '?' in whew close
            *return effected row count
         */

        SQLiteDatabase db = getWritableDatabase();
        int effected_rows = db.delete(TABLE_NAME , where , whereArgs);

    }


}
