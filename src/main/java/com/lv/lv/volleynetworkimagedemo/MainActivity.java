package com.lv.lv.volleynetworkimagedemo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;


public class MainActivity extends ActionBarActivity {
    /**
     * NetworkImageView的用法
     * NetworkImageView是一个自定义控制，它是继承自ImageView的，具备ImageView控件的所有功能，
     * 并且在原生的基础之上加入了加载网络图片的功能。NetworkImageView控件的用法要比前两种方式更加简单，
     * 大致可以分为以下五步：
     * 1. 创建一个RequestQueue对象。
     * 2. 创建一个ImageLoader对象。
     * 3. 在布局文件中添加一个NetworkImageView控件。
     * 4. 在代码中获取该控件的实例。
     * 5. 设置要加载的图片地址。
     */
    private RequestQueue requestQueue;
    private NetworkImageView networkImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(this);
        networkImageView = (NetworkImageView) findViewById(R.id.imageView);
    }

    public void onClick(View view) {
        //得到一个处理远程图片和缓存的对象imageloader
        ImageLoader imageLoader = new ImageLoader(requestQueue, new MyBitmapCache(this));
        //设置过渡图片
        networkImageView.setDefaultImageResId(R.drawable.ic_launcher);
        //设置加载失败的图片
        networkImageView.setErrorImageResId(R.drawable.ic_launcher);
        //设置图片的下载链接和imageloader
        networkImageView.setImageUrl("http://f.hiphotos.baidu.com/image/pic/item/d043ad4bd11373f0fff03580a60f4bfbfaed04e5.jpg", imageLoader);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
