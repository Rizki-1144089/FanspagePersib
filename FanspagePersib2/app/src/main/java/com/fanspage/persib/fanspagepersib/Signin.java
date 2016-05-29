package com.fanspage.persib.fanspagepersib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ImageView;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;
public class Signin extends AppCompatActivity {
    Intent i=null;
    ImageView im=null;
    EditText tv1,tv2,tv3,tv4;
    boolean flag=false;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        im=(ImageView)findViewById(R.id.show_hide);
        tv1=(EditText)findViewById(R.id.name);
        tv2=(EditText)findViewById(R.id.email_id);
        tv3=(EditText)findViewById(R.id.username);
        tv4=(EditText)findViewById(R.id.password);
        db=openOrCreateDatabase("persib.db", MODE_PRIVATE, null);
        db.execSQL("create table if not exists login(name varchar,username varchar,email_id varchar,password varchar,flag varchar)");


    }

    public void action(View v)
    {
        switch(v.getId())
        {
            case R.id.login:
                i=new Intent(this,Login.class);
                startActivityForResult(i, 500);
                overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_bottom);
                finish();
                break;
            case R.id.signin:
                String name=tv1.getText().toString();
                String email_id=tv2.getText().toString();
                String username=tv3.getText().toString();
                String password=tv4.getText().toString();
                if(name==null||name==""||name.length()<3)
                {
                    show("Please Enter Correct Name.");
                }
                else if(username==null||username==""||username.length()<1)
                {
                    show("Please Enter Correct username.");
                }
                else if(email_id==null||email_id==""||email_id.length()<10)
                {
                    show("Please Enter Correct Email id.");
                }
                else if(password==null||password==""||password.length()<6)
                {
                    show("Please Enter Strong Password. Minimal 5 karakter");
                }
                else
                {
                    db.execSQL("insert into login values('"+name+"','"+username+"','"+email_id+"','"+password+"','nothing')");
                    i=new Intent(this,data_member.class);
                    startActivityForResult(i, 500);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    db.close();
                    finish();
                }
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void show(String str)
    {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }
}
