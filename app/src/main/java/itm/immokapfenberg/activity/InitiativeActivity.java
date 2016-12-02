package itm.immokapfenberg.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import itm.immokapfenberg.R;
import itm.immokapfenberg.helper.Constants;

public class InitiativeActivity extends BaseActivity {

    private ImageView iniativeImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initiative);

        iniativeImg = (ImageView)findViewById(R.id.iniativeImg);

        Picasso.with(getApplicationContext()).load(Constants.INITATIVE_URL).into(iniativeImg);
    }
}
