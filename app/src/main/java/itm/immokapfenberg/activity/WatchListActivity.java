package itm.immokapfenberg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import itm.immokapfenberg.R;
import itm.immokapfenberg.helper.Constants;
import itm.immokapfenberg.helper.CustomListAdapter;
import itm.immokapfenberg.helper.FileHelper;
import itm.immokapfenberg.helper.Immovable;

public class WatchListActivity extends BaseActivity {
    private CustomListAdapter adapter;
    private ListView listView;

    private ArrayList<Immovable> watchImmovables;

    private Intent oneViewIntent;
    private String favouriteString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.immoList);

        oneViewIntent = new Intent(this, OneViewActivity.class);
        watchImmovables = new ArrayList<Immovable>();

        FileInputStream fis = null;
        favouriteString = "";
        try {
            fis = openFileInput(Constants.STORAGE_FAVOURITE);
            int c;
            while ((c = fis.read()) != -1) {
                favouriteString += Character.toString((char)c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        addItemList();
    }

    private void addItemList() {
        getItemList();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int index = getIndexNumber(watchImmovables.get(position));

                oneViewIntent.putExtra("immoIndex", index);

                startActivity(oneViewIntent);
            }
        });
    }

    private void getItemList() {
        ArrayList<Integer> favourites = splitFavouritesString();

        FileHelper fileHelper = new FileHelper();
        watchImmovables = fileHelper.readAllFavourites(WatchListActivity.this, favourites);
        ArrayList<String> items = new ArrayList();

        for (Immovable immo: watchImmovables) {
            items.add(immo.getName());
        }

        adapter = new CustomListAdapter(this, items.toArray(new String[0]), watchImmovables);
    }

    private ArrayList<Integer> splitFavouritesString() {
        ArrayList<Integer> favourites = new ArrayList<Integer>();

        if (favouriteString.contains(Constants.SEPERATOR) || favouriteString.length() > 0) {
            for (String favourite : favouriteString.split(Constants.SEPERATOR)) {
                favourites.add(Integer.parseInt(favourite));
            }
        }

        return favourites;
    }

    private int getIndexNumber(Immovable immovable) {
        FileHelper fileHelper = new FileHelper();
        watchImmovables = fileHelper.readAll(WatchListActivity.this);
        ArrayList<String> items = new ArrayList();
        int index = 0;

        for (Immovable immo: watchImmovables) {
            if (immo.getName().equals(immovable.getName())) {
                break;
            }
            index ++;
        }

        return index;
    }
}
