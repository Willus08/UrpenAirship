package posidenpalace.com.urpenairship;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.urbanairship.Autopilot;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private static final int SCANNER_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Autopilot.automaticTakeOff(this);

    }


    public void MemoryLeak(View view) {
        TestAsyc testAsyc = new TestAsyc();

        testAsyc.execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Startup.instance.MustDie(this);
    }

    public void scan(View view) {
        IntentIntegrator integrate = new IntentIntegrator(this);
        integrate.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrate.setPrompt("Scan");
        integrate.setCameraId(0);
        integrate.setBeepEnabled(false);
        integrate.initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,intent);
        if (result != null) {
            // Handle scan intent
            if (result.getContents() == null) {
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();

            } else {
                Timber.d("Scaned");
                Toast.makeText(this, "Scaned" + result.getContents(), Toast.LENGTH_SHORT).show();
            }
        }else {
            super.onActivityResult(requestCode,resultCode,intent);
        }

    }
}
