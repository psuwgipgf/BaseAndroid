package com.psu.myapp.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.psu.myapp.R;

import java.lang.ref.WeakReference;

/**
 * Created by psu on 2016/3/10.
 */
public class ImageLoad extends AsyncTask<Integer, Integer, Bitmap> {

    private final WeakReference<ImageView> imageViewWeakReference;
    private int data = 0;
    private Context mContext;

    public ImageLoad(Context context,ImageView v) {
        imageViewWeakReference = new WeakReference<ImageView>(v);
        mContext=context;
    }

    @Override
    protected Bitmap doInBackground(Integer[] params) {
        data = params[0];
        return init(data);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(imageViewWeakReference!=null&&bitmap!=null){
            ImageView imageView=imageViewWeakReference.get();
            if(imageView!=null){
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    private int imageScall(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int width = options.outWidth;
        int height = options.outHeight;
        int resultScall = 1;
        if (width > reqWidth || height > reqHeight) {
            int helfWidth = width / 2;
            int helfHeight = height / 2;
            while ((helfWidth / resultScall) > reqWidth && (helfHeight / resultScall) > reqHeight) {
                resultScall *= 2;
            }
        }
        return resultScall;
    }

    private Bitmap init(Integer i){
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeResource(mContext.getResources(),i,options);
        options.inSampleSize=imageScall(options,100,100);
        options.inJustDecodeBounds=false;
        Bitmap bitmap=BitmapFactory.decodeResource(mContext.getResources(),R.drawable.asdlfjei,options);
        return  bitmap;
    }
}
