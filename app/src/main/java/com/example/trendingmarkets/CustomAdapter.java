package com.example.trendingmarkets;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    @Override
    public int getCount() {
        return listStorage.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView ticker = null;
        TextView price = null;
        TextView volume = null;

        if (convertView == null) {
            convertView = lInflater.inflate(R.layout.list, parent,
                    false);
        }
        ticker = convertView.findViewById(R.id.textView);
        price = convertView.findViewById(R.id.textView2);
        volume = convertView.findViewById(R.id.textView3);

        ticker.setText("$" + listStorage.get(position).getTicker());
        price.setText("Price: $" + listStorage.get(position).getPrice());
        volume.setText("Volume (# of shares traded): " + listStorage.get(position).getVolume());

        if (position % 2 == 1) {
            convertView.setBackgroundResource(R.color.dark_blue);
        } else {
            convertView.setBackgroundResource(R.color.midnight_blue);
        }
        return convertView;
    }

    private LayoutInflater lInflater;
    private List<StockObject> listStorage;

    public CustomAdapter(Context context, List<StockObject> customizedListView) {
        lInflater = (LayoutInflater)context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        listStorage = customizedListView;
    }

}
