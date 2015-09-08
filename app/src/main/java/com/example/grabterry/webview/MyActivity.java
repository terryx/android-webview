package com.example.grabterry.webview;

import android.annotation.TargetApi;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebResourceResponse;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class MyActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        final WebView webView = (WebView) this.findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
//
//                DefaultHttpClient client = new DefaultHttpClient();
//                HttpGet httpGet = new HttpGet(url);
//                Map<String, String> headers = new HashMap<String, String>();
//                headers.put("Authorization", "GM 1:hYtDwvSF0cMdrMxSMoq2NXb6_NqGbUID");
//
//                return super.shouldInterceptRequest(view, url);
//            }

            public boolean shouldOverrideUrlLoading(final WebView view, final String url)
            {
                                    Map<String, String> headers = new HashMap<String, String>();
                    headers.put("Authorization", "GM 1:hYtDwvSF0cMdrMxSMoq2NXb6_NqGbUID");

                view.loadUrl(url, headers);
                return true;
            }

           // @Override
//            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
//                try {
//                    DefaultHttpClient client = new DefaultHttpClient();
//                    HttpGet httpGet = new HttpGet(url);
//
//                    httpGet.setHeader("Authorization", "GM 1:hYtDwvSF0cMdrMxSMoq2NXb6_NqGbUID");
////                    Map<String, String> headers = new HashMap<String, String>();
////                    headers.put("Authorization", "GM 1:hYtDwvSF0cMdrMxSMoq2NXb6_NqGbUID");
////
////                    for (String key : headers.keySet()) {
////                        httpGet.setHeader(key, headers.get(key));
////                    }
//                    HttpResponse httpReponse = client.execute(httpGet);
//
//                    Header contentType = httpReponse.getEntity().getContentType();
//                    Header encoding = httpReponse.getEntity().getContentEncoding();
//                    InputStream responseInputStream = httpReponse.getEntity().getContent();
//
//                    String contentTypeValue = null;
//                    String encodingValue = null;
//                    if (contentType != null)
//                    {
//                        contentTypeValue = contentType.getValue();
//                    }
//                    if (encoding != null)
//                    {
//                        encodingValue = encoding.getValue();
//                    }
//                    return new WebResourceResponse(contentTypeValue, encodingValue, responseInputStream);
//                } catch (Exception e) {
//                    // Fall back to super
//                    e.printStackTrace();
//                }
//                return super.shouldInterceptRequest(view, url);
//            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                if (consoleMessage != null) {
                    Log.i("TEST", consoleMessage.lineNumber() + " - " + consoleMessage.message());
                }
                return super.onConsoleMessage(consoleMessage);
            }
        });

       String url = "http://10.2.0.173:3031/driver.html?auth_token=shahzaibhassangrabtaxico";

        Map<String, String> headers = new HashMap<String, String>();

        webView.loadUrl(url, headers);
    }

    @TargetApi(16)
    protected void fixNewAndroid(WebView webView) {
        try {
            webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        } catch(NullPointerException e) {
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }
}
