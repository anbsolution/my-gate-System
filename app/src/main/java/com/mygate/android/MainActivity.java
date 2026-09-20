package com.mygate.android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String APP_URL =
            "https://selfpayroll.site.je/index.php?v=182";

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(Color.rgb(8, 105, 217));
        getWindow().setNavigationBarColor(Color.rgb(5, 18, 38));

        webView = new WebView(this);
        webView.setBackgroundColor(Color.WHITE);
        setContentView(webView);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
        settings.setAllowFileAccess(false);
        settings.setAllowContentAccess(false);
        settings.setMixedContentMode(WebSettings.MIXED_CONTENT_NEVER_ALLOW);

        webView.setWebChromeClient(new WebChromeClient());

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Uri uri = request.getUrl();

                if ("https".equalsIgnoreCase(uri.getScheme())
                        && "selfpayroll.site.je".equalsIgnoreCase(uri.getHost())) {
                    return false;
                }

                // Keep non-My-Gate links out of the app WebView.
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    startActivity(intent);
                } catch (Exception ignored) {
                    Toast.makeText(MainActivity.this,
                            "Link open nahi ho saka", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        if (savedInstanceState == null) {
            webView.loadUrl(APP_URL);
        } else {
            webView.restoreState(savedInstanceState);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        webView.saveState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
