package com.codeminator.attndr;

import android.app.Activity;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by naman on 19/03/16.
 */
public class Utils {

    public String loadJSONFromAsset(Activity activity) {

        String jsonString = "";
        try {
            InputStream is = activity.getAssets().open("students.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonString = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();

        }
        return jsonString;
    }

}
