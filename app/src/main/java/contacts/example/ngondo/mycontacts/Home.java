package contacts.example.ngondo.mycontacts;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.software.shell.fab.ActionButton;

public class Home extends ListActivity {
    /*Array of strings*/
    String [] contactArray =new String[] {"Ian", "John", "Corn", "Dolly"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        /*Array adapter*/
        setListAdapter(new myArrayAdapter(this, contactArray));

        /*ListView listView = (ListView) findViewById(R.id.list_contacts);
        listView.setAdapter(adapter);*/
    }
}
