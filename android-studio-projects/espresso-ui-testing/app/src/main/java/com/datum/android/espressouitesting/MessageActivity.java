package com.datum.android.espressouitesting;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_PICK = 16;
    private static final int REQUEST_PHONE_CALL = 1001;

    Button mDialog, mLogout, mMenu;
    TextView mName;
    EditText mCallerNumber;

    String name;

    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        getInit();

        mDialog.setOnClickListener(view -> getDialog());
        mLogout.setOnClickListener(view -> finish());

        getSpinnerData();
        mMenu.setOnClickListener(view -> startActivity(new Intent(this, BasicActivity.class)));

    }

    private void getInit() {
        mName = findViewById(R.id.tv_name);
        mDialog = findViewById(R.id.dialog);
        mLogout = findViewById(R.id.logout_button);
        spinner = findViewById(R.id.spinner);
        mCallerNumber = findViewById(R.id.edit_text_caller_number);
        mMenu = findViewById(R.id.menu_button);
    }

    private void getSpinnerData() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }


    private String buildToastMessage(String name) {
        return "Your name is " + name;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void getDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MessageActivity.this);
        builder.setTitle("Enter a name");

        EditText input = new EditText(MessageActivity.this);
        input.setId(R.id.et_input);
        input.setInputType(InputType.TYPE_CLASS_TEXT);

        builder.setView(input);

        builder.setPositiveButton("OK", (dialogInterface, i) -> {
            name = input.getText().toString();
            mName.setText(name);
            showToast(buildToastMessage(name));

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


    }

    public void enableDialog(AlertDialog dialog) {
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
    }

    public void disableDialog(AlertDialog dialog) {
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
    }

    public void onCall(View view) {
        boolean hasCallPhonePermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED;

        if (hasCallPhonePermission)
            startActivity(createCallIntentFromNumber());
        else
            ActivityCompat.requestPermissions(MessageActivity.this, new String[]    {
                    Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
    }

    private Intent createCallIntentFromNumber() {
        final Intent intentToCall = new Intent(Intent.ACTION_CALL);
        String number = mCallerNumber.getText().toString();
        intentToCall.setData(Uri.parse("tel:" + number));
        return intentToCall;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PICK) {
            if (resultCode == RESULT_OK) {
                mCallerNumber.setText(data.getExtras()
                        .getString(ContactActivity.KEY_PHONE_NUMBER));
            }
        }
    }



    public void onPick(View view) {
        final Intent pickContactIntent = new Intent(this, ContactActivity.class);
        startActivityForResult(pickContactIntent, REQUEST_CODE_PICK);
    }

}