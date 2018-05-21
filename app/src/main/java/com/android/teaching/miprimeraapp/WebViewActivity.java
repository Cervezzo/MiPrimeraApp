package com.android.teaching.miprimeraapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        WebView myWebView = findViewById(R.id.web_view);
        myWebView.setWebViewClient(new WebViewClient());
        //int stringResourceId = getIntent().getIntExtra("string_id", 0);
        //String url = getString(stringResourceId);
        String urlToLoad = getIntent().getStringExtra("url");
        myWebView.loadUrl(urlToLoad);
    }
}
