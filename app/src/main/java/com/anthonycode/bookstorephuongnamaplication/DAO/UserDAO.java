package com.anthonycode.bookstorephuongnamaplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.anthonycode.bookstorephuongnamaplication.Database.DatabaseHelper;
import com.anthonycode.bookstorephuongnamaplication.Model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public static final String TABLE_NAME = "NguoiDung";
    public static final String SQL_NGUOI_DUNG = "CREATE TABLE NguoiDung (" +
            "id integer primary key autoincrement, " +
            "username text, " +
            "password text, " +
            "phone text, " +
            "fullname text);";
    public static final String TAG = "UserDAO";
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

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
    public List<User> getAllUser() {
        List<User> dsUser = new ArrayList<>();
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
        if (result <= 0) {
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
        String selection = "username = ? and password = ?";
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

    public boolean checkUserIfExist(String username) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where username=?", new String[]{username});
        if (cursor.getCount() > 0) {
            return false;
        } else {
            return true;
        }
    }

    public User Authenticate(User user) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,// Selecting Table
                new String[]{"id", "username", "password", "phone", "fullname"},//Selecting columns want to query
                "username=? and password=?",
                new String[]{user.getUserName(), user.getPassword()},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            //if cursor has value then in user database there is user associated with this given email
            User user1 = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));

            //Match both passwords check they are same or not
            if (user.getPassword().equalsIgnoreCase(user1.getPassword())) {
                return user1;
            }
        }
        //if user password does not matches or there is no record with that email then return @false
        return null;
    }

    public boolean isEmailExists(String username) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,// Selecting Table
                new String[]{"id", "username", "password", "phone", "fullname"},//Selecting columns want to query
                "username = ?",
                new String[]{username},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            //if cursor has value then in user database there is user associated with this given email so return true
            return true;
        }

        //if email does not exist return false
        return false;
    }

}

