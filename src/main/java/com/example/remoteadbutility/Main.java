package com.example.remoteadbutility;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.remoteadbutility.service.LoaderService;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Main extends Activity {
    private static String TAG = "RADBU_Main";
    private static String instractionUrl = "";

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


    public String getInstructions(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        return doc.toString();
    }

    public void loadInstructions() {
        TextView instructions = this.findViewById(R.id.instructions);

        try {
            instructions.setText(getInstructions(instractionUrl));
        } catch (IOException error) {
            Log.i(TAG, "Cannot parse instruction %s".formatted(error));
            instructions.setText(
                    "Cannot load instructions.\nTried with: <a href=\"%s\">%s</a>"
                    .formatted(instractionUrl, instractionUrl)
            );
        }
    }

    public void changeStatus(String status) {
        findViewById(R.id.status);
    }
}
