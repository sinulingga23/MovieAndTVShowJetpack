package com.example.movieandtvshowjetpack.utils;

import android.content.Context;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

@GlideModule
public final class MyAppGlideModule extends AppGlideModule {
    public int getImageLocally(String path, Context context) {
        return context.getResources().getIdentifier(path, "drawable", context.getPackageName());
    }
}
