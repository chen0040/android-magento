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

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import chen0040.github.com.androidmagentomobile.modules.MagentoModule;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static final String TAG = "MainActivityFragment";


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
            private Pattern pattern = Pattern.compile(".*/id/(\\d+)/cat_id/.*");
            private int productId;



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
                    Matcher matcher = pattern.matcher(url);
                    while (matcher.find()) {
                        productId = Integer.parseInt(matcher.group(1));
                        System.out.println("product id: " + productId);
                    }

                    Snackbar.make(wView, "Send Email for Product with ProductId = " + productId, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                } else {
                    wView.loadUrl(url);
                }
                return true;
            }
        });
        webView.loadUrl(MagentoModule.HOME_URL);
        return mainView;
    }

    public void toggleMainPage(){
        Log.v(TAG, "go to home");
        Snackbar.make(webView, "Navigating to Home", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        webView.loadUrl(MagentoModule.HOME_URL);

    }

    public boolean canGoBack(){
        return webView.canGoBack();
    }

    public void goBack(){
        webView.goBack();
    }

}
