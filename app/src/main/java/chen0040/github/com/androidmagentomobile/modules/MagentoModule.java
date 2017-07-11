package chen0040.github.com.androidmagentomobile.modules;

import android.util.Log;

import com.github.chen0040.androidmagentoclient.AndroidMagentoClient;

/**
 * Created by chen0 on 4/7/2017.
 */

public class MagentoModule {
    private static final String TAG = "MagentoModule";

    public static final String HOME_URL = "http://j-clef-web-01.japaneast.cloudapp.azure.com/";
    public static final String USERNAME = "xs0040@gmail.com";
    public static final String PASSWORD = "chen@0040";
    private static MagentoModule instance;
    private AndroidMagentoClient client;

    public synchronized static MagentoModule getInstance(){
        if(instance == null) {
            instance = new MagentoModule();
        }
        return instance;
    }

    private MagentoModule(){
        client = new AndroidMagentoClient(HOME_URL);
    }

    public AndroidMagentoClient client(){
        return client;
    }

    public String loginDemoClient(){
        String token = client.loginAsClient(USERNAME, PASSWORD);
        Log.i(TAG, "login as client: " + token);
        return token;
    }
}
