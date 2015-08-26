package pasha.elagin.spareparts.data;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import pasha.elagin.spareparts.R;

public class PartListAdapter extends ArrayAdapter<Part> {

    private final Activity activity;
    private final List<Part> records;

    public PartListAdapter(Activity activity, List<Part> records) {
        super(activity, R.layout.row_part_list, records);
        this.activity = activity;
        this.records = records;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        // create a view for the row if it doesn't already exist
        if (view == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            view = inflater.inflate(R.layout.row_part_list, null);
        }

        // populate row widgets from record data
        Part record = records.get(position);

        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(record.name);

        TextView cat_number = (TextView) view.findViewById(R.id.cat_number);
        cat_number.setText(record.id);

        TextView price = (TextView) view.findViewById(R.id.price);
        price.setText(record.price.toString());
        return view;
    }

    @Override
    public void notifyDataSetChanged() {

        // configuration may have changed - get current settings
        //getSettings();
        super.notifyDataSetChanged();
    }
}
