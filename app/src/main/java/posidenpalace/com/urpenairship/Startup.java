package posidenpalace.com.urpenairship;


import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.urbanairship.UAirship;

import timber.log.Timber;

public class Startup extends Application {


    public static Startup instance;

    RefWatcher refWatcher;


    @Override
    public void onCreate() {
        super.onCreate();
        UAirship.takeOff(this);
        UAirship.shared().getPushManager().setUserNotificationsEnabled(true);
        Timber.plant(new Timber.DebugTree());
        Timber.d("Hello");

        instance = this;
        refWatcher = LeakCanary.install(this);



    }
    public void  MustDie(Object object){
        if(refWatcher!=null){
            refWatcher.watch(object);
        }

    }
}
