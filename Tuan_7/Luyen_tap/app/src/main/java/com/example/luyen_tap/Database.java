package com.example.luyen_tap;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // truy van khong tra lai ket qua CREATE , INSERT ,.....
    public void queryData(String sql)
    {
        SQLiteDatabase database  = getWritableDatabase();
        database.execSQL(sql);
    }
    // truy van tra lai ket qua SELECT
    public Cursor getData(String sql)
    {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);

    }
    public void INSERT_MONHOC(String maMH,String tenMH,byte[] hinhAnhSP)
    {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO MonHoc VALUES(null, ?,?,?)";
        SQLiteStatement statement  = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,maMH);
        statement.bindString(2,tenMH);
        statement.bindBlob(3,hinhAnhSP);
        statement.executeInsert();
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {}

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

}
