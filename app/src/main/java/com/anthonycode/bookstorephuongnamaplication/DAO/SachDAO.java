package com.anthonycode.bookstorephuongnamaplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.anthonycode.bookstorephuongnamaplication.Database.DatabaseHelper;
import com.anthonycode.bookstorephuongnamaplication.Model.Sach;

import java.util.ArrayList;
import java.util.List;


public class SachDAO {
    public static final String TABLE_NAME = "Sach";
    public static final String SQL_SACH = "CREATE TABLE Sach " +
            "(id integer primary key autoincrement, " +
            "maSach text, " +
            "maTheLoai text, " +
            "tenSach text," +
            "tacGia text, " +
            "NXB text, " +
            "giaBia double, " +
            "soLuong number);";
    public static final String TAG = "SachDAO";
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public SachDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //insert
    public void inserSach(Sach s) {
        Log.d("Thêm sách : ", s.toString());
        ContentValues values = new ContentValues();
        values.put("maSach", s.getMaSach());
        values.put("maTheLoai", s.getMaTheLoai());
        values.put("tenSach", s.getTenSach());
        values.put("tacGia", s.getTacGia());
        values.put("NXB", s.getNXB());
        values.put("giaBia", s.getGiaBia());
        values.put("soLuong", s.getSoLuong());

        db.insert(TABLE_NAME, null, values);
    }

    //getAll
    public ArrayList<Sach> getAllSach() {
        ArrayList<Sach> dsSach = new ArrayList<>();
        try {
            Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
            c.moveToFirst();
            while (c.isAfterLast() == false) {
                Sach s = new Sach();
                s.setId(c.getInt(0));
                s.setMaSach(c.getString(1));
                s.setMaTheLoai(c.getString(2));
                s.setTenSach(c.getString(3));
                s.setTacGia(c.getString(4));
                s.setNXB(c.getString(5));
                s.setGiaBia(c.getDouble(6));
                s.setSoLuong(c.getInt(7));
                dsSach.add(s);
                Log.d("//=====", s.toString());
                c.moveToNext();
            }
            c.close();
        } catch (Exception e) {
            Log.d(TAG, "Error getAllSach: ==" + e.toString());
        }

        return dsSach;

    }

    //get ma sach
    public ArrayList<Sach> getMaSach() {
        ArrayList<Sach> dsSach = new ArrayList<>();
        try {
            Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
            c.moveToFirst();
            while (c.isAfterLast() == false) {
                Sach s = new Sach();
                s.setMaSach(c.getString(0));
                dsSach.add(s);
                Log.d("//=====", s.toString());
                c.moveToNext();
            }
            c.close();
        } catch (Exception e) {
            Log.d(TAG, "Error getAllSach: ==" + e.toString());
        }
        return dsSach;
    }

    //update
    public int updateSach(Sach s) {
        ContentValues values = new ContentValues();
        values.put("maSach", s.getMaSach());
        values.put("maTheLoai", s.getMaTheLoai());
        values.put("tenSach", s.getTenSach());
        values.put("tacGia", s.getTacGia());
        values.put("NXB", s.getNXB());
        values.put("giaBia", s.getGiaBia());
        values.put("soLuong", s.getSoLuong());
        int result = db.update(TABLE_NAME, values, "maSach=?", new String[]{s.getMaSach()});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    //delete
    public int deleteSachByID(int id) {
        int result = db.delete(TABLE_NAME, "id=?", new String[]{String.valueOf(id)});
        if (result == 0)
            return -1;
        return 1;
    }

    //check
    public boolean checkPrimaryKey(String strPrimaryKey) {
        //SELECT
        String[] columns = {"maSach"};
        //WHERE clause
        String selection = "maSach=?";
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

    //check
    public Sach checkBook(String id) {
        Sach s = new Sach();
        //SELECT
        String[] columns = {"id"};
        //WHERE clause
        String selection = "id=?";
        //WHERE clause arguments
        String[] selectionArgs = {id};
        Cursor c = null;
        try {
            c = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
            c.moveToFirst();

            while (c.isAfterLast() == false) {
                s.setId(c.getInt(0));
                s.setMaSach(c.getString(1));
                s.setMaTheLoai(c.getString(2));
                s.setTenSach(c.getString(3));
                s.setTacGia(c.getString(4));
                s.setNXB(c.getString(5));
                s.setGiaBia(c.getDouble(6));
                s.setSoLuong(c.getInt(7));
                Log.d("//=====", s.toString());
                break;
            }
            c.close();
            return s;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    //getAll
    public Sach getSachByID(String masach) {
        Sach s = null;
        //WHERE clause
        String selection = "maSach=?";
        //WHERE clause arguments
        String[] selectionArgs = {masach};
        Cursor c = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
        Log.d("getSachByID", "===>" + c.getCount());
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            s = new Sach();
            s.setId(c.getInt(0));
            s.setMaSach(c.getString(1));
            s.setMaTheLoai(c.getString(2));
            s.setTenSach(c.getString(3));
            s.setTacGia(c.getString(4));
            s.setNXB(c.getString(5));
            s.setGiaBia(c.getDouble(6));
            s.setSoLuong(c.getInt(7));
            break;

        }
        c.close();
        return s;

    }

    //getAll
    public List<Sach> getSachTop10(String month) {
        List<Sach> dsSach = new ArrayList<>();
        if (Integer.parseInt(month) < 10) {
            month = "0" + month;
        }
        String sSQL = "SELECT maSach, SUM(soLuong) as soluong FROM HoaDonChiTiet INNER JOIN HoaDon " +
                "ON HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon WHERE strftime('%m',HoaDon.ngayMua) = '" + month + "' " +
                "GROUP BY maSach ORDER BY soluong DESC LIMIT 10";
        Cursor c = db.rawQuery(sSQL, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            Log.d("//=====", c.getString(0));
            Sach s = new Sach();
            s.setMaSach(c.getString(0));
            s.setSoLuong(c.getInt(1));
            s.setGiaBia(0);
            s.setMaTheLoai("");
            s.setTenSach("");
            s.setTacGia("");
            s.setNXB("");
            dsSach.add(s);
            c.moveToNext();
        }
        c.close();
        return dsSach;

    }


}
