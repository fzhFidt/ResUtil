package com.example.res;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.res.annotation.FieldType;
import com.example.res.annotation.ResField;

public class ActivityTest  extends AppCompatActivity  {
    @ResField
    private Button btnTest1;
    @ResField
    private Button btnTest2;
    @ResField
    private Button btnTest3;
    @ResField
    private Button btnTest4;
    @ResField
    private Button btnTest5;
    @ResField
    private Button btnTest6;
    @ResField(resId = R.id.btnTest7)
    private Button btnTest;
    @ResField(fieldType= FieldType.STRING)
    private String app_name;
    @ResField(fieldType= FieldType.STRING)
    private String app_name1;
    @ResField(fieldType = FieldType.COLOR)
    private int  colorPrimary1;
    private Boolean check;
    @ResField(fieldType = FieldType.DINEN)
    private float dimenWidth;
    @ResField(fieldType = FieldType.DINEN_PIXEL_SIZE)
    private int dimenHeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View mainView = LayoutInflater.from(this).inflate(R.layout.activity_main,null);
        setContentView(mainView);
        ResUtil.setResItem(this.getClass(),this,this,mainView);
        btnTest1.setText("btnTest1");
        btnTest2.setText("dimenWidth is "+getResources().getDimension(R.dimen.dimenWidth));
        btnTest3.setText("dimenHeight is "+getResources().getDimensionPixelSize(R.dimen.dimenHeight));
        btnTest4.setText("dimenHeight is "+dimenHeight);
        btnTest5.setText(" dimenWidth is "+dimenWidth);
        btnTest6.setText(app_name1);
        btnTest.setTextColor(colorPrimary1);
        btnTest.setText(app_name);
        Log.e("cl"," check is "+check+" int class is "+int.class);
        Log.e("cl"," colorPrimary is "+colorPrimary1);
        Log.e("cl"," dimen ");
    }

    private void btnTest(){

        int btnId = ResUtil.getIdResId(this,getPackageName(),"btnTest1");
        if(btnId > 0){
            btnTest1 = findViewById(btnId);
        }
         btnId = ResUtil.getIdResId(this,getPackageName(),"btnTest2");
        if(btnId > 0){
            btnTest2 = findViewById(btnId);
        }
         btnId = ResUtil.getIdResId(this,getPackageName(),"btnTest3");
        if(btnId > 0){
            btnTest3 = findViewById(btnId);
        }
         btnId = ResUtil.getIdResId(this,getPackageName(),"btnTest4");
        if(btnId > 0){
            btnTest4 = findViewById(btnId);
        }
         btnId = ResUtil.getIdResId(this,getPackageName(),"btnTest5");
        if(btnId > 0){
            btnTest5 = findViewById(btnId);
        }
         btnId = ResUtil.getIdResId(this,getPackageName(),"btnTest6");
        if(btnId > 0){
            btnTest6 = findViewById(btnId);
        }
        btnId = ResUtil.getIdResId(this,getPackageName(),"btnTest7");
        if(btnId > 0){
            btnTest = findViewById(btnId);
        }
    }
}
