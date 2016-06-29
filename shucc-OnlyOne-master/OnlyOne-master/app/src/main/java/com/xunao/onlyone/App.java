package com.xunao.onlyone;

import android.app.Application;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.xunao.onlyone.util.RSAUtil;
import com.xunao.onlyone.util.SPUtil;

import org.litepal.LitePalApplication;

import java.io.IOException;
import java.io.InputStream;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Created by chenchao on 16/5/27.
 * cc@cchao.org
 */
public class App extends Application {

    private static App instace;

    private static SPUtil spUtil;

    private static DisplayImageOptions options;

    @Override
    public void onCreate() {
        super.onCreate();
        LitePalApplication.initialize(this);
        instace = this;
        spUtil = new SPUtil(this);

        /**
         * 图片加载使用方法
         * 正常使用调用:ImageLoader.getInstance().displayImage("图片地址，本地网络都行", "要显示图片的imageview")
         * 希望能在图片加载时进行loading显示等可调用如下，SimpleImageLoadingListener中有开始失败以及成功回调
         * ImageLoader.getInstance().displayImage("图片地址，本地网络都行", "要显示图片的imageview", new SimpleImageLoadingListener(){...})
         */
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_default)
                .showImageForEmptyUri(R.drawable.ic_default)    //图片地址为空时显示图片
                .showImageOnFail(R.drawable.ic_default)      //图片加载失败时显示图片
                .cacheInMemory(true)    //是否使用内存缓存
                .cacheOnDisk(true)      //是否使用硬盘缓存
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new FadeInBitmapDisplayer(100))
                .build();
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(options)
                .build();
        ImageLoader.getInstance().init(configuration);
    }

    public static App getInstace() {
        return instace;
    }

    public static SPUtil getSpUtil() {
        return spUtil;
    }
}
