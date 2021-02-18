package com.filters_app.utility;

import android.content.Context;
import android.graphics.Bitmap;

import com.filters_app.ControlActivity;
import com.zomato.photofilters.imageprocessors.Filter;
import com.zomato.photofilters.imageprocessors.subfilters.BrightnessSubfilter;
import com.zomato.photofilters.imageprocessors.subfilters.ContrastSubfilter;
import com.zomato.photofilters.imageprocessors.subfilters.SaturationSubfilter;
import com.zomato.photofilters.imageprocessors.subfilters.VignetteSubfilter;

/**
 * Created by Sampath on 18-02-2021.
 */

public class TransformImage {

    public static final int MAX_BRIGHTNESS = 100;
    public static final int MAX_SATURATION = 5;
    public static final int MAX_CONTRAST = 100;
    public static final int MAX_VIGNETTE = 255;


    public static final int DEFAULT_BRIGNTESS = 70;
    public static final int DEFAULT_CONTRAST = 60;
    public static final int DEFAULT_SATURATION = 5;
    public static final int DEFAULT_VIGNETTE = 100;

    private String mFilename;
    private Bitmap mbitmap;
    private Context mcontext;

    private Bitmap brightnessFilteredBitmap;
    private Bitmap saturatedFilteredBitmap;
    private Bitmap contrastFilteredBitmap;
    private Bitmap vignetteFilteredBitmap;

    public static int FILTER_BRIGHTNESS = 0;
    public static int FILTER_SATURATION = 2;
    public static int FILTER_CONTRAST = 3;
    public static int FILTER_VIGNETTE = 1;

    public String getFilename(int filter){

       if(filter == FILTER_BRIGHTNESS) {
           return mFilename + "_brightness";
       } else if(filter == FILTER_VIGNETTE){
            return mFilename+"_vignette";
        }
        else if(filter == FILTER_SATURATION){
            return mFilename+"_saturation";
        }else if(filter == FILTER_CONTRAST){
            return mFilename+"_contrast";
        }
        return mFilename;
    }

    public Bitmap getBitmap(int filter){

        if(filter == FILTER_BRIGHTNESS) {
            return brightnessFilteredBitmap;
        } else if(filter == FILTER_VIGNETTE){
            return vignetteFilteredBitmap;
        }
        else if(filter == FILTER_SATURATION){
            return saturatedFilteredBitmap;
        }else if(filter == FILTER_CONTRAST){
            return contrastFilteredBitmap;
        }
        return mbitmap;
    }
    public TransformImage(Context context, Bitmap bitmap) {
        mbitmap = bitmap;
        mcontext = context;
        mFilename = System.currentTimeMillis() + "";
    }

    public Bitmap applyBrightnessSubFilter(int brightness){
        Filter myFilter = new Filter() ;

        Bitmap workingBitmap = Bitmap.createBitmap(mbitmap);
        Bitmap mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888,true);


        myFilter.addSubFilter(new BrightnessSubfilter(brightness));

        Bitmap outputImage = myFilter.processFilter(mutableBitmap);

        brightnessFilteredBitmap = outputImage;

        return outputImage;

    }
    public Bitmap applySaturationSubFilter(int saturation){
        Filter myFilter = new Filter() ;

        Bitmap workingBitmap = Bitmap.createBitmap(mbitmap);
        Bitmap mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888,true);


        myFilter.addSubFilter(new SaturationSubfilter(saturation));

        Bitmap outputImage = myFilter.processFilter(mutableBitmap);

        saturatedFilteredBitmap = outputImage;

        return outputImage;

    }
    public Bitmap applyVignetteSubFilter(int vignette){
        Filter myFilter = new Filter() ;

        Bitmap workingBitmap = Bitmap.createBitmap(mbitmap);
        Bitmap mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888,true);


        myFilter.addSubFilter(new VignetteSubfilter(mcontext,vignette));

        Bitmap outputImage = myFilter.processFilter(mutableBitmap);

        vignetteFilteredBitmap = outputImage;

        return outputImage;

    }


    public Bitmap applyContrastSubFilter(int contrast){
        Filter myFilter = new Filter() ;

        Bitmap workingBitmap = Bitmap.createBitmap(mbitmap);
        Bitmap mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888,true);


        myFilter.addSubFilter(new ContrastSubfilter(contrast));

        Bitmap outputImage = myFilter.processFilter(mutableBitmap);

        contrastFilteredBitmap = outputImage;

        return outputImage;

    }

}


