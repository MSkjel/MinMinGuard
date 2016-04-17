package tw.fatminmin.xposed.minminguard.blocker.adnetwork;

import tw.fatminmin.xposed.minminguard.Main;
import tw.fatminmin.xposed.minminguard.blocker.Blocker;
import tw.fatminmin.xposed.minminguard.blocker.Util;
import android.view.View;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.XposedHelpers.ClassNotFoundError;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class Tapfortap extends Blocker {
    
    public final static String banner = "com.tapfortap.AdView";
    public final static String bannerPrefix = "com.tapfortap";

    @Override
    public String getBannerPrefix() {
        return bannerPrefix;
    }

    @Override
    public String getBanner() {
        return banner;
    }
    public boolean handleLoadPackage(final String packageName, LoadPackageParam lpparam, final boolean removeAd) {
        try {
            
            Class<?> adView = XposedHelpers.findClass("com.tapfortap.AdView", lpparam.classLoader);
            
            XposedBridge.hookAllMethods(adView, "loadAds" ,new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            
                            Util.log(packageName, "Detect tapfortap AdView loadAds in " + packageName);
                            
                            if(removeAd) {
                                param.setResult(new Object());
                                Main.removeAdView((View) param.thisObject, packageName, true);
                            }
                        }
                    });
            
            Class<?> interAds = XposedHelpers.findClass("com.tapfortap.Interstitial", lpparam.classLoader);
            XposedBridge.hookAllMethods(interAds, "show" ,new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    
                    Util.log(packageName, "Detect tapfortap Interstitial show in " + packageName);
                    
                    if(removeAd) {
                        param.setResult(new Object());
                    }
                }
            });
            
            Util.log(packageName, packageName + " uses tapfortap");
        }
        catch(ClassNotFoundError e) {
            return false;
        }
        return true;
    }
}
