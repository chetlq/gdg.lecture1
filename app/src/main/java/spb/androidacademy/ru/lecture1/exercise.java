package spb.androidacademy.ru.lecture1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class exercise extends AppCompatActivity {

    private static final String YOUR_EMAIL = "chetl@mail.ru";
    private static final String KEY_INDEX = "index";

    private EditText messageEdit;
    private  String message;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i("orientation", "onSaveInstanceState");
        savedInstanceState.putString(KEY_INDEX, messageEdit.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_exercise );

        messageEdit = findViewById(R.id.messageEditText);

        if (savedInstanceState != null) {
            messageEdit.setText( savedInstanceState.getString(KEY_INDEX, "") );
        }

        Button activityButton = findViewById(R.id.activityMessageButton);
        activityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });

        Button emailButton = findViewById(R.id.emailMessageButton);
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEmail();
            }
        });
    }
    private void openActivity() {
        MessageActivity.start(this, messageEdit.getText().toString());
        //MessageActivity.start(this, messageEdit.getText().toString());

    }

    private void openEmail() {
        message = messageEdit.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData( Uri.parse("mailto:"))
                .putExtra(Intent.EXTRA_EMAIL, YOUR_EMAIL)
                .putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject))
                .putExtra(Intent.EXTRA_TEXT, message);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.email_sending_error, Toast.LENGTH_LONG).show();
        }

    }
}
