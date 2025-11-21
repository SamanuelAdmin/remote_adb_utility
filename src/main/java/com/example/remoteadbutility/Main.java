package com.example.remoteadbutility;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.example.remoteadbutility.service.LoaderService;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class Main extends Activity {
    private static String TAG = "RADBU_Main";
    private static String instructionUrl = "https://github.com/SamanuelAdmin/remote_adb_utility/blob/master/instructions.md?raw=true";

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


    @Override
    public void onStart() {
        super.onStart();
        new AcyncInstructionsLoader().execute(this.instructionUrl);
    }


    class AcyncInstructionsLoader extends AsyncTask<String, Integer, String> {

        public String getInstructions(String url) throws IOException {
            Document doc = Jsoup.connect(url).get();
            Element body = doc.body();
            return body.toString();
        }

        @Override
        protected String doInBackground(String... args) {
            String instructionsText;

            try {
                instructionsText = this.getInstructions(args[0]);
            } catch (IOException error) {
                Log.i(TAG, "Cannot parse instruction " + error.toString());
                instructionsText = String.format(
                                "Cannot load instructions.\nTried with: %s\nException: %s",
                                args[0], error
                        );
            }

            return instructionsText;
        }

        @Override
        protected void onPostExecute(String instructionsText) {
            super.onPostExecute(instructionsText);

            TextView instructions = findViewById(R.id.instructions);
            instructions.setText(instructionsText);
        }
    }
}
