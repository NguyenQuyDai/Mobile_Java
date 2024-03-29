package com.example.myapplication.SQL;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class Database extends SQLiteOpenHelper {
    public static final String DB_NAME = "ThietBi.db";

    public Database(Context context, int version) {
        super(context, DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS ThietBi (id INTEGER PRIMARY KEY AUTOINCREMENT, maTB VARCHAR(255) UNIQUE,tenTB VARCHAR(255), giaTB REAL, hinhanhTB BLOB)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ThietBi");
        onCreate(db);
    }

    public void QueryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor getData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    public long INSERT_THIETBI(String maTB, String tenTB, double giaTB, byte[] hinhanhTB) {
        SQLiteDatabase database = getWritableDatabase();

        if (isProductUnique(maTB, database)) {
            String sql = "INSERT INTO ThietBi (maTB, tenTB, giaTB, hinhanhTB) VALUES (?, ?, ?, ?)";
            SQLiteStatement statement = database.compileStatement(sql);
            statement.bindString(1, maTB);
            statement.bindString(2, tenTB);
            statement.bindDouble(3, giaTB);
            statement.bindBlob(4, hinhanhTB);

            return statement.executeInsert();
        } else {
            return -1; // Trả về -1 để chỉ ra rằng sản phẩm đã tồn tại và không thể thêm.
        }
    }
    public void UpdateWithImage(String query, byte[] imageData) {
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement stmt = db.compileStatement(query);

        // Nếu imageData không null, thêm dữ liệu hình ảnh vào truy vấn
        if (imageData != null) {
            stmt.bindBlob(1, imageData);
        }

        stmt.execute();
        db.close();
    }

    private boolean isProductUnique(String maTB, SQLiteDatabase db) {
        String query = "SELECT tenTB FROM ThietBi WHERE maTB = ?";
        Cursor cursor = db.rawQuery(query, new String[]{maTB});
        boolean isUnique = cursor.getCount() == 0;
        cursor.close();
        return isUnique;
    }
}
