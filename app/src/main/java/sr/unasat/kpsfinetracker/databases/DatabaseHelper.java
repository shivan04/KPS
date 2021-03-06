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


    private static final String SQL_SETUP_PERSON_QUERY = "CREATE TABLE people(id INTEGER PRIMARY KEY, lastname VARCHAR(255) NOT NULL, firstname VARCHAR(255) NOT NULL, id_number VARCHAR(255) NOT NULL, dob DATE , email_address VARCHAR(255) , phone_number VARCHAR(255) , address VARCHAR(255))";

    private static final String SQL_SETUP_USER_QUERY = "CREATE TABLE users(id INTEGER PRIMARY KEY, username VARCHAR(255) NOT NULL UNIQUE, password VARCHAR(255) NOT NULL, station VARCHAR(255) NOT NULL, region VARCHAR(255) NOT NULL, district VARCHAR(255) NOT NULL, person_id INTEGER NOT NULL)";

    private static final String SQL_SETUP_VEHICLE_QUERY = "CREATE TABLE vehicles(id INTEGER PRIMARY KEY, license_plate_number VARCHAR(255) NOT NULL UNIQUE, person_id INTEGER)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            System.out.println("CREATING PEOPLE TABLE");
            db.execSQL(SQL_SETUP_PERSON_QUERY);
            System.out.println("CREATING USER TABLE");
            db.execSQL(SQL_SETUP_USER_QUERY);
            System.out.println("CREATING VEHICLE TABLE");
            db.execSQL(SQL_SETUP_VEHICLE_QUERY);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PEOPLE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + VEHICLE_TABLE);

        onCreate(db);
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        setDefaultCredentials();
    }

    public void setDefaultCredentials(){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM users WHERE username=? AND password=?",
                new String[]{"admin", "admin"});
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
        db.close();
        return rowId;
    }

    public long updateOneRecord(String tableName, ContentValues contentValues, String id ){
        SQLiteDatabase db = getWritableDatabase();
        long rowId = db.update(tableName, contentValues, "id = ?", new String[]{id});
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

    public Cursor searchTable(String tableName, String field_name, String search_data){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM "+ tableName + " WHERE " + field_name + " LIKE '%"+ search_data+"%'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        return cursor;
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
    public  Cursor getPerson(String personId){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM people WHERE ID = ?", new String[]{personId});
        return cursor;
    }
    public  boolean checkPersonExist(String personId){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM people WHERE ID = ?", new String[]{personId});
        return cursor.getCount() > 0;
    }

    public  boolean checkVehicleExist(String vehicleId){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM vehicles WHERE ID = ?", new String[]{vehicleId});
        return cursor.getCount() > 0;
    }

    public Cursor getLicensePlate(String licensePlateNumber){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM vehicles WHERE license_plate_number = ?", new String[]{licensePlateNumber});
        return cursor;
    }

    public Cursor getLicensePlates(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM vehicles", null);
        return cursor;
    }

    public void deleteLicensePlate(String vehicleId){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(VEHICLE_TABLE, "id = ?", new String[]{vehicleId});
    }

}