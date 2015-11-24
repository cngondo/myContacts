package contacts.example.ngondo.mycontacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Created by ngondo on 11/24/15.
 */
public class myArrayAdapter extends ArrayAdapter<String> {
    final Context context;
    final String[] values;

    public myArrayAdapter(Context context, String[] values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        return super.getView(position, convertView, parent);
    }
}
