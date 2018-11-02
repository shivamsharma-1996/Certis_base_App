package com.certis_base_app.ui.onboarding.registration;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.certis_base_app.R;

import java.util.List;

public class CountryCodeSpinnerAdapter extends ArrayAdapter<Integer> {

    private Integer[] images;
    private String[] text;
    private Context context;

    public CountryCodeSpinnerAdapter(Context context, Integer[] images,String[] text) {
        super(context, android.R.layout.simple_spinner_item, images);
        this.images = images;
        this.text=text;
        this.context=context;
    }


    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.item_spinner_country_dropdown, parent, false);
        TextView textView = row.findViewById(R.id.tv_country_name);
        ImageView imageView = row.findViewById(R.id.iv_country_flag);
        if(position==0){
            textView.setText(text[position]);
        }
        else {
            textView.setText(text[position]);
            imageView.setImageResource(images[position]);
        }
        return row;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.item_spinner_country_selected_view, parent, false);
        TextView textView = row.findViewById(R.id.tv_country_name);
        textView.setText(text[position]);
        return row;
    }

}
