package itm.immokapfenberg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import itm.immokapfenberg.R;
import itm.immokapfenberg.helper.CustomListAdapter;
import itm.immokapfenberg.helper.FileHelper;
import itm.immokapfenberg.helper.Immovable;

public class MainActivity extends BaseActivity {

    private CustomListAdapter adapter;
    private ListView listView;

    private Intent oneViewIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.immoList);

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
