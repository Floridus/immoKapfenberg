package itm.immokapfenberg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import itm.immokapfenberg.R;

public class OneViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_one_view);

        Intent i = getIntent();
        int index = (int) i.getIntExtra("immoIndex", 0);

        Log.i("DEBUG Immo", "" + index);
    }
    public void kontaktieren(View view){
        Toast.makeText(this, "Kontakt", Toast.LENGTH_SHORT).show();
        startContact();
    }
    public void startContact() {
        Intent intentContact =new Intent(this,ContactActivity.class);
        startActivity(intentContact);
    }
}
