package contacts.example.ngondo.mycontacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
        View rowView = layoutInflater.inflate(R.layout.custom_list,parent,false);
        TextView name = (TextView) rowView.findViewById(R.id.contact_name);
        TextView number = (TextView) rowView.findViewById(R.id.contact_number);
        ImageView icon = (ImageView) rowView.findViewById(R.id.image);
        name.setText(values[position]);

        String s = values[position];
        if (s.startsWith("Ian"))
        {
            icon.setImageResource(R.drawable.ic_action);
            number.setText("084908492");
        }else{
            icon.setImageResource(R.drawable.ic_action);
        }

        return rowView;
    }
}
