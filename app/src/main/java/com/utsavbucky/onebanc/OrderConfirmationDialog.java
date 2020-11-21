package com.utsavbucky.onebanc;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


public class OrderConfirmationDialog extends DialogFragment {
    RelativeLayout dismissButton ;
    private View dialogView;

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Context context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.Dialog);
        final LayoutInflater inflater = LayoutInflater.from(context);
        dialogView = inflater.inflate(R.layout.order_confirmation_dialog,null);
        dismissButton = dialogView.findViewById(R.id.homeBtn);

        dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                getDialog().dismiss();
            }
        });

        builder.setView(dialogView);

        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            Window window = getDialog().getWindow();
            Point size = new Point();
            Display display = window.getWindowManager().getDefaultDisplay();
            display.getSize(size);
            window.setLayout((int) (size.x * 0.85), WindowManager.LayoutParams.WRAP_CONTENT);
        }
    }
}
