package com.danflorio.modernartui;

/**
 * MoreInformation.java
 *
 * Sets up the 'More Information" dialog
 **/

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class MoreInformation extends DialogFragment {
    // Create the dialog with the builder class
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_text)
                .setPositiveButton(R.string.website, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        doPositiveClick();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    // What happens when the user clicks 'Visit MoMa'
    public void doPositiveClick() {
        Intent visit = new Intent( Intent.ACTION_VIEW, Uri.parse("http://www.moma.org") );
        Intent chooser = Intent.createChooser(visit, getResources().getString(R.string.choose));
        startActivity(chooser);
    }
}
