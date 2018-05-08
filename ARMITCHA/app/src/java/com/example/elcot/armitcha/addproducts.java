package com.example.elcot.armitcha;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;


import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link addproducts.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link addproducts#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addproducts extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button b,b1,b2;
    private ImageView i;

    private static int pos=1;

    private StorageReference mStorage;
    DatabaseReference mDatabase;

    private static final int GALLERY_INTENT=2;
    Uri uri;

    private OnFragmentInteractionListener mListener;

    public addproducts() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment addproducts.
     */
    // TODO: Rename and change types and number of parameters
    public static addproducts newInstance(String param1, String param2) {
        addproducts fragment = new addproducts();
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
        final View view=inflater.inflate(R.layout.fragment_addproducts, container, false);

        b=(Button)view.findViewById(R.id.take_pic);
        i=(ImageView)view.findViewById(R.id.prod_img);

        b1=(Button)view.findViewById(R.id.upload);

        mStorage= FirebaseStorage.getInstance().getReference();


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent oin=new Intent(Intent.ACTION_PICK);
                oin.setType("image/*");
                startActivityForResult(oin,GALLERY_INTENT);
            }
        });
/* color picker
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ColorPicker colorPicker=new ColorPicker(getContext());
                ArrayList<String> colors=new ArrayList<>();
                colors.add("#258174");
                colors.add("#3C8D2F");
                colors.add("#20724f");
                colors.add("#6a3ad2");
                colors.add("#323299");
                colors.add("#800080");
                colors.add("#b79716");
                colors.add("#966d37");
                colors.add("#b77231");
                colors.add("#808000");

                colorPicker.setColors(colors)
                        .setColumns(5)
                        .setRoundColorButton(true)
                        .setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                            @Override
                            public void onChooseColor(int position, int color) {

                            }
                            @Override
                            public void onCancel() {

                            }
                        }).show();
            }
        }); */

        //upload image
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return view;
    }

    /* take image from gallery

                Intent pickPhoto=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto,1);  */

    public void onActivityResult(int requestCode,int resultCode,Intent imageReturnedIntent)
    {
        super.onActivityResult(requestCode,resultCode,imageReturnedIntent);

        if(requestCode==GALLERY_INTENT&&resultCode==RESULT_OK)
        {
            uri=imageReturnedIntent.getData();
            i.setImageURI(uri);

            StorageReference filepath=mStorage.child("Photos"+pos);
            pos++;

            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    AlertDialog.Builder alertDialog=new AlertDialog.Builder(getContext());
                    alertDialog.setTitle("Successfully uploaded!!!");
                    alertDialog.setIcon(R.drawable.tick);

                    alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    alertDialog.show();
                    alertDialog.setCancelable(false);
                }
            });
            filepath.putFile(uri).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
            });

        }
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
