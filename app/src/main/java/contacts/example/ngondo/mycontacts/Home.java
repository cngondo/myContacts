package contacts.example.ngondo.mycontacts;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.software.shell.fab.ActionButton;


public class Home extends ListActivity {
    //Array of strings
    String [] contactArray = new String[] {"Ian", "John", "Corn", "Dolly"};
    final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Firebase.setAndroidContext(this);
        //Initialize Firebase Client
        final Firebase MYCONTACTS = new Firebase("https://mycontacts254.firebaseio.com/");

        final Firebase thecontacts = MYCONTACTS.child("contacts");



        // Declare the action button to the view and add a click listener
        ActionButton actionButton = (ActionButton) findViewById(R.id.action_button);

        actionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //pop up a custom dialog for adding a new contact
                final Dialog addContact = new Dialog(context);
                addContact.setContentView(R.layout.add_contact);
                addContact.setTitle("New Contact");

                //Set the custom dialog components
                final EditText contactName = (EditText) addContact.findViewById(R.id.name);
                final EditText contactNumber = (EditText) addContact.findViewById(R.id.number);
                Button back = (Button) addContact.findViewById(R.id.back);
                Button addNewContact = (Button) addContact.findViewById(R.id.addnew);

                //If back is pressed dismiss the dialog
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addContact.dismiss();
                    }
                });

                addNewContact.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //capture users input
                        String theNumber = contactNumber.getText().toString();
                        String theName = contactName.getText().toString();

                        // Adding a child node to firebase client
                        // Getter for the name and contacts
                        Contacts contacts = new Contacts(theName, theNumber);
                        //Update to firebase contacts node and
                        //Add a callback to check whether the data has beed sent
                        thecontacts.push().setValue(contacts, new Firebase.CompletionListener() {
                            @Override
                            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                                if (firebaseError != null) {
                                    Toast.makeText(context, "Contact not saved! Check Connection", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "Contact saved Successfully", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        addContact.dismiss();
                    }
                });
                addContact.show();
            }

        });

        //read Data from Firebase
        thecontacts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot contactsSnapshot : dataSnapshot.getChildren()) {
                    Contacts contacts = contactsSnapshot.getValue(Contacts.class);
                    Log.i("con", contacts.getcName() + ": " + contacts.getcNumber());
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Toast.makeText(context, "The read failed!!", Toast.LENGTH_LONG).show();
            }
        });
        /*Array adapter*/
//        setListAdapter(new myArrayAdapter(this, contactArray));
        FirebaseListAdapter mAdapter = new FirebaseListAdapter<Contacts>(MYCONTACTS.child("contacts"), Contacts.class, android.R.layout.two_line_list_item, this) {
            @Override
            protected void populateView(View v, Contacts contacts) {
                ((TextView)v.findViewById(android.R.id.text1)).setText(contacts.getcName());
                ((TextView)v.findViewById(android.R.id.text2)).setText(contacts.getcNumber());
            }
        };

        setListAdapter(mAdapter);

    }


}
