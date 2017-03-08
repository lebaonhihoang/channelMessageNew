package com.example.hoangleb.channelmessaging;

/**
 * Created by hoangleb on 08/03/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.HashMap;

public class MessagesActivity extends Activity implements OnDownloadListener {
//Variables
Context context;
 EditText message;
 Button btn_envoyer;
 ListView message_list;
 Handler handler = new Handler();

 LoginActivity la = new LoginActivity();

 protected void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 setContentView(R.layout.message_layout);

 Intent intent = getIntent();
 int id = intent.getIntExtra("channelID", 0);    //getting the first channel ID??
 final Runnable r = new Runnable() {
 public void run() {
 //use to display messages: tv.append("Hello World");
 handler.postDelayed(this, 1000);
 }
 };

 handler.postDelayed(r, 1000);

 message_list = (ListView) findViewById(R.id.message_list);
 message = (EditText) findViewById(R.id.message);
 btn_envoyer = (Button) findViewById(R.id.envoyer);

 SharedPreferences tokens = getSharedPreferences(la.MY_TOKEN, Context.MODE_PRIVATE);
 if (tokens != null) {
 String accestoken = tokens.getString(la.TOKENS, "");

 HashMap<String, String> params = new HashMap<>();
 params.put("accesstoken", accestoken);
 params.put("channelID", String.valueOf(id));

 Download d = new Download(this, "http://www.raphaelbischof.fr/messaging/?function=sendmessage", params);
 d.setOnDownloadListener(this);
 d.execute();
 }
 }


 @Override
 public void onDownload(String result) {
 Gson gson = new Gson();
 Messages m = gson.fromJson(result, Messages.class);

 }
 }

