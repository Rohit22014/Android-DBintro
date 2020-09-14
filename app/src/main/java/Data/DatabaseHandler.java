package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import Model.Contact;
import Utils.Util;

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(@Nullable Context context) {
        super(context, Util.DATABASE_NAME,null,Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACT_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY," + Util.KEY_NAME +  " TEXT,"
                + Util.KEY_PHONE_NUMBER + " TEXT" + ")";

        sqLiteDatabase.execSQL(CREATE_CONTACT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, contact.getName());
        values.put(Util.KEY_PHONE_NUMBER, contact.getPhnumber());

        db.insert(Util.TABLE_NAME,null, values);
        db.close();
    }

    public Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Util.TABLE_NAME, new String[] {Util.KEY_ID,
                Util.KEY_NAME,
                Util.KEY_PHONE_NUMBER},Util.KEY_ID + "=?", new String[] {String.valueOf(id)}, null,
                null, null,null);

        if(cursor != null) {
            cursor.moveToFirst();
        }
        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2));

        return contact;
    }

    public List<Contact> getAllContacts() {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Contact> contactList = new ArrayList<>();

        //select all contacts

        String selectAll = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll,null);

        if(cursor.moveToFirst()) {
            do{
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhnumber(cursor.getString(2));

                contactList.add(contact);
            }
            while (cursor.moveToNext());
        }
        return contactList;
    }

    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(Util.KEY_NAME, contact.getName());
            values.put(Util.KEY_PHONE_NUMBER, contact.getPhnumber());

            return db.update(Util.TABLE_NAME, values, Util.KEY_ID + "=?",
                    new String[]{String.valueOf(contact.getId())});
    }

    public void deleteContact (Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Util.TABLE_NAME,Util.KEY_ID + " = ?", new String[]{String.valueOf(contact.getId())});

        db.close();
    }

    public int getContactsCount() {
        String countQuery = "SELECT * FROM " + Util.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

//        cursor.close();
        return cursor.getCount();
    }
}
