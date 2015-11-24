package contacts.example.ngondo.mycontacts;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Home extends Activity {
    /*Array of strings*/
    String [] contactArray = {"Ian", "John", "Corn", "Dolly"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        /*Array adapter*/
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.list_items, contactArray);

        /*The Listview to the adapter*/
        ListView listView = (ListView) findViewById(R.id.list_contacts);
        listView.setAdapter(adapter);

    }
}
