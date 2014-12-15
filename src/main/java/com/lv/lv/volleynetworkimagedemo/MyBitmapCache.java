package com.lv.lv.volleynetworkimagedemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;

/**
 * bitmap缓存类
 * Created by lv on 2014/12/11.
 */
public class MyBitmapCache implements ImageLoader.ImageCache {

    private LruCache<String,Bitmap> bitmapLruCache;

    public MyBitmapCache(final Context context){
        //给予10M缓存
        int maxSize =  10*1024*1024;
        bitmapLruCache = new LruCache<String,Bitmap>(maxSize){
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                //返回图片所占的byte=bitmap每行所占的byte数*bitmap的高度
                Toast.makeText(context,bitmap.getRowBytes()*bitmap.getHeight()+"",Toast.LENGTH_LONG).show();
                return bitmap.getRowBytes()*bitmap.getHeight();
            }
        };
    }
    @Override
    //从缓存中取bitmap
    public Bitmap getBitmap(String url) {
        return bitmapLruCache.get(url);
    }

    @Override
    //将bitmap添加到缓存中
    public void putBitmap(String url, Bitmap bitmap) {
        if (getBitmap(url)==null){
            bitmapLruCache.put(url,bitmap);
        }
    }
}
