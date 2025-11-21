package com.example.remoteadbutility;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.remoteadbutility.service.LoaderService;

public class Main extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        startService(
                new Intent(
                        this, LoaderService.class
                )
        );
    }

    public void changeStatus(String status) {
        findViewById(R.id.status);
    }
}
