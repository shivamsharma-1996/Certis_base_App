package com.certis_base_app.utills;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.certis_base_app.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class MapUtil {
    public static Bitmap createCustomMarker(final Activity activity, @DrawableRes int resource, @LayoutRes int layout, String name)
    {
        View markerView = LayoutInflater.from(activity).inflate(layout, null);
        CircleImageView mUserIcon = markerView.findViewById(R.id.civ_user);
        mUserIcon.setImageResource(resource);

        if(layout == R.layout.layout_custom_marker_detail)
        {
            TextView mUsername = markerView.findViewById(R.id.tv_user_name);
            mUsername.setText(name);
        }

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        markerView.setLayoutParams(new ViewGroup.LayoutParams(52, ViewGroup.LayoutParams.WRAP_CONTENT));
        markerView.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        markerView.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        markerView.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(markerView.getMeasuredWidth(), markerView.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        markerView.draw(canvas);
        return bitmap;
    }
}
