package itm.immokapfenberg;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemName;
    private final Integer[] imgId;

    public CustomListAdapter(Activity context, String[] itemName, Integer[] imgId) {
        super(context, R.layout.list_view, itemName);

        this.context = context;
        this.itemName = itemName;
        this.imgId = imgId;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_view, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.mainName);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.image);

        txtTitle.setText(itemName[position]);
        imageView.setImageResource(imgId[position]);

        return rowView;
    };
}