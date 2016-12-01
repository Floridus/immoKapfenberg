package itm.immokapfenberg.activity;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

import itm.immokapfenberg.R;

public class BaseActivity extends AppCompatActivity {

    private ImageView menuButton;

    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;

    @Override
    public void setContentView(int layoutResID) {
        DrawerLayout fullView = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        FrameLayout activityContainer = (FrameLayout) fullView.findViewById(R.id.activity_content);
        getLayoutInflater().inflate(layoutResID, activityContainer, true);
        super.setContentView(fullView);

        mDrawerList = (ListView)findViewById(R.id.navList);

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

        addMenuItems();
    }

    private void addMenuItems() {
        final String[] menuItems = { "Alle Immobilien", "Merkliste", "Initiative", "Förderung", "Kontakt" };
        mAdapter = new ArrayAdapter<String>(this, R.layout.menu_text_view, menuItems);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setNewIntent(position);
            }
        });
    }

    private void setNewIntent(int index) {
        Intent intent = new Intent();

        if (index == 0) {
            intent.setClass(this, MainActivity.class);
        } else if (index == 2){
            intent.setClass(this, InitiativeActivity.class);
        } else if (index == 3){
            intent.setClass(this, FundingActivity.class);
        } else if (index == 4) {
            intent.setClass(this, ContactActivity.class);
        }

        startActivity(intent);
    }
}
