package com.example.datastorage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPreferencesActivity extends AppCompatActivity {

    private TextView nameTV,ageTV,readNameTV,readAgeTV;
    private EditText nameET,ageET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        nameTV = (TextView) findViewById(R.id.name_text);
        ageTV = (TextView) findViewById(R.id.age_text);
        nameET = (EditText) findViewById(R.id.name_edit);
        ageET = (EditText) findViewById(R.id.age_edit);
        readNameTV = (TextView) findViewById(R.id.readName_text);
        readAgeTV = (TextView) findViewById(R.id.readAge_text);
    }

    public void onSaveClick(View view) {
        SharedPreferences.Editor editor = getSharedPreferences("userinfo",MODE_PRIVATE).edit();
        editor.putString(nameTV.getText().toString(),nameET.getText().toString());
        editor.putString(ageTV.getText().toString(),ageET.getText().toString());
        editor.commit();
    }

    public void onReadClick(View view) {
        //读取的时候直接使用sharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("userinfo",MODE_PRIVATE);
        //SharedPreferences的get方法的两个参数为key和没有值时返回的默认值
        readNameTV.setText(sharedPreferences.getString(nameTV.getText().toString(),""));
        readAgeTV.setText(sharedPreferences.getString(ageTV.getText().toString(),"0"));
    }

    public void onNextClick(View view) {
        startActivity(new Intent(this,SQLiteActivity.class));
    }


    /**
     * sharedPreferences的要点
     * 1.存储的值的形式是键值对，存储的对象是基本的数据对象，包括几种基本的类型
     * 2.获取SharedPreferences的三种方式
     *   (1)Context的getSharedPreferences(文件名，写入模式)方法
     *   (2)Activity的getPreferences()方法，获取Activity中默认的shared preference文件，这个文件是Activity私有的，所以不需要指定名字。
     *   (3)PreferenceManager 类中的 getDefaultSharedPreferences()方法.这是一个静态方法，它接收一个 Context 参数，并自动使用当前应用程序的包名
           作为前缀来命名 SharedPreferences 文件。
        3.读取和写入的过程如上代码所示
     */
}
