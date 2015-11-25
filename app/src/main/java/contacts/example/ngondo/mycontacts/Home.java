package contacts.example.ngondo.mycontacts;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.software.shell.fab.ActionButton;

public class Home extends ListActivity {
    /*Array of strings*/
    String [] contactArray = new String[] {"Ian", "John", "Corn", "Dolly"};
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
                EditText contactName = (EditText) addContact.findViewById(R.id.name);
                EditText contactNumber = (EditText) addContact.findViewById(R.id.number);
                Button back = (Button) addContact.findViewById(R.id.back);
                Button addNewContact = (Button) addContact.findViewById(R.id.addnew);

                //If back is pressed dismiss the dialog
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addContact.dismiss();
                    }
                });

                addContact.show();
            }
        });

    }


}
