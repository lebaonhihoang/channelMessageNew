package com.example.hoangleb.channelmessaging;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nhi on 11/02/2017.
 */

public class MyAdapter extends ArrayAdapter<GetChannel> {
     Context context;
      List<GetChannel> value;
    private String message = "connected user(s:)";

    public MyAdapter(Context context,List<GetChannel> value) {
        super(context, R.layout.getchannel_list_row, value);
        this.context = context;
        this.value = value;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.getchannel_list_row, parent, false);
        TextView tvChannel = (TextView) rowView.findViewById(R.id.GetChannelList1);
        TextView tvUser = (TextView) rowView.findViewById(R.id.GetChannelList2);

        GetChannel chl = value.get(position);
        tvChannel.setText(chl.getName());

        tvUser.setText(message + chl.getusers());

        return rowView;
    }

}
