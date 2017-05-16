package rayckaprojects.tfg;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MailActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn;
    EditText to;
    EditText subject;
    EditText body;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);

        btn = (Button)findViewById(R.id.btnSend);
        btn.setOnClickListener(this);
        to= (EditText)findViewById(R.id.mailTo);
        subject= (EditText)findViewById(R.id.mailSubject);
        body= (EditText)findViewById(R.id.mailBody);

    }

    @Override
    public void onClick(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL  ,to.getText().toString());
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
        emailIntent.putExtra(Intent.EXTRA_TEXT   ,body.getText().toString());
        startActivity(emailIntent);
    }

}
