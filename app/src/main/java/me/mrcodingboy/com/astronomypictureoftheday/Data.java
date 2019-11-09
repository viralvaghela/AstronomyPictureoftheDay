package me.mrcodingboy.com.astronomypictureoftheday;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class Data extends Activity {

    WebView webView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data2);
        Intent intent=getIntent();
        webView=(WebView)findViewById(R.id.webview);
        textView=(TextView)findViewById(R.id.txt);

        webView.loadUrl(intent.getStringExtra("url"));
        textView.setText(intent.getStringExtra("explanation"));

    }

}
