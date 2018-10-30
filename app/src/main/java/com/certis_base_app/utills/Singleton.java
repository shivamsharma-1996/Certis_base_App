package com.certis_base_app.utills;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ScrollView;
import android.widget.TextView;

import com.certis_base_app.R;

public class Singleton {

    private static Singleton self;

    public static Singleton getInstance() {
        if (self == null)
            self = new Singleton();

        return self;
    }

    public Singleton() {
        self = this;
    }

    public static void openKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY); // show
    }

    public static void hideKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0); // hide
    }

    public static void showSnackbar(Context context, final View view, int drawable, int messageText, int actionText, int actionColor) {
        final Snackbar snackBar = Snackbar.make(view, messageText, Snackbar.LENGTH_INDEFINITE);
        View snackbarLayout = snackBar.getView();
        TextView textView = snackbarLayout.findViewById(android.support.design.R.id.snackbar_text);
        textView.setCompoundDrawablesWithIntrinsicBounds(drawable, 0, 0, 0);

        ScrollView.LayoutParams params = (ScrollView.LayoutParams) view.getLayoutParams();
        params.gravity = Gravity.TOP;
        view.setLayoutParams(params);
        textView.setCompoundDrawablePadding(context.getResources().getDimensionPixelOffset(R.dimen.snackbar_inset_padding));
        snackBar.setAction(actionText, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackBar.dismiss();
            }
        }).setActionTextColor(ContextCompat.getColor(context, actionColor)).show();
    }
}

