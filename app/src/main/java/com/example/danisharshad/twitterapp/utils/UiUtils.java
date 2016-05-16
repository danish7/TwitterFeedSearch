package com.example.danisharshad.twitterapp.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.danisharshad.twitterapp.R;

/**
 * Created by danisharshad on 5/15/16.
 */
public class UiUtils {

    public static void hideKeyBoard (View v) {
        InputMethodManager inputManager = (InputMethodManager) v.getContext().
                                    getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * Creates a default Android alert dialog with provided title, and message
     * @param context The context in which to display the alert dialog
     * @param titleId The resource id of the title string
     * @param stringId The resource id of the dialog message string
     */
    public static void showSimpleDialog(Context context, int titleId, int stringId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(stringId).setNeutralButton(R.string.ok, null);
        builder.setTitle(titleId);
        builder.show();
    }
}
