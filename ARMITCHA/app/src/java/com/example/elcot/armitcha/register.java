package com.example.elcot.armitcha;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link register.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link register#newInstance} factory method to
 * create an instance of this fragment.
 */
public class register extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FirebaseAuth firebaseAuth;

    ProgressBar br;
    ProgressDialog pg;

    private EditText et,et1,et2,et3,et4;
    private Button bu;
    String username,password,repassword,email,contactno;
    public static final String PASSWORD_PATTERN="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";


    private static int uniq_id=1,uniq_id1=1;

    RadioGroup radioGroup;

    private OnFragmentInteractionListener mListener;

    public register() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment register.
     */
    // TODO: Rename and change types and number of parameters
    public static register newInstance(String param1, String param2) {
        register fragment = new register();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_register, container, false);


        et=(EditText)view.findViewById(R.id.regname);
        et1=(EditText)view.findViewById(R.id.regemail);
        et2=(EditText)view.findViewById(R.id.regpass);
        et3=(EditText)view.findViewById(R.id.regrepass);
        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==0)
                {
                    et2.setError("weak");
                }
                else if(s.length()<4)
                {
                    et2.setError("Medium");
                }
                else if(s.length()>4&&s.length()<8)
                {
                    et2.setError("weak");
                }
                else
                {

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        et4=(EditText)view.findViewById(R.id.regcontactno);
        bu=(Button)view.findViewById(R.id.signin);
        radioGroup=(RadioGroup)view.findViewById(R.id.radio_grp);

        firebaseAuth=FirebaseAuth.getInstance();/*
        if(firebaseAuth.getCurrentUser()!=null)
        {
            Intent i=new Intent(getContext(),users.class);
            startActivity(i);
        } */
        pg=new ProgressDialog(view.getContext());


        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                username=et.getText().toString().trim();
                email=et1.getText().toString().trim();
                password=et2.getText().toString().trim();
                repassword=et3.getText().toString().trim();
                contactno=et4.getText().toString().trim();
                int checked_tem=radioGroup.getCheckedRadioButtonId();
               Firebase.setAndroidContext(getContext());
                if(checked_tem==R.id.opt1)
                {
                    Firebase fm=new Firebase("https://armitcha-d0028.firebaseio.com/users/employee/"+uniq_id);
                    uniq_id++;
                    insertCredentials(fm,username,password,repassword,contactno,email);
                }
                else
                {
                    Firebase fm=new Firebase("https://armitcha-d0028.firebaseio.com/users/customer/"+uniq_id1);
                    uniq_id1++;
                    insertCredentials(fm,username,password,repassword,contactno,email);
                }

                Toast.makeText(getContext(),"welcome "+username,Toast.LENGTH_LONG).show();


                //create a user
                if(isvalidName(username)==true&&isvalidPass(password)==true&& isvalidEmail(email)==true && isvalidMobno(contactno)==true)
                {
                    pg.setMessage("Registering please wait.......");
                    pg.show();
                    if(isvalidEmail(email)==true) {
                        firebaseAuth.createUserWithEmailAndPassword(email,password)
                                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (!task.isSuccessful()) {
                                            Toast.makeText(getContext(), "Authentication failed." + task.getException(),
                                                    Toast.LENGTH_SHORT).show();
                                        } else {
                                            pg.dismiss();

                                            AlertDialog.Builder alertDialog=new AlertDialog.Builder(getContext());
                                            alertDialog.setMessage("User created!!!");
                                            alertDialog.setPositiveButton("Go to Dashboard", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    getActivity().finish();
                                                    Intent intent=new Intent(getContext(),users.class);
                                                    startActivity(intent);
                                                }
                                            });
                                            alertDialog.show();
                                            alertDialog.setCancelable(false);


                                        }
                                    }
                                });
                    }

                   // String id=UserDataBase.push().getKey();
                    //userdb db=new userdb(name.getText().toString(),emailid.getText().toString(),mob.getText().toString());
                    //UserDataBase.child(id).setValue(db);
                }
                else
                {
                    Toast.makeText(getContext(), "Input deatails are incorrect!!!",
                            Toast.LENGTH_SHORT).show();
                }



            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void insertCredentials(Firebase fm,String username,String password,String repassword,String contactno,String email)
    {
        Firebase fg=fm.child("name");
        fg.setValue(username);

        fg=fm.child("password");
        fg.setValue(password);

        fg=fm.child("re_password");
        fg.setValue(repassword);

        fg=fm.child("contact_no");
        fg.setValue(contactno);

        fg=fm.child("email");
        fg.setValue(email);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    /*validate the inputs */
    public boolean isvalidName(String val1)
    {
        if(val1.isEmpty()==true)
        {
            et.setError("plz enter your name");
            return true;
        }
        else if(val1.matches("[a-zA-Z.? ]*")==false)
        {
            et.setError("invalid input");
            return true;
        }
        else
        {
            return true;
        }
    }

    public boolean isvalidMobno(String val3)
    {
        if(val3.isEmpty()==true)
        {
            et4.setError("plz enter your Mobile No");
            return true;
        }
        else if(val3.length()!=10)
        {
            et4.setError("plz enter valid mobile no");
            return true;
        }
        else {
            return true;
        }
    }

    public boolean isvalidPass(String val4)
    {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(val4);
        if(val4.isEmpty()==true)
        {
            et2.setError("plz enter password");
            return true;
        }
        else if(matcher.matches()==false)
        {
            et2.setError("password must contains alphabet and number and special character");
            return true;
        }
        else {
            return true;
        }
    }

    public boolean passwordCompare(String a,String b)
    {
        if(a!=b)
        {
            et3.setError("password does not match");
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean isvalidEmail(String val6)
    {
        if(val6.isEmpty()==true)
        {
            et1.setError("plz enter your mail id");
            return true;
        }
        else if(Patterns.EMAIL_ADDRESS.matcher(val6).matches()==false)
        {
            et1.setError("please enter valid email");
            return true;
        }
        else {
            return true;
        }
    }
    /*end of validation of inputs */


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
