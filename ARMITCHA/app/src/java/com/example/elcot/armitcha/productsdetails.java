package com.example.elcot.armitcha;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class productsdetails extends AppCompatActivity {

    private TextView tv,tv1,tv2;
    private ImageView im;
    Button b;
    FirebaseAuth f;
    int image;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productsdetails);
       // tv=(TextView)findViewById(R.id.t);
        //tv1=(TextView)findViewById(R.id.t1);
        //tv2=(TextView)findViewById(R.id.t2);
        im=(ImageView) findViewById(R.id.item_img_id1);
        b=(Button) findViewById(R.id.buynow_id);

        f=FirebaseAuth.getInstance();



        //receive the data

        Intent intent=getIntent();
        String Title=intent.getExtras().getString("Title");
        String Description=intent.getExtras().getString("Description");
        image=intent.getExtras().getInt("Thumbnail");

       // tv.setText(Title);
        //tv1.setText(Description);
        im.setImageResource(image);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(f.getCurrentUser()==null)
                {

                    Toast.makeText(getApplicationContext(),"Please login ",Toast.LENGTH_LONG).show();
                }
                else
                {

                    sharedPreferences=getSharedPreferences("MyPreference", Context.MODE_WORLD_WRITEABLE);
                    SharedPreferences.Editor e=sharedPreferences.edit();
                    e.putInt("uri",image);
                    e.commit();

                    Intent in=new Intent(productsdetails.this,orderdetails.class);
                    startActivity(in);
                }

            }
        });


    }
}
