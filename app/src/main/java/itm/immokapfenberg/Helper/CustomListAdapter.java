package itm.immokapfenberg.helper;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;

import itm.immokapfenberg.R;

public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemName;
    private final Integer[] imgId;
    private final Float[] rating;
    private final Float[] price;

    public CustomListAdapter(Activity context, String[] itemName, ArrayList<Immovable> immovables) {
        super(context, R.layout.list_view, itemName);

        ArrayList<Integer> imageId = new ArrayList();
        ArrayList<Float> ratings = new ArrayList();
        ArrayList<Float> prices = new ArrayList();

        for (Immovable immo: immovables) {
            imageId.add(immo.getImageId());
            ratings.add(immo.getRating());
            prices.add(immo.getPrice());
        }

        this.context = context;
        this.itemName = itemName;
        this.imgId = imageId.toArray(new Integer[0]);
        this.rating = ratings.toArray(new Float[0]);
        this.price = prices.toArray(new Float[0]);
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_view, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.mainName);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.image);
        RatingBar ratingValue = (RatingBar) rowView.findViewById(R.id.ratingBar);
        TextView priceTxt = (TextView) rowView.findViewById(R.id.price);

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String moneyString = formatter.format(price[position]);

        txtTitle.setText(itemName[position]);
        imageView.setImageResource(imgId[position]);
        ratingValue.setRating(rating[position]);
        priceTxt.setText(moneyString);

        return rowView;
    };
}