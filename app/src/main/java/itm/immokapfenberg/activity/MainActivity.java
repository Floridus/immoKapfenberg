package itm.immokapfenberg.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

import itm.immokapfenberg.R;
import itm.immokapfenberg.helper.CustomListAdapter;
import itm.immokapfenberg.helper.FileHelper;
import itm.immokapfenberg.helper.Immovable;

public class MainActivity extends BaseActivity {

    private CustomListAdapter adapter;
    private ListView listView;

    private ImageButton searchFilter;

    private Intent oneViewIntent;

    private boolean redfeld;
    private boolean hafendorf;
    private boolean werkVI;
    private boolean altstadt;
    private int parking;
    private float rating;
    private int infrastructure;
    private int price;
    private int size;
    private boolean accessibility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.immoList);

        searchFilter = (ImageButton)findViewById(R.id.searchFilter);

        searchFilter.setVisibility(View.VISIBLE);

        redfeld = true;
        hafendorf = true;
        werkVI = true;
        altstadt = true;
        parking = 0;
        rating = 0;
        infrastructure = 0;
        price = -1;
        size = 0;
        accessibility = false;



        searchFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.filter_layout);
                dialog.setTitle("Title...");

                View redfeldView = dialog.findViewById(R.id.redfeld);
                View hafendorfView = dialog.findViewById(R.id.hafendorf);
                View werkVIView = dialog.findViewById(R.id.werkVI);
                View altstadtView = dialog.findViewById(R.id.altstadt);
                View parkingView = dialog.findViewById(R.id.parkingInput);
                View ratingView = dialog.findViewById(R.id.ratingInput);
                View infrastructureView = dialog.findViewById(R.id.infrastructureInput);
                View priceView = dialog.findViewById(R.id.priceInput);
                View sizeView = dialog.findViewById(R.id.sizeeInput);
                View accessibilityView = dialog.findViewById(R.id.accessibilityInput);
                View okButton = dialog.findViewById(R.id.dialogButtonOK);
                View cancelButton = dialog.findViewById(R.id.dialogButtonCancel);



                View.OnClickListener okListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("INFO", "Okay button clicked");
                        dialog.cancel();
                    }
                };

                View.OnClickListener cancelListener = new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Log.i("INFO", "Cancel button clicked");
                        dialog.cancel();
                    }
                };

                okButton.setOnClickListener(okListener);
                cancelButton.setOnClickListener(cancelListener);






                dialog.show();

            }
        });







        oneViewIntent = new Intent(this, OneViewActivity.class);

        addItemList();
    }

    private void addItemList() {
        getItemList();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                oneViewIntent.putExtra("immoIndex", position);

                startActivity(oneViewIntent);
            }
        });
    }

    private void getItemList() {
        FileHelper fileHelper = new FileHelper();
        ArrayList<Immovable> immovables = fileHelper.readAll(MainActivity.this);
        ArrayList<String> items = new ArrayList();

        for (Immovable immo: immovables) {
            items.add(immo.getName());
        }

        adapter = new CustomListAdapter(this, items.toArray(new String[0]), immovables);
    }
}
