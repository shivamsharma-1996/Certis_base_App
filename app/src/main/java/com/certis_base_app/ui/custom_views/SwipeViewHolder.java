package com.certis_base_app.ui.custom_views;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class SwipeViewHolder extends RecyclerView.ViewHolder {
        public View viewBackground;
        public View viewForeground;

        public SwipeViewHolder(View view) {
            super(view);
        }
    }