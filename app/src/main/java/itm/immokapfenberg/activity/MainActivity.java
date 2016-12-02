package itm.immokapfenberg.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.immoList);

        searchFilter = (ImageButton)findViewById(R.id.searchFilter);

        searchFilter.setVisibility(View.VISIBLE);

        searchFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Filter")
                        .setMessage("This is a very fancy alert box.")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .show();
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
