package vn.nms.core.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import vn.nms.core.R;


public class DialogUtils {

    public static AlertDialog createMessageDialog(
            Context context, String title, String message,
            String confirm, String cancel,
            @Nullable DialogInterface.OnClickListener confirmListener,
            @Nullable DialogInterface.OnClickListener cancelListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.MessageDialogAnimation);
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        if (!TextUtils.isEmpty(message)) {
            builder.setMessage(message);
        }
        if (!TextUtils.isEmpty(confirm)) {
            builder.setPositiveButton(confirm, confirmListener);
        } else {
            builder.setPositiveButton(R.string.ok, confirmListener);
        }
        if (!TextUtils.isEmpty(cancel)) {
            builder.setNegativeButton(cancel, cancelListener);
        } else {
            builder.setNegativeButton(R.string.cancel, cancelListener);
        }
        return builder.create();
    }

    public static AlertDialog createMessageDialog(
            Context context, String title, String message, String cancel,
            @Nullable DialogInterface.OnClickListener cancelListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.MessageDialogAnimation);
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        if (!TextUtils.isEmpty(message)) {
            builder.setMessage(message);
        }
        if (!TextUtils.isEmpty(cancel)) {
            builder.setNegativeButton(cancel, cancelListener);
        } else {
            builder.setNegativeButton(R.string.dismiss, cancelListener);
        }
        return builder.create();
    }

    public static AlertDialog createCustomMessageDialog(
            Context context, String title, String message, String cancel,
            @Nullable DialogInterface.OnClickListener cancelListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.MessageDialogAnimation);
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        if (!TextUtils.isEmpty(message)) {
            builder.setMessage(message);
        }
        if (!TextUtils.isEmpty(cancel)) {
            builder.setNegativeButton(R.string.dismiss, cancelListener);
        }
        return builder.create();
    }

}
