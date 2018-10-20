package medical.firstresponse.com.civilianresponseapp;

<<<<<<< Updated upstream
import android.content.pm.PackageManager;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
=======
>>>>>>> Stashed changes
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

<<<<<<< Updated upstream
/**
 * https://developer.android.com/training/beam-files/send-files#java
 */
=======
import com.google.zxing.WriterException;

import java.io.IOException;

import medical.firstresponse.com.civilianresponseapp.GenerateQRActivity;

>>>>>>> Stashed changes
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
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());

<<<<<<< Updated upstream
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
=======



        /*GenerateQRActivity gQR = new GenerateQRActivity();

        try {
            byte[] data = gQR.generateQRCodeImage("QR Code", 350, 350, QR_CODE_IMAGE_PATH);
            Log.v("wow", "we did it! " + data);

        } catch (WriterException e) {
            Log.v("wow","Could not generate QR Code (WriterException)... " + e.getMessage());
        } catch (IOException e) {
            Log.v("wow","Could not generate QR Code (IOException)... " + e.getMessage());
        }
        */
>>>>>>> Stashed changes

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
