package chen0040.github.com.androidmagentomobile;

import android.os.Build;
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



            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    String url = request.getUrl().toString();
                    Log.v(TAG, url);
                    view.loadUrl(url);
                }
                return true;
            }
        });
        webView.loadUrl("http://j-clef-web-01.japaneast.cloudapp.azure.com");
        return mainView;
    }

    public void goToAdmin(){
        webView.loadUrl("http://j-clef-web-01.japaneast.cloudapp.azure.com/admin");
    }

}
