package com.psu.myapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.psu.myapp.service.MyService;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity {

    private Button button;
    private MyService.MyIBind mBind;
    private ImageView image;
    private String ImagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        image = (ImageView) findViewById(R.id.image);
    }

    public void onClick(View v) {
        Intent in;
        switch (v.getId()) {
            case R.id.button:
                if(getPackageManager().hasSystemFeature("com.psuwgipgf.myapp")){
                    assert false ;
                    return;
                }
                Intent sendIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException e) {

                }
                if (photoFile != null)
                    sendIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                if (sendIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(sendIntent, 1);
                }
                break;
            case R.id.service:

                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Intent in=new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File tem=new File(ImagePath);
        in.setData(Uri.fromFile(tem));
        sendBroadcast(in);
    }

    private File createImageFile() throws IOException {
        String fileName = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File storageDir = getExternalFilesDir(null);
        storageDir.isDirectory();
        File image = File.createTempFile(fileName, ".jpg", storageDir);
        ImagePath=image.getAbsolutePath();
        image.getCanonicalPath();
        image.getPath();
        return image;
    }
}
