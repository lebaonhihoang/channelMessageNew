 package com.example.hoangleb.channelmessaging;

 import android.content.Context;
 import android.content.Intent;
 import android.content.SharedPreferences;
 import android.os.Bundle;
 import android.support.v7.app.AppCompatActivity;
 import android.view.View;
 import android.widget.Button;
 import android.widget.EditText;
 import android.widget.Toast;

 import com.google.gson.Gson;

 import java.util.HashMap;


public class LoginActivity extends AppCompatActivity implements OnDownloadListener {

    //Variables
    private Button btnValider;
    private EditText editUsername;
    private EditText editPassword;
    private SharedPreferences tokens;
    private connect connection = new connect();

    //Save the access token in a file
    public static final String MY_TOKEN = "MyTokensFile";
    public static final String TOKENS = "tokenKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);    //Setting the layout

        editUsername = (EditText) findViewById(R.id.userNameTextField);
        editPassword = (EditText) findViewById(R.id.passwordTextField);
        //Button
        btnValider = (Button) findViewById(R.id.validate);
        tokens = getSharedPreferences(MY_TOKEN, Context.MODE_PRIVATE);

        //When the button is clicked, so add whatever you want
        // accomplished by the button you put here.
        btnValider.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                String username = "username";
                String password = "password";

                HashMap<String, String> params = new HashMap<String, String>();
                params.put(username, editUsername.getText().toString());
                params.put(password, editPassword.getText().toString());

                if(v.getId() == R.id.validate){
                    String UserName = String.valueOf(editUsername.getText());
                    String Password = String.valueOf(editPassword.getText());

                    Download d = new Download(LoginActivity.this, "http://www.raphaelbischof.fr/messaging/?function=connect", params);
                    d.setOnDownloadListener(LoginActivity.this);
                    d.execute();
                }
            }
        });
    }//End of oncreate

    //We deserialise the results downloaded here.
    @Override
    public void onDownload(String values) {
        SharedPreferences.Editor editor = tokens.edit();
        //Gson: convert Json to Java object

        Gson gson = new Gson();
        connection = gson.fromJson(values, connect.class);

        editor.putString(TOKENS, connection.getAccesstoken());
        connection.getAccesstoken();
        editor.commit();
        if (connection.getResponse().equalsIgnoreCase("OK")) {
            Toast.makeText(getApplicationContext(),
                    "Réussi", Toast.LENGTH_SHORT).show();

            editor.putString(TOKENS, connection.getAccesstoken());
            editor.commit();

            Intent ob = new Intent(LoginActivity.this, ChannelListActivity.class);
            //ob.putExtra()
            startActivity(ob);
        } else {
            Toast.makeText(getApplicationContext(),
                    "Erreur de connexion", Toast.LENGTH_SHORT).show();
        }
    }
}
