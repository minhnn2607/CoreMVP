package vn.nms.core.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class ViewUtils {
    public static int convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return (int)px;
    }
}
