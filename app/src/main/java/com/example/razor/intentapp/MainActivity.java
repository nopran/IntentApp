package com.example.razor.intentapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText websiteEditext;
    private EditText textShare ;
    private EditText nomorhp ;
    private EditText textSMS ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        websiteEditext = (EditText) findViewById(R.id.edtbrowser);
        textShare = (EditText) findViewById(R.id.edtTextShare);
        nomorhp = (EditText) findViewById(R.id.edtphone);
        textSMS = (EditText) findViewById(R.id.edtsms);
    }

    public void openlink(View view){

        String url = "http://" + (websiteEditext.getText().toString());
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        if (intent.resolveActivity(getPackageManager()) !=null){
            startActivity(intent);

        }else{
            Log.d(" Implicit Intens", "Can't handle this intent !");
        }

    }

    public void txtshare(View view){
        String txt = textShare.getText().toString();

        ShareCompat.IntentBuilder
            .from(this)
            .setType("text/plain")
                .setChooserTitle("Share This Text With : ")
                .setText(txt)
                .startChooser();
}

    public void phonecall(View view){
        String nomortelp = String.format("tel: %s", nomorhp.getText().toString());
        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
        dialIntent.setData(Uri.parse(nomortelp));

        if (dialIntent.resolveActivity(getPackageManager()) != null){
            startActivity(dialIntent);

        }else {
            Log.d("Phone Call:", "Cannot Call This Number");
        }

    }

    public void sendSMS (View view){
        String smsNumber = String.format("smsto: %s", nomorhp.getText().toString());
        String sms = textSMS.getText().toString();

        Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
        smsIntent.setData(Uri.parse(smsNumber));

        smsIntent.putExtra("sms_body", sms);

        if (smsIntent.resolveActivity(getPackageManager()) !=null){
            startActivity(smsIntent);

        }else {
            Log.d("SMS : ", " Can't resolve app for ACTION_SENDTO Intent.");
        }

    }


}
