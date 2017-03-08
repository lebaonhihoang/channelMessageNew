package com.example.hoangleb.channelmessaging;

/**
 * Created by hoangleb on 03/03/2017.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
public class MyChannelAdaptor extends ArrayAdapter<GetChannel>{
    private final Context context;
    private final List<GetChannel> value;
    private String message = "Number of connected user(s): ";

    public MyChannelAdaptor(Context context, List<GetChannel> value) {
        super(context,R.layout.getchannel_list_row, value);
        this.context = context;
        this.value = value;
    }

    public View getView (int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.getchannel_list_row, parent, false);
        TextView  tvChannel = (TextView) rowView.findViewById(R.id.GetChannelList1);
        TextView tvUser =  (TextView) rowView.findViewById(R.id.GetChannelList2);

        GetChannel chnnl = value.get(position);
        tvChannel.setText(chnnl.getName());

        tvUser.setText(message + chnnl.getusers());

        return rowView;
    }
}

