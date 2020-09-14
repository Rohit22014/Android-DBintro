package tryme.gohoof.dbintro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import Data.DatabaseHandler;
import Model.Contact;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);

        Log.d("DB Count:" , String.valueOf(db.getContactsCount()));

        Log.d("Insert: ", "Inserting....");
//        db.addContact(new Contact("Paul","293i23i20"));
//        db.addContact(new Contact("Paul2","293i23i22"));
//        db.addContact(new Contact("Paul3","293i23i23"));
//        db.addContact(new Contact("Paul4","293i23i24"));
//        db.addContact(new Contact("Paul5","293i23i25"));
        List<Contact> contactList = db.getAllContacts();

//        Contact oneContact = db.getContact(10);
//        oneContact.setName("Pauloooooooooo");
//        oneContact.setPhnumber("00000000000");
//        int newContact = db.updateContact(oneContact);

//        Log.d("One Contact","Update Row: " + String.valueOf(newContact) + " Name: " + oneContact.getName()+ " Phone: " + oneContact.getPhnumber());
//        db.deleteContact(oneContact);
        for(Contact c : contactList) {
            String log = "ID: "+c.getId()+ ", Name: " + c.getName() + ", Phone " + c.getPhnumber();

            Log.d("Name: ", log);
        }
    }
}