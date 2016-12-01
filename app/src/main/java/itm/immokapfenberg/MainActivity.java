package itm.immokapfenberg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> itemList;
    private ArrayList<Integer> imgList;
    private CustomListAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_one_view);

       /* setContentView(R.layout.activity_main);

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

        listView = (ListView)findViewById(R.id.immoList);

        adapter = new CustomListAdapter(this, items, imageId);
        //adapter = new ArrayAdapter<String>(this, R.layout.list_view, R.id.mainName, itemList);

        listView.setAdapter(adapter);
*/
    }
}
