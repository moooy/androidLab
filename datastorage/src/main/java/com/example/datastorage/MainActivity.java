package com.example.datastorage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText dataET;
    private TextView dataTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataET = (EditText) findViewById(R.id.data_edit);
        dataTV = (TextView) findViewById(R.id.read_text);
    }

    public void onSaveClick(View view) {
        String data = dataET.getText().toString();
        saveToFile(data);
    }

    private void saveToFile(String data) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = openFileOutput("data",MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (writer !=null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onReadClick(View view) {
        String data = readFromFile();
        dataTV.setText(data);
    }

    private String readFromFile() {
        FileInputStream inputStream = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            inputStream = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while ((line = reader.readLine())!=null){
                content.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }

    public void onNextClick(View view) {
      startActivity(new Intent(this,SharedPreferencesActivity.class));
    }
}
