package com.lhd.ontap06.until;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class BindingUntil {
    @BindingAdapter("android:loadImage")
    public static void loadImage(ImageView imageView, String urlToImage) {
        Glide.with(imageView)
                .load(urlToImage)
                .centerInside()
                .into(imageView);
    }
}
