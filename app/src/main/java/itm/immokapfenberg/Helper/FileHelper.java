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
        int imgId = 0;

        try {
            InputStream inputStream = context.getAssets().open("data.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            while ((line = bufferedReader.readLine()) != null) {
                String[] dataSet = line.split(";");
                imgId = context.getResources().getIdentifier(dataSet[1], "mipmap", context.getPackageName());
                Immovable immovable = new Immovable(dataSet[0], imgId, Float.parseFloat(dataSet[2]), Float.parseFloat(dataSet[3]));
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
}
