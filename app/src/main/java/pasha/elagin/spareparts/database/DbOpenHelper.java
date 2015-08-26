package pasha.elagin.spareparts.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE = "spareparts";

    public DbOpenHelper(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createSchema(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        createSchema(db);
    }

    private void createSchema(SQLiteDatabase db) {
        String createTableParts = "CREATE TABLE IF NOT EXISTS parts (catalog_number text, name text, price real)";
        db.execSQL(createTableParts);
    }
}
