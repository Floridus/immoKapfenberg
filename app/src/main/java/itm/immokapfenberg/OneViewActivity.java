package itm.immokapfenberg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;

public class OneViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_one_view);

        Intent i = getIntent();
        int index = (int) i.getIntExtra("immoIndex", 0);

        Log.i("DEBUG Immo", "" + index);
    }
}

