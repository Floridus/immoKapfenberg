package itm.immokapfenberg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> itemList;
    private ArrayList<Integer> imgList;
    private CustomListAdapter adapter;
    private ListView listView;
    private ImageView menuButton;

    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;

    private Intent contactIntent;
    private Intent oneViewIntent;
    private Intent initiativeIntent;
    private Intent fundingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        mDrawerList = (ListView)findViewById(R.id.navList);
        listView = (ListView)findViewById(R.id.immoList);

        menuButton = (ImageView)findViewById(R.id.menuButton);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawerList.getVisibility() == View.VISIBLE) {
                    mDrawerList.setVisibility(View.INVISIBLE);
                } else if (mDrawerList.getVisibility() == View.INVISIBLE) {
                    mDrawerList.setVisibility(View.VISIBLE);
                }
            }
        });

        contactIntent = new Intent(this, Contact.class);
        oneViewIntent = new Intent(this, OneViewActivity.class);
        initiativeIntent = new Intent(this, Initiative.class);
        fundingIntent = new Intent(this, Funding.class);

        addSubMenuItems();
        addItemList();
    }

    private void addItemList() {
        String[] items = {
                "High-Tech-Park Kapfenberg",
                "Schmiedgasse 2",
                "Linke Mürzzeile 19 a"
        };

        Integer[] imageId = {
                R.mipmap.img_high_tech_park,
                R.mipmap.img_schmiedgasse,
                R.mipmap.img_linke_muerzzeile
        };

        adapter = new CustomListAdapter(this, items, imageId);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                oneViewIntent.putExtra("immoIndex", position);

                startActivity(oneViewIntent);
            }
        });
    }

    private void addSubMenuItems() {
        final String[] menuItems = { "Alle Immobilien", "Merkliste", "Initiative", "Förderung", "Kontakt" };
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuItems);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    mDrawerList.setVisibility(View.INVISIBLE);
                } else if (position == 4) {
                    startActivity(contactIntent);
                } else if (position == 2){
                    startActivity(initiativeIntent);
                } else if (position == 3){
                    startActivity(fundingIntent);
                }

                // position oder id ist Index von menuItems Array
                Toast.makeText(MainActivity.this, menuItems[position], Toast.LENGTH_SHORT).show();
            }
        });
    }
}
