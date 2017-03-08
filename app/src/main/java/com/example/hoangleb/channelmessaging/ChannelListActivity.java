package com.example.hoangleb.channelmessaging;
        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ListView;
        import android.widget.Toast;

        import com.google.gson.Gson;

        import java.util.HashMap;

/**
 * Created by hoangleb on 30/01/2017.
 */
public class ChannelListActivity extends Activity implements AdapterView.OnItemClickListener, View.OnClickListener, OnDownloadListener{
    //Variables
    private ListView lvChannels;
    //public static final String PREFS_NAME = "stockage";
    String accestoken;

    @Override
    public void onClick(View v) {
        Toast.makeText(getApplicationContext(),
                "Réussi", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getchannel_layout);

        lvChannels = (ListView) findViewById(R.id.listChannels);
        lvChannels.setOnItemClickListener(this);

        SharedPreferences tokens = getSharedPreferences(LoginActivity.MY_TOKEN, Context.MODE_PRIVATE);
        if (tokens != null) {
            accestoken = tokens.getString(LoginActivity.TOKENS, "");

            HashMap<String, String> params = new HashMap<>();
            params.put("accesstoken", accestoken);

            Download d = new Download(this, "http://www.raphaelbischof.fr/messaging/?function=getchannels", params);
            d.setOnDownloadListener(this);
            d.execute();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent message = new Intent(ChannelListActivity.this, MessagesActivity.class);
        message.putExtra("channelID", 0); //Puts the channelID inside the intent.
        startActivity(message);
    }


    @Override
    public void onDownload(String result) {


        Gson gson = new Gson();
        chan c = gson.fromJson(result, chan.class);

        for(GetChannel ch :c.channelList){
            Toast.makeText(this, ch.getName(), Toast.LENGTH_SHORT).show();
        }

        lvChannels = (ListView) findViewById(R.id.listChannels);
        lvChannels.setAdapter(new MyChannelAdaptor(getApplicationContext(), c.channelList));
        //lvChannels.setOnItemClickListener(this);
    }
}
