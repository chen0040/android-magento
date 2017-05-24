package chen0040.github.com.androidmagentomobile;

import android.graphics.Bitmap;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static final String TAG = "MainActivityFragment";
    private static final String HOME_URL = "http://j-clef-web-01.japaneast.cloudapp.azure.com";
    private boolean mainPageHome = true;

    private WebView webView;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_main, container, false);
        webView = (WebView) mainView.findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){

            private String currentUrl;

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

                Log.v(TAG, "your current url when webpage loading... " + url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                currentUrl = url;
                Log.v(TAG, "your current url when webpage loading.. finish => " + url);
                super.onPageFinished(view, url);
            }


            @Override
            public boolean shouldOverrideUrlLoading(WebView wView, String url)
            {
                // sendfriend/product/send/id/808/cat_id/5/
                Log.v(TAG, "user clicking link: " + url);

                if(url.contains("sendfriend")){
                    Snackbar.make(wView, "Open 3D model now!!!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    //wView.loadUrl(currentUrl);
                } else {
                    wView.loadUrl(url);
                }
                return true;
            }
        });
        webView.loadUrl(HOME_URL);
        return mainView;
    }

    public void toggleMainPage(){
        if(mainPageHome) {
            Log.v(TAG, "go to admin");
            Snackbar.make(webView, "Navigating to Administration", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            webView.loadUrl(HOME_URL + "/admin");
        } else {
            Log.v(TAG, "go to home");
            Snackbar.make(webView, "Navigating to Home", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            webView.loadUrl(HOME_URL);
        }
        mainPageHome = !mainPageHome;

    }

    public boolean canGoBack(){
        return webView.canGoBack();
    }

    public void goBack(){
        webView.goBack();
    }

}
