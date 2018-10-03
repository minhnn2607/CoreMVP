package vn.nms.core.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import vn.nms.core.R;

public class ImageViewMenu extends LinearLayout {
    public ImageViewMenu(Context context) {
        super(context);
        init(context, null);
    }

    public ImageViewMenu(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ImageViewMenu(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View view = View.inflate(getContext(), R.layout.image_view_menu, null);
        if (attrs != null) {
            TypedArray attributes = context.obtainStyledAttributes(attrs,
                    R.styleable.ImageViewMenu);
            Drawable drawable = attributes.getDrawable(R.styleable.ImageViewMenu_ivm_src);
            if (drawable != null)
                ((ImageView) view.findViewById(R.id.imgMenu)).setImageDrawable(drawable);
            attributes.recycle();
        }
        this.addView(view);
    }

    public void setImageResource(@DrawableRes int resId) {
        ((ImageView) findViewById(R.id.imgMenu)).setImageResource(resId);
    }
}
