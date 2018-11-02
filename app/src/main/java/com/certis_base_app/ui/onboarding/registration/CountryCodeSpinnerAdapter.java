package com.certis_base_app.ui.onboarding.registration;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class CountryCodeSpinnerAdapter extends ArrayAdapter<CountryCode> {

    private Context context;
    private List<CountryCode> countryCodeList;

    public CountryCodeSpinnerAdapter(@NonNull Context context, int resource, ArrayList<CountryCode> countryCodeList) {
        super(context, resource);
        this.context = context;
        this.countryCodeList = countryCodeList;
    }


    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null)
        {
           // LayoutInflater.from(context).inflate(R.la)
        }
        return super.getDropDownView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
