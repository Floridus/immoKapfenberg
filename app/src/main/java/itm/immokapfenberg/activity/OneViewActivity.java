package itm.immokapfenberg.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.util.ArrayList;

import itm.immokapfenberg.R;
import itm.immokapfenberg.helper.Constants;
import itm.immokapfenberg.helper.FileHelper;
import itm.immokapfenberg.helper.Immovable;
import itm.immokapfenberg.helper.OnSwipeTouchListener;

public class OneViewActivity extends BaseActivity {

    private Immovable immovable;
    private int immoIndex;

    private ImageView favoriteStar;
    private TextView priceInfo;
    private ImageView immoImage;
    private TextView immoName;
    private RatingBar ratingResult;

    private LinearLayout imageStepLayout;

    private String favouriteString;
    private boolean isFavourite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_one_view);

        Intent i = getIntent();
        immoIndex = (int) i.getIntExtra("immoIndex", 0);

        FileInputStream fis = null;
        favouriteString = "";
        try {
            fis = openFileInput(Constants.STORAGE_FAVOURITE);
            int c;
            while ((c = fis.read()) != -1) {
                favouriteString += Character.toString((char)c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        isFavourite = searchIndexInFavourites(immoIndex);

        FileHelper fileHelper = new FileHelper();
        immovable = fileHelper.readOneById(this, immoIndex);

        favoriteStar = (ImageView)findViewById(R.id.favoriteStar);
        priceInfo = (TextView)findViewById(R.id.priceInfo);
        immoName = (TextView)findViewById(R.id.immoName);
        ratingResult = (RatingBar)findViewById(R.id.ratingResult);
        immoImage = (ImageView)findViewById(R.id.immoImage);

        favoriteStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> favourites = splitFavouritesString();

                if (isFavourite) {
                    isFavourite = false;

                    favourites.remove(Integer.valueOf(immoIndex));
                    favoriteStar.setImageResource(R.drawable.ic_star_inactive);
                } else {
                    isFavourite = true;

                    favourites.add(immoIndex);
                    favoriteStar.setImageResource(R.drawable.ic_star_active);
                }

                favouriteString = "";
                for (int index: favourites) {
                    if (!favouriteString.equals("")) {
                        favouriteString += Constants.SEPERATOR;
                    }
                    favouriteString += index;
                }

                FileOutputStream fos = null;
                try {
                    fos = openFileOutput(Constants.STORAGE_FAVOURITE, Context.MODE_PRIVATE);
                    fos.write(favouriteString.getBytes());
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        setImage(immovable.getImgUrl());

        if (isFavourite) {
            favoriteStar.setImageResource(R.drawable.ic_star_active);
        } else {
            favoriteStar.setImageResource(R.drawable.ic_star_inactive);
        }

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

    public void toContact(View view) {
        Intent intentContact = new Intent(this, ContactActivity.class);
        startActivity(intentContact);
    }

    public void makeFavorite(View view) {
        ArrayList<Integer> favourites = splitFavouritesString();

        if (isFavourite) {
            favourites.remove(immoIndex);
            favoriteStar.setImageResource(R.drawable.ic_star_inactive);
        } else {
            favourites.add(immoIndex);
            favoriteStar.setImageResource(R.drawable.ic_star_active);
        }

        isFavourite = !isFavourite;

        favouriteString = "";
        for (int index: favourites) {
            if (!favouriteString.equals("")) {
                favouriteString += Constants.SEPERATOR;
            }
            favouriteString += index;
        }

        FileOutputStream fos = null;
        try {
            fos = openFileOutput(Constants.STORAGE_FAVOURITE, Context.MODE_PRIVATE);
            fos.write(favouriteString.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Integer> splitFavouritesString() {
        ArrayList<Integer> favourites = new ArrayList<Integer>();

        if (favouriteString.contains(Constants.SEPERATOR) || favouriteString.length() > 0) {
            for (String favourite : favouriteString.split(Constants.SEPERATOR)) {
                favourites.add(Integer.parseInt(favourite));
            }
        }

        return favourites;
    }

    private boolean searchIndexInFavourites(int index) {
        ArrayList<Integer> favourites = splitFavouritesString();

        if (favourites.size() > 0) {
            return favourites.contains(index);
        } else {
            return false;
        }
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
