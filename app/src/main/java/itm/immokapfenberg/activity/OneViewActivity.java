package itm.immokapfenberg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;

import itm.immokapfenberg.R;
import itm.immokapfenberg.helper.Constants;
import itm.immokapfenberg.helper.FileHelper;
import itm.immokapfenberg.helper.Immovable;
import itm.immokapfenberg.helper.OnSwipeTouchListener;

public class OneViewActivity extends BaseActivity {

    private Immovable immovable;

    private TextView priceInfo;
    private ImageView immoImage;
    private TextView immoName;
    private RatingBar ratingResult;

    private LinearLayout imageStepLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_one_view);

        Intent i = getIntent();
        int index = (int) i.getIntExtra("immoIndex", 0);

        FileHelper fileHelper = new FileHelper();
        immovable = fileHelper.readOneById(this, index);

        priceInfo = (TextView)findViewById(R.id.priceInfo);
        immoName = (TextView)findViewById(R.id.immoName);
        ratingResult = (RatingBar)findViewById(R.id.ratingResult);
        immoImage = (ImageView)findViewById(R.id.immoImage);

        setImage(immovable.getImgUrl());

        final ArrayList<String> imgUrls = new ArrayList<String>();
        imgUrls.add(immovable.getImgUrl());

        imageStepLayout = (LinearLayout)findViewById(R.id.imageStepLayout);
        imageStepLayout.removeAllViewsInLayout();

        addImageView(true);

        for (String url: immovable.getOtherImgUrls()) {
            imgUrls.add(url);
            addImageView(false);
        }

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String moneyString = formatter.format(immovable.getPrice());

        immoName.setText(immovable.getName());
        priceInfo.setText(moneyString);
        ratingResult.setRating(immovable.getRating());

        // Photoswipe is only active if there are more than one photo
        if (imgUrls.size() > 1) {
            immoImage.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()) {
                int imgIndex = 0;

                public void onSwipeRight() {
                    changeImageView(imgIndex, false);

                    imgIndex--;
                    if (imgIndex < 0) {
                        imgIndex = imgUrls.size() - 1;
                    }

                    changeImageView(imgIndex, true);

                    setImage(imgUrls.get(imgIndex));
                }

                public void onSwipeLeft() {
                    changeImageView(imgIndex, false);

                    imgIndex++;
                    if (imgIndex > (imgUrls.size() - 1)) {
                        imgIndex = 0;
                    }

                    changeImageView(imgIndex, true);

                    setImage(imgUrls.get(imgIndex));
                }
            });
        }
    }

    public void toContact(View view){
        Toast.makeText(this, "Kontakt", Toast.LENGTH_SHORT).show();
        Intent intentContact = new Intent(this, ContactActivity.class);
        startActivity(intentContact);
    }

    private void setImage(String url) {
        Picasso.with(getApplicationContext()).load(Constants.API_URL + url).into(immoImage);
    }

    private void addImageView(boolean isActive) {
        ImageView myImage = new ImageView(this);

        if (isActive) {
            myImage.setImageResource(R.drawable.ic_circle);
        } else {
            myImage.setImageResource(R.drawable.ic_circle_inactive);
        }

        imageStepLayout.addView(myImage);
    }

    private void changeImageView(int index, boolean toActive) {
        ImageView view = (ImageView) imageStepLayout.getChildAt(index);
        if (toActive) {
            view.setImageResource(R.drawable.ic_circle);
        } else {
            view.setImageResource(R.drawable.ic_circle_inactive);
        }
    }
}
