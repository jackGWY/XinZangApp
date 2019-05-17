package com.guo.xinzangapp.medicineArticle;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.guo.beans.HeartNews;
import com.guo.beans.News;
import com.guo.xinzangapp.R;

public class medicineAticle1Activity extends AppCompatActivity {
    private WebView mWv1;
    private String url = "https://mp.weixin.qq.com/s/6-oVK4SIecYfuGW7sEdsAg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_aticle1);
        Intent intent = getIntent();
        Bundle bundle=intent.getExtras();
//        News news =(News) bundle.getSerializable("news");
//        url = news.getNews_url();
        HeartNews heartNews = (HeartNews) bundle.getSerializable("news");
        url = heartNews.getNewsurl();
        mWv1=(WebView) findViewById(R.id.wv1);
        mWv1.getSettings().setJavaScriptEnabled(true);//设置支持javascript
        mWv1.setWebViewClient(new MyWebViewClient());
        mWv1.loadUrl(url);//公司的移动站点都是m.
        mWv1.setWebChromeClient(new MyWebChromeClient());
    }

    class MyWebViewClient extends WebViewClient {//浏览器在自己的app上运行搜索
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Log.d("WebView","onPageStarted...");
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.d("WebView","onPageFinished...");
            //mWvMain.loadUrl("javascript:alert('hello')");
            // mWvMain.evaluateJavascript("javascript:alert('hello')",null);
        }
    }
    class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {//进度条
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            setTitle(title);
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {//返回到自己的浏览器，而不是推到view
        if(keyCode==KeyEvent.KEYCODE_BACK&&mWv1.canGoBack()){//如果返回键
            mWv1.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
