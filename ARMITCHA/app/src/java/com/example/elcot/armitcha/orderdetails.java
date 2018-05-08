package com.example.elcot.armitcha;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;

public class orderdetails extends AppCompatActivity {

    Button b;
    Firebase f;
    FirebaseAuth f1;
    private static int uniq_id=1;
    EditText e,e1,e2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetails);

        Firebase.setAndroidContext(this);
        f1=FirebaseAuth.getInstance();

        e=(EditText)findViewById(R.id.city);
        e1=(EditText)findViewById(R.id.pincode);
        e2=(EditText)findViewById(R.id.flatnoandstreet);

        b=(Button)findViewById(R.id.confirm);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Firebase fm=new Firebase("https://armitcha-d0028.firebaseio.com/orders/"+uniq_id);
                uniq_id++;



                Firebase fg=fm.child("user");
                fg.setValue(f1.getCurrentUser());

                Firebase fg1=fm.child("city");
                fg1.setValue(e.getText().toString());

                Firebase fg2=fm.child("pincode");
                fg2.setValue(e1.getText().toString());

                Firebase fg3=fm.child("address");
                fg3.setValue(e2.getText().toString());


                Toast.makeText(getApplicationContext(),"Order taken!!!",Toast.LENGTH_LONG).show();
                finish();
                Intent in=new Intent(orderdetails.this,products.class);
                startActivity(in);
            }
        });
    }
}
