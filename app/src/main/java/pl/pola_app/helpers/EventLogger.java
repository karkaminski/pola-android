package pl.pola_app.helpers;

import android.content.Context;
import android.os.Bundle;

import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.analytics.FirebaseAnalytics;

import pl.pola_app.BuildConfig;

public class EventLogger {

    private Context c;

    public EventLogger(Context context) {
        c = context;
    }

    public void logSearch(String result, String deviceId, String source) {
        if (!BuildConfig.USE_FIREBASE) {
            return;
        }

        Bundle bundle = new Bundle();
        bundle.putString("code", result);
        bundle.putString("device_id", deviceId);
        bundle.putString("source", source);
        FirebaseAnalytics.getInstance(c).logEvent("scan_code", bundle);
    }

    public void logCustom(String eventName, Bundle bundle) {
        if (!BuildConfig.USE_FIREBASE) {
            return;
        }

        FirebaseAnalytics.getInstance(c).logEvent(eventName, bundle);
    }

    public void logContentView(String contentName, String contentType, String contentId, String code, String deviceId) {
        if (!BuildConfig.USE_FIREBASE) {
            return;
        }

        Bundle bundle = new Bundle();
        bundle.putString("company", contentName);
        bundle.putString("device_id", deviceId);
        bundle.putString("product_id", contentId);
        bundle.putString("code", code);
        FirebaseAnalytics.getInstance(c).logEvent(contentType, bundle);
    }

    public void logException(Throwable throwable) {
        if (!BuildConfig.USE_FIREBASE) {
            return;
        }

        try {
            FirebaseCrashlytics.getInstance().recordException(throwable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logLevelStart(String levelName, String productId, String deviceId) {
        if(!BuildConfig.USE_FIREBASE) {
            return;
        }

        Bundle bundle = new Bundle();
        bundle.putString("device_id", deviceId);
        bundle.putString("code", productId);
        FirebaseAnalytics.getInstance(c).logEvent(levelName+"_started", bundle);
    }

    public void logLevelEnd(String levelName, String productId, String deviceId) {
        if(!BuildConfig.USE_FIREBASE) {
            return;
        }

        Bundle bundle = new Bundle();
        bundle.putString("device_id", deviceId);
        bundle.putString("code", productId);
        FirebaseAnalytics.getInstance(c).logEvent(levelName+"_finished", bundle);
    }

    public void logMenuItemOpened(String itemName, String deviceId) {
        if(!BuildConfig.USE_FIREBASE) {
            return;
        }

        Bundle bundle = new Bundle();
        bundle.putString("item", itemName);
        bundle.putString("device_id", deviceId);
        FirebaseAnalytics.getInstance(c).logEvent("menu_item_opened", bundle);
    }
}
