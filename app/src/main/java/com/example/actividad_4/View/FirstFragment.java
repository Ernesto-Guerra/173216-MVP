package com.example.actividad_4.View;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.actividad_4.Model.FirstFragmentModel;
import com.example.actividad_4.R;
import com.example.actividad_4.Tools.Cliente;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FirstFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    public FirstFragmentModel first;
    public static boolean creando = true;
    public static TextView campo1;
    public static TextView campo2;
    public static TextView campo3;
    public static String idedit = "";

    private Button aubutton;
    private Button cancelbutton;

    private OnFragmentInteractionListener mListener;

    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */

    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        first = new FirstFragmentModel(FirstFragment.this);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View mimenu = inflater.inflate(R.layout.fragment_first,container,false);



        return first.obtenerDatos(mimenu);


    }

    @Override
    public void onStart(){
        super.onStart();

        campo1 = (TextView) getView().findViewById(R.id.nombre);
        campo2 = (TextView) getView().findViewById(R.id.apellido);
        campo3 = (TextView) getView().findViewById(R.id.edad);

        aubutton = (Button) getView().findViewById(R.id.addupdate);

        aubutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                crear();

            }
        });

        cancelbutton = (Button) getView().findViewById(R.id.cancel);

        cancelbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cancel();

            }
        });
    }

    public static void llenar(String valor1, String valor2, String valor3){
        campo1.setText(valor1);
        campo2.setText(valor2);
        campo3.setText(valor3);
        creando = false;

    }

    public void crear(){
        if(creando){
            first.crearU(creando,campo1.getText().toString(),campo2.getText().toString(),campo3.getText().toString());
        }
        else{
            first.editarU(creando,campo1.getText().toString(),campo2.getText().toString(),campo3.getText().toString(),idedit);
        }

    }

    public void cancel(){
        campo1.setText("");
        campo2.setText("");
        campo3.setText("");
        creando = true;
    }

    public void fillData(JSONArray response){

    }

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

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
