 package com.example.hoangleb.channelmessaging;
 import android.content.Context;
 import android.os.AsyncTask;
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
        import java.io.InputStreamReader;
        import java.io.OutputStream;
        import java.io.OutputStreamWriter;
        import java.io.UnsupportedEncodingException;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.net.URLEncoder;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.Map;

        import javax.net.ssl.HttpsURLConnection;

/**
 * Created by hoangleb on 30/01/2017.
 */
public class Download extends AsyncTask
        <Void, Void, String> {
    //Variables
    private Context context;
    private HashMap<String, String> params;
    private String url;


    public void addOnDownloadCompleteListener(OnDownloadListener listener){
        listeners.add(listener);
    }


    //Constructor
    public Download(Context context, String url, HashMap<String, String>params) {
        this.params = params;
        this.context = context;
        this.url = url;
    }
    @Override
    protected String doInBackground(Void... input) {
        return performPostCall(url,params);
    }

    public void setOnDownloadListener
            (OnDownloadListener listener) {
// Store the listener object
        // Store the listener object
        this.listeners.add(listener);
    }

    public String performPostCall(String requestURL,
                                  HashMap<String, String> postDataParams) {
        URL url;
        String response = "";
        try {

            url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(15000);

            conn.setConnectTimeout(15000);

            conn.setRequestMethod("POST");

            conn.setDoInput(true);

            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

            writer.write(getPostDataString(postDataParams));

            writer.flush();

            writer.close();

            os.close();

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {

                String line;

                BufferedReader br =
                        new BufferedReader(new InputStreamReader(conn.getInputStream()));

                while ((line = br.readLine()) != null) {
                    response += line;
                }

            } else {

                response = "";

            }

        } catch (Exception e) {

            e.printStackTrace();

        }
        return response;
    }

    private String getPostDataString
            (HashMap<String, String> params) throws
            UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first) first = false;
            else result.append("&");
            result.append
                    (URLEncoder.encode
                            (entry.getKey(), "UTF-8"));
            result.append("=");
            result.append
                    (URLEncoder.encode
                            (entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }

    //ArrayList which adds listeners
    private ArrayList<OnDownloadListener> listeners = new
            ArrayList<>();

    //Listeners are added to the ArrayList
    public void addOnDownloaderListener(OnDownloadListener listener){
        listeners.add(listener);
    }

    @Override
    protected void onPostExecute(String result) {

        super.onPostExecute(result);
        for (OnDownloadListener oneListener : listeners)
        {

            oneListener.onDownload(result);
        }
    }
}
