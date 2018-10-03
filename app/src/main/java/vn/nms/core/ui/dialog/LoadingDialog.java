package vn.nms.core.ui.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import vn.nms.core.R;

public class LoadingDialog extends ProgressDialog {

    private int msgId = 0;

    public LoadingDialog(Context context) {
        super(context);
    }

    public LoadingDialog(Context context, int msg) {
        super(context);
        this.msgId = msg;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_processing);
        if (getWindow() != null) {
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getWindow().getAttributes().windowAnimations = R.style.ProcessingDialogAnimation;
        }
    }

    @Override
    public void show() {
        super.show();
        TextView textView = findViewById(R.id.message);
        if (msgId != 0) {
            textView.setText(msgId);
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.GONE);
        }
    }

}