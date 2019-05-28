package tw.fatminmin.xposed.minminguard.blocker.custom_mod;

import android.view.View;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import tw.fatminmin.xposed.minminguard.blocker.Util;

//TODO Fix formatting
final class Train
{

    private static String pkg = "idv.nightgospel.TWRailScheduleLookUp";

    private Train() throws InstantiationException
    {
        throw new InstantiationException("This class is not for instantiation");
    }

    public static void handleLoadPackage(LoadPackageParam lpparam)
    {
        if (!lpparam.packageName.equals(pkg))
        {
            return;
        }

        XposedHelpers.findAndHookMethod("com.waystorm.ads.WSAdBanner", lpparam.classLoader, "setWSAdListener", "com.waystorm.ads.WSAdListener", new XC_MethodHook()
        {
            @Override
            protected void beforeHookedMethod(MethodHookParam param)
            {

                Util.log(pkg, "Prevent WSAdBanner setWSAdListener " + pkg);

                param.setResult(new Object());
            }
        });
    }

    public static void handleInitPackageResources(InitPackageResourcesParam resparam)
    {
        if (!resparam.packageName.equals(pkg))
        {
            return;
        }

        resparam.res.hookLayout(pkg, "layout", "adlayout", new XC_LayoutInflated()
        {

            @Override
            public void handleLayoutInflated(LayoutInflatedParam liparam)
            {

                Util.log(pkg, "Handle train ad layout");

                View ad = liparam.view.findViewById(liparam.res.getIdentifier("adLayout", "id", pkg));

                ad.setVisibility(View.GONE);
            }
        });
    }
}
