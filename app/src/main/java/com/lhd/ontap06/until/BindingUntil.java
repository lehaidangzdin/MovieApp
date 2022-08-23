package com.lhd.ontap06.until;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.lhd.ontap06.R;

import java.text.DecimalFormat;

public class BindingUntil {
    @BindingAdapter("android:loadImage")
    public static void loadImage(ImageView imageView, String urlToImage) {
        Glide.with(imageView)
                .load(urlToImage)
                .error(R.drawable.warning)
                .into(imageView);
    }

    @BindingAdapter("android:loadBudget")
    public static void loadBudget(TextView textView, int revenue) {
        DecimalFormat formatter = new DecimalFormat("###,###,### $");
        textView.setText(formatter.format(revenue));
    }
}
