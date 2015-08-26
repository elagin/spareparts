package pasha.elagin.spareparts.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import pasha.elagin.spareparts.data.Part;

public class Parts {

    public static List<Part> get(Context context) {
        List<Part> result = new ArrayList<>();
        DbOpenHelper dbOpenHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        //Cursor cursor       = db.rawQuery("SELECT catalog_number, name, price FROM parts", new String[]{});
        Cursor cursor = db.rawQuery("SELECT catalog_number, name, price FROM parts", null);
        while (cursor.moveToNext()) {
            //result.add(new Part(cursor.getColumnIndex("catalog_number"), cursor.getColumnName("name"), , cursor.getColumnName("price")));
            result.add(new Part(cursor.getString(0), cursor.getString(1), cursor.getDouble(2)));
            //result.add(cursor.getInt(0));
        }
        cursor.close();
        db.close();
        return result;
    }

    public static void add(Context context, Part part) {
        DbOpenHelper dbOpenHelper = new DbOpenHelper(context);
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("catalog_number", part.id);
        contentValues.put("name", part.name);
        contentValues.put("price", part.price);
        db.insert("parts", null, contentValues);
        db.close();
    }
}
