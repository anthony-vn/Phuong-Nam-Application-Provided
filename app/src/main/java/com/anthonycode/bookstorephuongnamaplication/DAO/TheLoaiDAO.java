package com.anthonycode.bookstorephuongnamaplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.anthonycode.bookstorephuongnamaplication.Database.DatabaseHelper;
import com.anthonycode.bookstorephuongnamaplication.Model.TheLoai;

import java.util.ArrayList;

public class TheLoaiDAO {
    public static final String TABLE_NAME = "TheLoai";
    public static final String SQL_THE_LOAI = "CREATE TABLE TheLoai (" +
            "id integer primary key autoincrement, " +
            "matheloai text, " +
            "tentheloai text, " +
            "mota text);";
    public static final String TAG = "TheLoaiDAO";
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public TheLoaiDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //insert
    public int inserTheLoai(TheLoai theLoai) {
        ContentValues values = new ContentValues();
        values.put("matheloai", theLoai.getMaTheLoai());
        values.put("tentheloai", theLoai.getTenTheLoai());
        values.put("mota", theLoai.getMoTa());
        if (checkPrimaryKey(theLoai.getMaTheLoai())) {
            int result = db.update(TABLE_NAME, values, "matheloai=?", new String[]{theLoai.getMaTheLoai()});
            if (result == 0) {
                return -1;
            }
        } else {
            try {
                if (db.insert(TABLE_NAME, null, values) == -1) {
                    return -1;
                }
            } catch (Exception ex) {
                Log.e(TAG, ex.toString());
            }
        }
        return 1;
    }

    //getAllEmployee
    public ArrayList<TheLoai> getAllTheLoai() {
        ArrayList<TheLoai> dsTheLoai = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            TheLoai ee = new TheLoai();
            ee.setId(c.getInt(0));
            ee.setMaTheLoai(c.getString(1));
            ee.setTenTheLoai(c.getString(2));
            ee.setMoTa(c.getString(3));
            dsTheLoai.add(ee);
            Log.d("//=====", ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsTheLoai;
    }

    //getAllEmployee
    public ArrayList<TheLoai> getMaAndTenTheLoai() {
        ArrayList<TheLoai> dsTheLoai = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            TheLoai ee = new TheLoai();
            ee.setMaTheLoai(c.getString(0));
            ee.setTenTheLoai(c.getString(1));
            dsTheLoai.add(ee);
            Log.d("//=====", ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsTheLoai;
    }

    //update
    public boolean updateTheLoai(TheLoai theLoai) {
        ContentValues values = new ContentValues();
        values.put("matheloai", theLoai.getMaTheLoai());
        values.put("tentheloai", theLoai.getTenTheLoai());
        values.put("mota", theLoai.getMoTa());
        long result = db.update(TABLE_NAME, values, "id=?", new String[]{String.valueOf(theLoai.getId())});
        if (result <= 0) {
            return false;
        }
        return true;
    }

    //delete
    public int deleteTheLoaiByID(int id) {
        int result = db.delete(TABLE_NAME, "id=?", new String[]{String.valueOf(id)});
        if (result == 0)
            return -1;
        return 1;
    }

    //check
    public boolean checkPrimaryKey(String strPrimaryKey) {
        //SELECT
        String[] columns = {"matheloai"};
        //WHERE clause
        String selection = "matheloai=?";
        //WHERE clause arguments
        String[] selectionArgs = {strPrimaryKey};
        Cursor c = null;
        try {
            c = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
            c.moveToFirst();
            int i = c.getCount();
            c.close();
            if (i <= 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}

