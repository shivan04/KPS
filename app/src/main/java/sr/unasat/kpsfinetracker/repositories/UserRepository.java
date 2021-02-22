package sr.unasat.kpsfinetracker.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserRepository extends SQLiteOpenHelper {

    private static final String table_name = "kps_db.users";

    public UserRepository(Context context) {
        super(context, table_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean validateLogin(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT COUNT(*) FROM " + table_name + " WHERE username="+ username + " AND password=" + password;
        Cursor data = db.rawQuery(query, null);

//        ContentValues contentValues = new ContentValues();
//        contentValues.put("username", username);
//        contentValues.put("password", password);
//        if (data)
        return true;
    }

}
