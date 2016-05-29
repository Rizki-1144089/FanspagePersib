package com.fanspage.persib.fanspagepersib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ImageView;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;


public class Login extends AppCompatActivity {
    Intent i=null;
    ImageView im=null;
    EditText tv1,tv4;
    boolean flag=false;
    SQLiteDatabase db=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        im=(ImageView)findViewById(R.id.show_hide2);
        tv1=(EditText)findViewById(R.id.username2);
        tv4=(EditText)findViewById(R.id.password2);
        db=openOrCreateDatabase("persib.db", MODE_PRIVATE, null);
        //db.execSQL("create table login(name varchar,username varchar,email_id varchar,password varchar,flag varchar)");

        //im.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View arg0) {

              //  if(flag==false)
                //{
                  //  im.setImageResource(R.drawable.hide);
                   // tv4.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    //flag=true;
                //}
                //else
                //{
                  //  im.setImageResource(R.drawable.show);
                    //tv4.setInputType(129);
                    //flag=false;

             //   }
            //}
        //});
    }

    public void action(View v)
    {
        switch(v.getId())
        {
            case R.id.signin2:
                i=new Intent(this,Signin.class);
                startActivityForResult(i, 500);
                overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_bottom);
                finish();
                break;
            case R.id.start:
                String username=tv1.getText().toString();
                String password=tv4.getText().toString();
                if(username==null||username==""||username.length()<1)
                {
                    show("Please Enter Correct username.");
                }
                else if(password==null||password==""||password.length()<6)
                {
                    show("Please Enter Correct Password.");
                }
                else
                {
                    Cursor c=db.rawQuery("select * from login where username='"+username+"' and password='"+password+"'",null);
                    c.moveToFirst();
                    if(c.getCount()>0)
                    {
                        i=new Intent(this,data_member.class);
                        startActivityForResult(i,500);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        db.close();
                        finish();
                    }
                    else
                        show("Wrong Password or Username.");

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
