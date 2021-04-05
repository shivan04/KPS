package sr.unasat.kpsfinetracker.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.util.List;

import sr.unasat.kpsfinetracker.entities.User;
import sr.unasat.kpsfinetracker.entities.Person;
import sr.unasat.kpsfinetracker.entities.Vehicle;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "kps.db";

    public static final String USERS_TABLE = "users";
    public static final String USERS_USERNAME = "username";
    public static final String USERS_PASSWORD = "password";

    public static final String PEOPLE_TABLE = "people";
    public static final String PEOPLE_ID = "id_number";

    public static final String VEHICLE_TABLE = "vehicles";
    public static final String VEHICLE_LICENSE_PLATE = "license_plate_number";

    public static final String CRIMES_TABLE = "crimes";
    public static final String CRIMES_PERSON = "person_id";

    private static final String SQL_SETUP_PERSON_QUERY = "CREATE TABLE people(id INTEGER PRIMARY KEY, lastname VARCHAR(255) NOT NULL, firstname VARCHAR(255) NOT NULL, id_number VARCHAR(255) NOT NULL, dob DATE , email_address VARCHAR(255) NOT NULL, phone_number VARCHAR(255) NOT NULL, address VARCHAR(255) NOT NULL)";

    private static final String SQL_SETUP_USER_QUERY = "CREATE TABLE users(id INTEGER PRIMARY KEY, username VARCHAR(255) NOT NULL UNIQUE, password VARCHAR(255) NOT NULL, station VARCHAR(255) NOT NULL, region VARCHAR(255) NOT NULL, district VARCHAR(255) NOT NULL, person_id INTEGER NOT NULL)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("CREATING PEOPLE TABLE");
        db.execSQL(SQL_SETUP_PERSON_QUERY);
        System.out.println("CREATING USER TABLE");
        db.execSQL(SQL_SETUP_USER_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        setDefaultCredentials();
    }

    public void setDefaultCredentials(){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM users WHERE username=? AND password=?", new String[]{"admin", "admin"});
        if(cursor.getCount() == 0){
            ContentValues personContentValues = new ContentValues();
            personContentValues.put("lastname", "Admin");
            personContentValues.put("firstname", "Admin");
            personContentValues.put("id_number", "0000");
            personContentValues.put("email_address", "admin@kps.sr");
            personContentValues.put("phone_number", "0000");
            personContentValues.put("address", "NA");
            long personId = insertOneRecord(PEOPLE_TABLE, personContentValues);

            if (personId >= 1){
                ContentValues userContentValues = new ContentValues();
                userContentValues.put(USERS_USERNAME, "admin");
                userContentValues.put(USERS_PASSWORD, "admin");
                userContentValues.put("station", "Tamenga");
                userContentValues.put("region", "Tamenga");
                userContentValues.put("district", "Paramaribo");
                userContentValues.put("person_id", personId);
                insertOneRecord(USERS_TABLE, userContentValues);
            }
        }

    }

    public long insertOneRecord(String tableName, ContentValues contentValues){
        SQLiteDatabase db = getWritableDatabase();
        long rowId = db.insert(tableName, null, contentValues);
        System.out.println("INSERT:"+ rowId);
        db.close();
        return rowId;
    }

    public boolean insertMultipleRecords(String tableName, List<ContentValues> contentValuesList){
        SQLiteDatabase db = getWritableDatabase();
        long countOnSucces = 0;
        long rowId = 0;
        for (ContentValues contentValues : contentValuesList) {
            rowId = db.insert(tableName, null, contentValues);
            countOnSucces = (rowId >= 1 ? countOnSucces++ : countOnSucces);
        }
        boolean isSuccess = (countOnSucces > 0 && contentValuesList.size() == countOnSucces);
        db.close();
        return isSuccess;
    }   

    public boolean validateLogin(String username, String password){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM users WHERE username=? AND password=?", new String[]{username, password});
        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

}