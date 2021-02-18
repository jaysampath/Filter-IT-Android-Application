package com.filters_app.utility;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v4.content.ContextCompat;

import com.filters_app.R;

import java.io.*;


/**
 * Created by Sampath on 18-02-2021.
 */

public class Helper {
    public static Boolean writeDataintoExternalStorage(Context context, String filename, Bitmap bitmap){

        File diectory = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+context.getString(R.string.app_name));

        if(!diectory.exists() && !diectory.mkdir())
        {
          return false;
        }
        File file = new File(diectory.getAbsolutePath()+"/"+filename);
        if(file.exists() &&  !file.canWrite()){
            return false;
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            return bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static File getFilefromExternalStorage(Context context,String filename){
        File diectory = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+context.getString(R.string.app_name));

        File file = new File(diectory.getAbsolutePath()+"/"+filename);

        if(!file.exists() || !file.canRead()){
            return null;
        }
        return file;

    }


}
