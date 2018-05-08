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
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link login.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class login extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button bu;
    EditText e,e1;
    RadioGroup ra;

    FirebaseAuth fa;

    ProgressDialog pd;

    private OnFragmentInteractionListener mListener;

    public login() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment login.
     */
    // TODO: Rename and change types and number of parameters
    public static login newInstance(String param1, String param2) {
        login fragment = new login();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v1=inflater.inflate(R.layout.fragment_login, container, false);

        e=(EditText)v1.findViewById(R.id.editText3);

        e1=(EditText)v1.findViewById(R.id.editText4);

        ra=(RadioGroup)v1.findViewById(R.id.r);
        pd=new ProgressDialog(getContext());

        fa=FirebaseAuth.getInstance();
        if(fa.getCurrentUser()!=null)
        {
            Toast.makeText(getContext(), "Already logged in", Toast.LENGTH_SHORT).show();
            Intent i2=new Intent(getContext(),users.class);
            startActivity(i2);
        }


        bu=(Button)v1.findViewById(R.id.login_bu);
        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent in=new Intent(getContext(),admin.class);
                startActivity(in); */
                final String uname=e.getText().toString();
                if(TextUtils.isEmpty(uname))
                {
                    e.setError("Enter user name");
                }
                final String passw=e1.getText().toString();
                if(TextUtils.isEmpty(passw))
                {
                    e.setError("Enter password");
                }


                int id=ra.getCheckedRadioButtonId();
                if(id==R.id.o1)
                {
                    Intent i=new Intent(getContext(),admin.class);
                    startActivity(i);
                }
                else if(id==R.id.o2)
                {

                    //user login
                    pd.setMessage("Registering please wait...");
                    pd.show();

                    fa.signInWithEmailAndPassword(uname,passw).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    pd.dismiss();
                                    if(task.isSuccessful())
                                    {
                                        getActivity().finish();
                                        Intent i1=new Intent(getContext(),users.class);
                                        startActivity(i1);
                                    }
                                    else
                                    {
                                        //Toast.makeText(getContext(), (CharSequence) task.getException(),Toast.LENGTH_LONG).show();
                                    }

                                }
                            });

                }
                else
                {
                    AlertDialog.Builder alertDialog=new AlertDialog.Builder(getContext());
                    alertDialog.setMessage("Please choose the role");
                    alertDialog.show();
                    alertDialog.setCancelable(false);
                }


            }
        });

        return v1;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
