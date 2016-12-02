package itm.immokapfenberg.helper;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FileHelper {
    final static String TAG = FileHelper.class.getName();

    public static ArrayList<Immovable> readAll(Context context){
        ArrayList<Immovable> immos = new ArrayList();
        String line = "";

        try {
            InputStream inputStream = context.getAssets().open("data.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            while ((line = bufferedReader.readLine()) != null) {
                String[] dataSet = line.split(";");
                String[] otherImgUrls = dataSet[4].split(",");

                Immovable immovable = new Immovable(
                        dataSet[0],
                        dataSet[1],
                        Float.parseFloat(dataSet[2]),
                        Float.parseFloat(dataSet[3]),
                        otherImgUrls
                );
                immos.add(immovable);
            }
            inputStream.close();
            bufferedReader.close();
        } catch(FileNotFoundException ex) {
            Log.d(TAG, ex.getMessage());
        } catch(IOException ex) {
            Log.d(TAG, ex.getMessage());
        }

        return immos;
    }

    public static Immovable readOneById(Context context, int index){
        Immovable immo = new Immovable();
        String line = "";
        int i = 0;

        try {
            InputStream inputStream = context.getAssets().open("data.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            while ((line = bufferedReader.readLine()) != null) {
                if (i == index) {
                    String[] dataSet = line.split(";");

                    String[] otherImgUrls = dataSet[4].split(",");

                    immo.setName(dataSet[0]);
                    immo.setImgUrl(dataSet[1]);
                    immo.setRating(Float.parseFloat(dataSet[2]));
                    immo.setPrice(Float.parseFloat(dataSet[3]));
                    immo.setOtherImgUrls(otherImgUrls);

                    break;
                }

                i++;
            }
            inputStream.close();
            bufferedReader.close();
        } catch(FileNotFoundException ex) {
            Log.d(TAG, ex.getMessage());
        } catch(IOException ex) {
            Log.d(TAG, ex.getMessage());
        }

        return immo;
    }
}
