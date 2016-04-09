package com.example.stronger.androidcamera;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_OPEN = 1;
    private static final boolean swither= true;
    private ImageView view;
    private File currentImageFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = (ImageView) findViewById(R.id.photo_image);
    }

    public void onOpenCameraClick(View view) {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (swither) {
            //原来的写法:        Uri uri = Uri.fromFile(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM));
            //当要指定相机生成的路径是必须指定具体的生成路径和文件名，而不能指定文件夹而已
            this.currentImageFile = getStoreFile();
            Uri uri = Uri.fromFile(this.currentImageFile);

            intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        }
        startActivityForResult(intent,CAMERA_OPEN);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode){
                case CAMERA_OPEN:
                    //当不指定文件夹时，返回的intent不为空，但这是为了防止返回Bitmap占用内存过大
                    //对于返回的结果进行了压缩，压缩后的图片变得很小，通过之前说的getData的方式只能满足比如显示个头像这样的需求
                    if (data != null) {
                        Uri uri = data.getData();
                        Bundle bundle = data.getExtras();
                        Bitmap bitmap = (Bitmap) bundle.get("data");
                        view.setImageBitmap(bitmap);
                    }else{
                        //当手动指定文件夹的时候，安卓系统就不会把生成的照片信息通过intent返回到当前
                        //Activity中，返回的intent就为null，这是就得从文件中读取
                        //从文件中读取图片
                        //获取清晰的大图的方法
                        Bitmap bitmap = BitmapFactory.decodeFile(this.currentImageFile.getPath());//有可能会造成内存泄漏
                        // TODO: 2016/4/9  可以进行压缩后在进行展示或上传到服务器
                        view.setImageBitmap(bitmap);
                    }
            }
        }
    }

    private File getStoreFile(){
        String filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath()
                +"Lab_"+System.currentTimeMillis()+".jpg";

        //有关外部存储的文件操作在进行之前都要进行存储卡是否挂载坚持
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file = new File(filePath);
            return file;
        }
        return null;
    }

    public void onNextClick(View view) {
        startActivity(new Intent(this,GalleryActivity.class));
    }


    //以上例子说明了
    /**
     * 1.在默认情况下调用系统的相机拍照并不会自动在本地存储下照片
     * 2.所有在返回的intent中getData返回的Uri为空
     */


    /**
     * 压缩从文件中读取的图片
     */
    public static Bitmap getScaleBitmap(Context ctx, String filePath) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(filePath, opt);

        int bmpWidth = opt.outWidth;
        int bmpHeght = opt.outHeight;

        WindowManager windowManager = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        int screenWidth = display.getWidth();
        int screenHeight = display.getHeight();

        opt.inSampleSize = 1;
        if (bmpWidth > bmpHeght) {
            if (bmpWidth > screenWidth)
                opt.inSampleSize = bmpWidth / screenWidth;
        } else {
            if (bmpHeght > screenHeight)
                opt.inSampleSize = bmpHeght / screenHeight;
        }
        opt.inJustDecodeBounds = false;

        bmp = BitmapFactory.decodeFile(filePath, opt);
        return bmp;
    }
}
