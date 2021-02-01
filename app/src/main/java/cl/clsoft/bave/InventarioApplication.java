package cl.clsoft.bave;

import android.app.Application;
import android.content.Context;

public class InventarioApplication extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        InventarioApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return InventarioApplication.context;
    }
}