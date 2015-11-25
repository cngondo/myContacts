package contacts.example.ngondo.mycontacts;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.software.shell.fab.ActionButton;

import java.util.HashMap;
import java.util.Map;


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
        /*Array adapter*/
        setListAdapter(new myArrayAdapter(this, contactArray));
        /*ListView listView = (ListView) findViewById(R.id.list_contacts);
        listView.setAdapter(adapter);*/
//        Declare the action button to the view and add a click listener
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
                        Firebase thecontacts = MYCONTACTS.child("contacts");
                        Map<String, String> contactsMap = new HashMap<String, String>();
                        contactsMap.put(theName, theNumber);

                        //Update to firebase contacts node and
                        //Add a callback to check whether the data has beed sent
                        thecontacts.setValue(contactsMap, new Firebase.CompletionListener() {
                            @Override
                            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                                if (firebaseError != null) {
                                    Toast.makeText(this,"Contact failed to save",Toast.LENGTH_SHORT).show();
                                } else {
                                    System.out.println("Data saved successfully.");
                                }
                            }
                        });



                    }
                });
                addContact.show();
            }

        });

    }


}
