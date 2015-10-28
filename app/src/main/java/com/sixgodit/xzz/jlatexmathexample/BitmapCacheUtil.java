package com.sixgodit.xzz.jlatexmathexample;

/**
 * Created by xuzhenzhou on 15/10/14.
 */

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import java.util.Iterator;
import java.util.Map;

public class BitmapCacheUtil {
    /**
     * 缓存Image的类，当存储Image的大小大于LruCache设定的值，系统自动释放内存
     */
    private LruCache<String, Bitmap> mMemoryCache;
    private static BitmapCacheUtil bitmapCacheUtil;

    private BitmapCacheUtil() {
        //获取系统分配给每个应用程序的最大内存，每个应用系统分配32M
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int mCacheSize = maxMemory / 8;
        //给LruCache分配1/8 4M
        mMemoryCache = new LruCache<String, Bitmap>(mCacheSize) {

            //必须重写此方法，来测量Bitmap的大小
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }

    public void remove(String key) {
        Bitmap bitmap = mMemoryCache.remove(key);
        if (bitmap != null) {
            bitmap.recycle();
        }
    }

    /**
     * 清空Bitmap缓存
     */
    public void clearCache() {
        Map<String, Bitmap> map = mMemoryCache.snapshot();
        Iterator<Map.Entry<String, Bitmap>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Bitmap> entry = iterator.next();
            remove(entry.getKey());
        }
//        mMemoryCache.trimToSize(0);//清除所有缓存
    }

    /**
     * 获取缓存工具类实例
     * @return
     */
    public static BitmapCacheUtil getInstance() {
        if (bitmapCacheUtil == null) {
            bitmapCacheUtil = new BitmapCacheUtil();
        }
        return bitmapCacheUtil;
    }

    /**
     * 添加Bitmap到内存缓存
     *
     * @param key
     * @param bitmap
     */
    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null && bitmap != null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    /**
     * 从内存缓存中获取一个Bitmap
     *
     * @param key
     * @return
     */
    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }
}

