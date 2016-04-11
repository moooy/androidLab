package com.example.datastorage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SQLiteActivity extends AppCompatActivity {

    private Button mCreateButton;
    private MySQLiteOpenHelper mMySQLiteOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        mCreateButton = (Button) findViewById(R.id.create_btn);

        mMySQLiteOpenHelper = new MySQLiteOpenHelper(this,"book.db", null,1);
    }

    public void onCreateClick(View view) {

        mMySQLiteOpenHelper.getReadableDatabase();
    }
}
