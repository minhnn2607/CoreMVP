package vn.nms.core.utils;

public class ColorUtils {
    public static int getColor(int R, int G, int B){
        return 0xff000000 | (R << 16) | (G << 8) | B;
    }
}
