package com.example.john.sunset;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

/**
 * Created by john on 2016/10/10.
 */

public class DialogFragment extends android.support.v4.app.DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity()).setTitle("hha").setIcon(R.mipmap.ic_launcher)
                .setMessage("zhessi").create();
    }
}
