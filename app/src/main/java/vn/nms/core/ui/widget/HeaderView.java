package vn.nms.core.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import vn.nms.core.R;

import me.grantland.widget.AutofitTextView;

public class HeaderView extends FrameLayout {

    private ImageViewMenu imgToolbarLeft;
    private TextView tvToolbarLeft;
    private AutofitTextView tvToolbarTitle;
    private ImageViewMenu imgToolbarRight;
    private TextView tvToolbarRight;

    private HeaderListener listener;

    public void setListener(HeaderListener listener) {
        this.listener = listener;
    }

    public HeaderView(Context context) {
        super(context);
        init(context, null);
    }

    public HeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public HeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View view = View.inflate(getContext(), R.layout.common_header_layout, null);
        imgToolbarLeft = view.findViewById(R.id.imgToolbarLeft);
        tvToolbarLeft = view.findViewById(R.id.tvToolbarLeft);
        tvToolbarTitle = view.findViewById(R.id.tvToolbarTitle);
        imgToolbarRight = view.findViewById(R.id.imgToolbarRight);
        tvToolbarRight = view.findViewById(R.id.tvToolbarRight);
        tvToolbarLeft.setOnClickListener(view1 -> {
            if (listener != null) listener.onHeaderLeftTextPressed();
        });
        tvToolbarRight.setOnClickListener(view1 -> {
            if (listener != null) listener.onHeaderRightTextPressed();
        });
        imgToolbarLeft.setOnClickListener(view1 -> {
            if (listener != null) listener.onHeaderLeftImagePressed();
        });
        imgToolbarRight.setOnClickListener(view1 -> {
            if (listener != null) listener.onHeaderRightImagePressed();
        });
        if (attrs != null) {
            TypedArray attributes = context.obtainStyledAttributes(attrs,
                    R.styleable.HeaderView);
            tvToolbarTitle.setText(attributes.getText(R.styleable.HeaderView_hv_title));
            if (attributes.getBoolean(R.styleable.HeaderView_hv_titleOnly, false)) {
                tvToolbarLeft.setVisibility(View.GONE);
                tvToolbarRight.setVisibility(View.GONE);
                imgToolbarLeft.setVisibility(View.GONE);
                imgToolbarRight.setVisibility(View.GONE);
            } else {
                String textLeft = attributes.getString(R.styleable.HeaderView_hv_leftText);
                String textRight = attributes.getString(R.styleable.HeaderView_hv_rightText);
                if (!TextUtils.isEmpty(textLeft)) {
                    tvToolbarLeft.setText(textLeft);
                    tvToolbarLeft.setVisibility(View.VISIBLE);
                } else {
                    tvToolbarLeft.setVisibility(View.GONE);
                }

                if (!TextUtils.isEmpty(textRight)) {
                    tvToolbarRight.setText(textRight);
                    tvToolbarRight.setVisibility(View.VISIBLE);
                } else {
                    tvToolbarRight.setVisibility(View.GONE);
                }
                int leftImgResId = attributes.getResourceId(R.styleable.HeaderView_hv_leftImage, 0);
                int rightImgResId = attributes.getResourceId(R.styleable.HeaderView_hv_rightImage, 0);

                if (leftImgResId != 0) {
                    imgToolbarLeft.setVisibility(View.VISIBLE);
                    imgToolbarLeft.setImageResource(
                            attributes.getResourceId(R.styleable.HeaderView_hv_leftImage, 0));
                } else {
                    imgToolbarLeft.setVisibility(View.GONE);
                }
                if (rightImgResId != 0) {
                    imgToolbarRight.setVisibility(View.VISIBLE);
                    imgToolbarRight.setImageResource(
                            attributes.getResourceId(R.styleable.HeaderView_hv_rightImage, 0));
                } else {
                    imgToolbarRight.setVisibility(View.GONE);
                }


            }
            attributes.recycle();
        }
        this.addView(view);
    }


    public void setTitle(String title) {
        if (TextUtils.isEmpty(title)) {
            tvToolbarTitle.setText(title);
        }
    }

    public void setImgLeftVisible(boolean visible) {
        imgToolbarLeft.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public void setImgRightVisible(boolean visible) {
        imgToolbarRight.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public void setTextRightVisible(boolean visible) {
        tvToolbarRight.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public void setTextLeftVisible(boolean visible) {
        tvToolbarLeft.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public interface HeaderListener {
        void onHeaderLeftImagePressed();

        void onHeaderLeftTextPressed();

        void onHeaderRightImagePressed();

        void onHeaderRightTextPressed();
    }
}
