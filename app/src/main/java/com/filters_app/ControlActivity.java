package com.filters_app;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.filters_app.utility.Helper;
import com.filters_app.utility.TransformImage;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import static com.filters_app.R.id.centralimage;

public class ControlActivity extends AppCompatActivity {
    static
    {
        System.loadLibrary("NativeImageProcessor");
    }

    Toolbar mcontroltoolbar;
    ImageView mtickimageView;
    ImageView mcenterimage;
    TransformImage mTransformImage;
    int mScreenwidth;
    int mScreenheight;
    Uri mSelectedImageUri;

    int mCurrentFilter;

    SeekBar mSeekbar;

    ImageView mCancelImageView;


    Target mApplySingleTarget = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            int currentFilterValue = mSeekbar.getProgress();
            if(mCurrentFilter == TransformImage.FILTER_BRIGHTNESS){
                mTransformImage.applyBrightnessSubFilter(currentFilterValue);

                Helper.writeDataintoExternalStorage(ControlActivity.this,mTransformImage.getFilename(TransformImage.FILTER_BRIGHTNESS),mTransformImage.getBitmap(TransformImage.FILTER_BRIGHTNESS));
               Picasso.with(ControlActivity.this).invalidate(Helper.getFilefromExternalStorage(ControlActivity.this,mTransformImage.getFilename(TransformImage.FILTER_BRIGHTNESS)));
                Picasso.with(ControlActivity.this).load(Helper.getFilefromExternalStorage(ControlActivity.this,mTransformImage.getFilename(TransformImage.FILTER_BRIGHTNESS))).resize(0,mScreenheight/2).into(mcenterimage);

            }else if(mCurrentFilter == TransformImage.FILTER_SATURATION){
                mTransformImage.applySaturationSubFilter(currentFilterValue);

                Helper.writeDataintoExternalStorage(ControlActivity.this,mTransformImage.getFilename(TransformImage.FILTER_SATURATION),mTransformImage.getBitmap(TransformImage.FILTER_SATURATION));
                Picasso.with(ControlActivity.this).invalidate(Helper.getFilefromExternalStorage(ControlActivity.this,mTransformImage.getFilename(TransformImage.FILTER_SATURATION)));
                Picasso.with(ControlActivity.this).load(Helper.getFilefromExternalStorage(ControlActivity.this,mTransformImage.getFilename(TransformImage.FILTER_SATURATION))).resize(0,mScreenheight/2).into(mcenterimage);

            }else if(mCurrentFilter == TransformImage.FILTER_CONTRAST){
                mTransformImage.applyContrastSubFilter(currentFilterValue);

                Helper.writeDataintoExternalStorage(ControlActivity.this,mTransformImage.getFilename(TransformImage.FILTER_CONTRAST),mTransformImage.getBitmap(TransformImage.FILTER_CONTRAST));
                Picasso.with(ControlActivity.this).invalidate(Helper.getFilefromExternalStorage(ControlActivity.this,mTransformImage.getFilename(TransformImage.FILTER_CONTRAST)));
                Picasso.with(ControlActivity.this).load(Helper.getFilefromExternalStorage(ControlActivity.this,mTransformImage.getFilename(TransformImage.FILTER_CONTRAST))).resize(0,mScreenheight/2).into(mcenterimage);

            }else if(mCurrentFilter == TransformImage.FILTER_VIGNETTE){
                mTransformImage.applyVignetteSubFilter(currentFilterValue);

                Helper.writeDataintoExternalStorage(ControlActivity.this,mTransformImage.getFilename(TransformImage.FILTER_VIGNETTE),mTransformImage.getBitmap(TransformImage.FILTER_VIGNETTE));
                Picasso.with(ControlActivity.this).invalidate(Helper.getFilefromExternalStorage(ControlActivity.this,mTransformImage.getFilename(TransformImage.FILTER_VIGNETTE)));
                Picasso.with(ControlActivity.this).load(Helper.getFilefromExternalStorage(ControlActivity.this,mTransformImage.getFilename(TransformImage.FILTER_VIGNETTE))).resize(0,mScreenheight/2).into(mcenterimage);

            }


        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };

    Target mSmallTarget = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

            mTransformImage = new TransformImage(ControlActivity.this,bitmap);
            mTransformImage.applyBrightnessSubFilter(TransformImage.DEFAULT_BRIGNTESS);

            Helper.writeDataintoExternalStorage(ControlActivity.this,mTransformImage.getFilename(TransformImage.FILTER_BRIGHTNESS),mTransformImage.getBitmap(TransformImage.FILTER_BRIGHTNESS));
            Picasso.with(ControlActivity.this).load(Helper.getFilefromExternalStorage(ControlActivity.this,mTransformImage.getFilename(TransformImage.FILTER_BRIGHTNESS))).fit().centerInside().into(mFirstFilterPreviewImageView);

            //
            mTransformImage.applySaturationSubFilter(TransformImage.DEFAULT_SATURATION);
            Helper.writeDataintoExternalStorage(ControlActivity.this,mTransformImage.getFilename(TransformImage.FILTER_SATURATION),mTransformImage.getBitmap(TransformImage.FILTER_SATURATION));
            Picasso.with(ControlActivity.this).load(Helper.getFilefromExternalStorage(ControlActivity.this,mTransformImage.getFilename(TransformImage.FILTER_SATURATION))).fit().centerInside().into(mSecondFilterPreviewImageView);

            //
            mTransformImage.applyContrastSubFilter(TransformImage.DEFAULT_CONTRAST);
            Helper.writeDataintoExternalStorage(ControlActivity.this,mTransformImage.getFilename(TransformImage.FILTER_CONTRAST),mTransformImage.getBitmap(TransformImage.FILTER_CONTRAST));
            Picasso.with(ControlActivity.this).load(Helper.getFilefromExternalStorage(ControlActivity.this,mTransformImage.getFilename(TransformImage.FILTER_CONTRAST))).fit().centerInside().into(mThirdFilterPreviewImageView);

            //
            mTransformImage.applyVignetteSubFilter(TransformImage.DEFAULT_VIGNETTE);
            Helper.writeDataintoExternalStorage(ControlActivity.this,mTransformImage.getFilename(TransformImage.FILTER_VIGNETTE),mTransformImage.getBitmap(TransformImage.FILTER_VIGNETTE));
            Picasso.with(ControlActivity.this).load(Helper.getFilefromExternalStorage(ControlActivity.this,mTransformImage.getFilename(TransformImage.FILTER_VIGNETTE))).fit().centerInside().into(mFourthFilterPreviewImageView);

        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };

    final static int PICK_IMAGE = 2;
    final static int MY_PERMISSIONS_REQUEST_STORAGE_PERMESSION = 3;
    ImageView mFirstFilterPreviewImageView;
    ImageView mSecondFilterPreviewImageView;
    ImageView mThirdFilterPreviewImageView;
    ImageView mFourthFilterPreviewImageView;
    private static final String TAG = ControlActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        mcontroltoolbar = (Toolbar) findViewById(R.id.toolbar2);
        mcenterimage = (ImageView) findViewById(centralimage);
        mSeekbar = (SeekBar) findViewById(R.id.seekBar) ;



        mcontroltoolbar.setTitle(getString(R.string.app_name));
        mcontroltoolbar.setNavigationIcon(R.drawable.appicon);


      //  mcontroltoolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));

        mFirstFilterPreviewImageView = (ImageView) findViewById(R.id.imageView11) ;
        mSecondFilterPreviewImageView = (ImageView)findViewById(R.id.imageView12) ;
        mThirdFilterPreviewImageView = (ImageView) findViewById(R.id.imageView13);
        mFourthFilterPreviewImageView = (ImageView) findViewById(R.id.imageView15);


        mtickimageView = (ImageView) findViewById(R.id.imageView4);
        mtickimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ControlActivity.this,ImagePreviewActivity.class);
                startActivity(intent);
            }
        });

        mcenterimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requestStoragePermission();
                if(ContextCompat.checkSelfPermission(ControlActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    return;
                }

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"),PICK_IMAGE);
            }
        });

        mFirstFilterPreviewImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSeekbar.setMax(TransformImage.MAX_BRIGHTNESS);
                mSeekbar.setProgress(TransformImage.DEFAULT_BRIGNTESS);
                mCurrentFilter = TransformImage.FILTER_BRIGHTNESS;
                Picasso.with(ControlActivity.this).load(Helper.getFilefromExternalStorage(ControlActivity.this,mTransformImage.getFilename(TransformImage.FILTER_BRIGHTNESS))).resize(0,mScreenheight/2).into(mcenterimage);

            }
        });

        mSecondFilterPreviewImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSeekbar.setMax(TransformImage.MAX_SATURATION);
                mSeekbar.setProgress(TransformImage.DEFAULT_SATURATION);
                mCurrentFilter = TransformImage.FILTER_SATURATION;
                Picasso.with(ControlActivity.this).load(Helper.getFilefromExternalStorage(ControlActivity.this,mTransformImage.getFilename(TransformImage.FILTER_SATURATION))).resize(0,mScreenheight/2).into(mcenterimage);

            }
        });

        mThirdFilterPreviewImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSeekbar.setMax(TransformImage.MAX_CONTRAST);
                mSeekbar.setProgress(TransformImage.DEFAULT_CONTRAST);
                mCurrentFilter = TransformImage.FILTER_CONTRAST;
                Picasso.with(ControlActivity.this).load(Helper.getFilefromExternalStorage(ControlActivity.this,mTransformImage.getFilename(TransformImage.FILTER_CONTRAST))).resize(0,mScreenheight/2).into(mcenterimage);

            }
        });

        mFourthFilterPreviewImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSeekbar.setMax(TransformImage.MAX_VIGNETTE);
                mSeekbar.setProgress(TransformImage.DEFAULT_VIGNETTE);
                mCurrentFilter = TransformImage.FILTER_VIGNETTE;
                Picasso.with(ControlActivity.this).load(Helper.getFilefromExternalStorage(ControlActivity.this,mTransformImage.getFilename(TransformImage.FILTER_VIGNETTE))).resize(0,mScreenheight/2).into(mcenterimage);

            }
        });



      mtickimageView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

                  Picasso.with(ControlActivity.this).load(mSelectedImageUri).into(mApplySingleTarget);

          }
      });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        mScreenheight = displayMetrics.heightPixels;
        mScreenwidth = displayMetrics.widthPixels;


    }

    public void onRequestPermissionsResults(int requestCode, String[] permission, int[] grantresults){
        switch (requestCode){
            case MY_PERMISSIONS_REQUEST_STORAGE_PERMESSION:
                if(grantresults.length>0 && grantresults[0] == PackageManager.PERMISSION_GRANTED){
                    new MaterialDialog.Builder(ControlActivity.this).title("Permission Required")
                            .content("Thankyou for providing Access")
                            .positiveText("OK").canceledOnTouchOutside(true).show();
                }else{
                    Log.d(TAG,"Permission Denied");
                }
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,  Intent data){
        if(requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK)
        {
             mSelectedImageUri = data.getData();
            // -
            Picasso.with(ControlActivity.this).load(mSelectedImageUri).fit().centerInside().into(mcenterimage);
            Picasso.with(ControlActivity.this).load(mSelectedImageUri).into(mSmallTarget);



        }

    }

    public void requestStoragePermission(){
        if(ContextCompat.checkSelfPermission(ControlActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(ControlActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE))
            {
                // show a message
                new MaterialDialog.Builder(ControlActivity.this).title(R.string.permission_title).content(R.string.permission_control)
                        .negativeText(R.string.Permission_cancel)
                        .positiveText(R.string.Permission_agree_settings)
                        .canceledOnTouchOutside(true)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                startActivityForResult(new Intent(Settings.ACTION_SETTINGS),0);
                            }
                        })
                        .show();

            }
            else
            {
                ActivityCompat.requestPermissions(ControlActivity.this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},MY_PERMISSIONS_REQUEST_STORAGE_PERMESSION);
            }

        }

    }

}
