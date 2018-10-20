package medical.firstresponse.com.civilianresponseapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.net.URI;
import java.net.URISyntaxException;
import java.lang.String;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;

/**
 * https://developer.android.com/training/beam-files/send-files#java
 */
//import com.google.zxing.WriterException;
import com.google.zxing.WriterException;

import org.json.JSONObject;

import java.io.IOException;

import medical.firstresponse.com.civilianresponseapp.GenerateQRActivity;

public class MainActivity extends AppCompatActivity {

    NfcAdapter mNfcAdapter;
    // Flag to indicate that Android Beam is available
    boolean mAndroidBeamAvailable  = false;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.editText);
        //tv.setText(stringFromJNI());

        // NFC isn't available on the device
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_NFC)) {
            /*
             * Disable NFC features here.
             * For example, disable menu items or buttons that activate
             * NFC-related features
             */
            // Android Beam file transfer isn't supported
        } else if (Build.VERSION.SDK_INT <
                Build.VERSION_CODES.JELLY_BEAN_MR1) {
            // If Android Beam isn't available, don't continue.
            mAndroidBeamAvailable = false;
            /*
             * Disable Android Beam file transfer features here.
             */
            // Android Beam file transfer is available, continue
        } else {
            mAndroidBeamAvailable = true;
            mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        }
    }

    // List of URIs to provide to Android Beam
    private Uri[] mFileUris = new Uri[10];
    /**
     * Callback that Android Beam file transfer calls to get
     * files to share
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private class FileUriCallback implements
            NfcAdapter.CreateBeamUrisCallback {
        public FileUriCallback() {
        }
        /**
         * Create content URIs as needed to share with another device
         */
        @Override
        public Uri[] createBeamUris(NfcEvent event) {
            return mFileUris;
        }
    }

    /**
     * for opening up QR code
     * @param view
     */
    public void openQR(View view) {
        // Do something in response to button

        try {
            makeCall();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(MainActivity.this, QR_Display.class);
        startActivity(intent);
    }


    public void makeCall() throws URISyntaxException {
        String ACCOUNT_SID = "ACf3723639b9db9b2f947802c4da67832c";
        String AUTH_TOKEN = "36a967029cbc835c3a8ab037cce979fe";

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        String from = "+18722190190";
        String to = "+12245454352";

        Call call = Call.creator(new PhoneNumber(to), new PhoneNumber(from),
                new URI("")).create();

        Log.v("call", call.getSid());
    }

    /*public static class MakePhoneCall {
        public static final String ACCOUNT_SID = "ACf3723639b9db9b2f947802c4da67832c";
        public static final String AUTH_TOKEN = "36a967029cbc835c3a8ab037cce979fe";

        public static void main(String[] args) throws URISyntaxException {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            String from = "+18722190190";
            String to = "+12245454352";

            // MAKE SURE FILE PATH IS CORRECT FOR DEPLOYING DEVICE
            Call call = Call.creator(new PhoneNumber(to), new PhoneNumber(from),
                    new URI("/Users/mattstevenson/Desktop/FirstNet_Hackathon/Medical-Badge/CivilianAlertApp/app/src/main/Response.xml")).create();

            Log.v("call", call.getSid());
        }
    }*/

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
