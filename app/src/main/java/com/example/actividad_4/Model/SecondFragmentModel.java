package com.example.actividad_4.Model;

import android.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.actividad_4.Presenter.SecondFragmentPresenter;
import com.example.actividad_4.R;
import com.example.actividad_4.Tools.Cliente;
import com.example.actividad_4.View.SecondFragment;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class SecondFragmentModel implements SecondFragmentPresenter {

    public SecondFragment second;

    public SecondFragmentModel(SecondFragment second){
        this.second = second;
    }
    @Override
    public View obtenerDatos(View menu) {

        final View mimenu2 = menu;

        Cliente.getAllClient(null,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                try {
                    final TableLayout stk = (TableLayout) mimenu2.findViewById(R.id.table_main2);
                    TableRow tbrow0 = new TableRow(mimenu2.getContext());
                    TextView tv0 = new TextView(mimenu2.getContext());
                    tv0.setText("NOMBRE");
                    tv0.setGravity(Gravity.CENTER);
                    tbrow0.addView(tv0);
                    stk.addView(tbrow0);


                    for (int i=0 ;i<response.length();i++){
                        TableRow tbrow = new TableRow(mimenu2.getContext());
                        JSONObject jsonObject;
                        jsonObject = response.getJSONObject(i);
                        TextView t1v = new TextView(mimenu2.getContext());
                        t1v.setText("" + jsonObject.getString("Nombre"));
                        t1v.setGravity(Gravity.CENTER);
                        tbrow.addView(t1v);


                        final Button btn = new Button(mimenu2.getContext());
                        btn.setText("ver Mas");
                        btn.setTag(jsonObject.getString("id"));
                        btn.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                Cliente.getByidClient(btn.getTag().toString(),null,new JsonHttpResponseHandler(){
                                    @Override
                                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                        try {

                                            AlertDialog.Builder alerta = new AlertDialog.Builder(mimenu2.getContext());
                                            alerta.setMessage("Nombre: "+response.getString("Nombre")+"\nApellido: "+response.getString("apellido")+"\nEdad "+response.getString("edad")+"\nSexo: "+response.getString("sexo")+"\nCarrera: "+response.getString("carrera"));
                                            alerta.setCancelable(false);
                                            alerta.setPositiveButton("cerrar",null);
                                            AlertDialog titulo = alerta.create();
                                            titulo.show();
                                        }catch (Exception e){

                                        }
                                    }
                                });

                            }
                        });
                        tbrow.addView(btn);
                        stk.addView(tbrow);


                    }

                }catch (JSONException e){

                };
            }


        });
        return mimenu2;
    }
}
