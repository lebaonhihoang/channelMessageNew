package com.example.hoangleb.channelmessaging;

/**
 * Created by hoangleb on 03/03/2017.
 */
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

import static android.R.attr.value;

    public class MessagesAdapter extends ArrayAdapter<Message> implements OnDownloadListener {

        private final Context context;
        private final List<Message> values;
        View messageRowView;
        private String url;

        public MessagesAdapter(Context context, List<Message> values) {
            super(context, R.layout.message_row, values);
            this.context = context;
            this.values = values;
        }

        public View getView (int position, View convertView, ViewGroup parent){

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            messageRowView = inflater.inflate(R.layout.message_row, parent, false);

            TextView message_text = (TextView) messageRowView.findViewById(R.id.txtMessage);
            message_text.setText(values.get(position).getMessage());

            TextView message_date =  (TextView) messageRowView.findViewById(R.id.txtDate);
            message_date.setText(values.get(position).getDate());

            TextView message_user = (TextView) messageRowView.findViewById(R.id.text_user);
            message_user.setText(values.get(position).getUsername() + ": " );

            return messageRowView;
        }


        @Override
        public void onDownload(String result) {
            File imgFile = new File(url);
            if (imgFile.exists()) {
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                ImageView imgUser = (ImageView) messageRowView.findViewById(R.id.imgUser);
                imgUser.setImageBitmap(myBitmap);

            }
        }



    }

