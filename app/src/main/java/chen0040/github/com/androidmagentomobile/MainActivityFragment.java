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
            private Pattern pattern = Pattern.compile(".*/id/(\\d+)/cat_id/.*");
            private int productId;

            private List<String> modelIds = Arrays.asList("1d5285f2e0fd4211a27c8042496d5959",
                    "311d052a9f034ba8bce55a1a8296b6f9",
                    "66e4ced42d3d4a9f8598241de7491783",
                    "aba23531911c45439067a6e0aaccad07",
                    "8e75372b3ace4717b85fe6eb224c9550",
                    "89c45d1d5dfa4876ba353c86007084b8",
                    "f2129918d55646dbbaa8268367fa242e",
                    "e215b6fd60ca48ddabaa77373f35176c",
                    "66b4d99c026343ce8774e8a3b521246d"
            );

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

                    Snackbar.make(wView, "Open 3D model for Product with ProductId = " + productId, Snackbar.LENGTH_LONG).setAction("Action", null).show();

                    String id = modelIds.get(productId % modelIds.size());


                    String mockUrl = "https://sketchfab.com/models/".concat(id).concat("/embed");
                    wView.loadUrl(mockUrl);
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
