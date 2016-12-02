package itm.immokapfenberg.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import itm.immokapfenberg.R;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import com.google.zxing.Result;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QRScannerActivity extends BaseActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scanner);
    }

    public void scanQRCode(View view){
        // Programmatically initialize the scanner view
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);

        // Register ourselves as a handler for scan results.
        mScannerView.setResultHandler(this);
        // Start camera
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();

        if (mScannerView != null) {
            // Stop camera on pause
            mScannerView.stopCamera();
        }
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here

        Log.i("QR Handler", rawResult.getText()); // Prints scan results
        Log.i("QR Handler", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode)

        int index = -1;

        String str = rawResult.getText();
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            index = Integer.parseInt(matcher.group(0));
        }

        if (index != -1) {
            Intent intent = new Intent(this, OneViewActivity.class);
            intent.putExtra("immoIndex", index);
            startActivity(intent);
        } else {
            Toast.makeText(this, "QR Code konnte nicht gelesen werden", Toast.LENGTH_SHORT).show();

            // If you would like to resume scanning, call this method below:
            mScannerView.resumeCameraPreview(this);
        }
    }
}
