package itm.immokapfenberg.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import itm.immokapfenberg.R;

public class ContactActivity extends BaseActivity {

    EditText inputName;
    EditText inputEmail;
    EditText inputSubject;
    EditText inputInfo;
    String name;
    String email;
    String subject;
    String info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        inputName = (EditText) findViewById(R.id.nameInput);
        inputEmail = (EditText) findViewById(R.id.emailInput);
        inputSubject = (EditText) findViewById(R.id.subjectInput);
        inputInfo = (EditText) findViewById(R.id.infoInput);
    }

    public boolean sendContactForm(View v){
        Log.i("INFO","Trying to send contact form");
        name = inputName.getText().toString();
        email = inputEmail.getText().toString();
        subject = inputSubject.getText().toString();
        info = inputInfo.getText().toString();

        Log.i("Name", name);

        if(name.isEmpty()){
            Log.e("Error", "No name entered.");
            return false;
        }

        if (email.isEmpty()){
            Log.e("Error", "No email entered.");
            return false;
        }

        if (subject.isEmpty()) {
            Log.i("Info", "No subject entered.");
            subject = "default";
        }
        if (info.isEmpty()){
            Log.i("Info", "No info entered.");
        }

        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(email);
        boolean matchFound = m.matches();
        if (!matchFound) {
            Log.e("ERROR", "No valid email address.");
            return false;
        }

        Log.i("Name: ", name);
        Log.i("E-Mail: ", email);
        Log.i("Subject: ", subject);
        Log.i("Info: ", info);

        //TODO: Send form via email etc. to contact person
        return true;
    }
}
