package com.example.elcot.armitcha;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class users extends AppCompatActivity {

    CardView cardView,cardView1,cardView2,cardView3;
    FirebaseAuth fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        setTitle("Dashboard");

        fb=FirebaseAuth.getInstance();
        FirebaseUser fu=fb.getCurrentUser();

        if(fb.getCurrentUser()==null)
        {
            finish();
            startActivity(new Intent(users.this,homepage.class));
        }

        cardView=(CardView)findViewById(R.id.account);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(users.this,account.class);
                startActivity(intent);
            }
        });

        cardView1=(CardView)findViewById(R.id.shopping);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(users.this,products.class);
                startActivity(intent);
            }
        });

        cardView2=(CardView)findViewById(R.id.log);
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fb.signOut();
                finish();
                startActivity(new Intent(users.this,homepage.class));

            }
        });

        cardView3=(CardView)findViewById(R.id.ord);
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sh=getSharedPreferences("MyPreference", Context.MODE_WORLD_READABLE);
                SharedPreferences.Editor e=sh.edit();
                //int i=e.
            }
        });
    }
}
