package com.example.basictech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.tps.TpsManager;
import com.tencent.opentelemetry.core.TpsTelemetrySdk;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TpsManager tpsManager = new TpsManager();
        tpsManager.init();
        TpsTelemetrySdk.getTpsTelemetrySdk().setTenantID("tenantID").withDefaultExporter();
    }
}
