package itm.immokapfenberg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

/**
 * Created by Christoph Hofer on 01.12.2016.
 */

public class OneViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_one_view);
    }
    public void Start2nd (View view){
        Intent intent2nd;

        intent2nd =new Intent(this,CustomListAdapter.class);
        startActivity(intent2nd);


    }
}
