package com.example2.admin.iit_jee;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Admin on 16-05-2017.
 */

class MyBrowser extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}

