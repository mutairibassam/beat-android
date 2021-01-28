package com.datum.android.espressouitesting;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogBehavior;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.internal.main.DialogLayout;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MessageActivity extends AppCompatActivity {

    Button mDialog, mLogout;
    TextView mName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        mName = findViewById(R.id.tv_name);

        mDialog = findViewById(R.id.dialog);
        mDialog.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MessageActivity.this);
            builder.setTitle("Enter a name");

            EditText input = new EditText(MessageActivity.this);
            input.setId(R.id.et_input);
            input.setInputType(InputType.TYPE_CLASS_TEXT);

            builder.setView(input);

            builder.setPositiveButton("OK", (dialogInterface, i) -> {
                mName.setText(input.getText().toString());
            });

            final AlertDialog dialog = builder.create();
            dialog.show();

            disableDialog(dialog);
            input.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                    if (TextUtils.isEmpty(s)) disableDialog(dialog);
                    else enableDialog(dialog);

                }
            });

        });

        mLogout = findViewById(R.id.logout_button);
        mLogout.setOnClickListener(view -> finish());

    }

    public void enableDialog(AlertDialog dialog) {
        ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
    }

    public void disableDialog(AlertDialog dialog) {
        ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
    }

}