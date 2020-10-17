package com.anthonycode.bookstorephuongnamaplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.anthonycode.bookstorephuongnamaplication.Database.DatabaseHelper;
import com.anthonycode.bookstorephuongnamaplication.Model.TheLoai;
import com.anthonycode.bookstorephuongnamaplication.Model.User;

import java.util.ArrayList;

public class UserDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public static final String TABLE_NAME = "NguoiDung";
    public static final String SQL_NGUOI_DUNG = "CREATE TABLE NguoiDung (" +
            "id integer primary key autoincrement, " +
            "username text, " +
            "password text, " +
            "phone text, " +
            "fullname text);";

    public static final String TAG = "UserDAO";

    public UserDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //insert
    public int inserNguoiDung(User user) {
        ContentValues values = new ContentValues();
        values.put("username", user.getUserName());
        values.put("password", user.getPassword());
        values.put("phone", user.getPhone());
        values.put("fullname", user.getHoTen());
        if (checkPrimaryKey(user.getUserName())) {
            int result = db.update(TABLE_NAME, values, "username=?", new String[]{user.getUserName()});
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
    public ArrayList<User> getAllUser() {
        ArrayList<User> dsUser = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            User ee = new User();
            ee.setId(c.getInt(0));
            ee.setUserName(c.getString(1));
            ee.setPassword(c.getString(2));
            ee.setPhone(c.getString(3));
            ee.setHoTen(c.getString(4));
            dsUser.add(ee);
            Log.d("Get all user //=====", ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsUser;

    }

    //update
    public boolean updateUser(User user) {
        ContentValues values = new ContentValues();
        values.put("username", user.getUserName());
        values.put("password", user.getPassword());
        values.put("phone", user.getPhone());
        values.put("fullname", user.getHoTen());
        long result = db.update(TABLE_NAME, values, "id=?", new String[]{String.valueOf(user.getId())});
        if (result <= 0){
            return false;
        }
        return true;
    }

    //delete
    public int deleteUserByID(int id) {
        int result = db.delete(TABLE_NAME, "id=?", new String[]{String.valueOf(id)});
        if (result == 0)
            return -1;
        return 1;
    }

    //check
    public boolean checkPrimaryKey(String strPrimaryKey) {
        //SELECT
        String[] columns = {"username"};
        //WHERE clause
        String selection = "username=?";
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
    //check user
    public boolean checkUser(String username, String password) {
        //SELECT
        String[] columns = {"id"};
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //WHERE clause
        String selection = "username = ? AND password = ?";
        //WHERE clause arguments
        String[] selectionArgs = {username, password};
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

