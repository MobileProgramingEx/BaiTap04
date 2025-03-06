package com.example.girdview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LanguageAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> names;
    private ArrayList<Integer> images;

    public LanguageAdapter(Context context, ArrayList<String> names, ArrayList<Integer> images) {
        this.context = context;
        this.names = names;
        this.images = images;
    }

    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int position) {
        return names.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.gird_item, parent, false);
        }

        ImageView imgLogo = convertView.findViewById(R.id.imgLogo);
        TextView txtName = convertView.findViewById(R.id.txtName);

        imgLogo.setImageResource(images.get(position));
        txtName.setText(names.get(position));

        return convertView;
    }
}
