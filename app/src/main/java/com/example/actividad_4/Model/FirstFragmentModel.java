package com.example.actividad_4.Model;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.actividad_4.View.Main2Activity;
import com.loopj.android.http.RequestParams;

import com.example.actividad_4.Presenter.FirstFragmentPresenter;
import com.example.actividad_4.R;
import com.example.actividad_4.Tools.Cliente;
import com.example.actividad_4.View.FirstFragment;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.xml.transform.Result;

import cz.msebera.android.httpclient.Header;

public class FirstFragmentModel implements FirstFragmentPresenter {
    FirstFragment firstFragment;

    public FirstFragmentModel(FirstFragment firstFragment){
        this.firstFragment = firstFragment;
    }

    @Override
    public View obtenerDatos(View menu) {

        final View mimenu = menu;

        Cliente.getAllClient(null,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                try {
                    final TableLayout stk = (TableLayout) mimenu.findViewById(R.id.table_main2);
                    TableRow tbrow0 = new TableRow(mimenu.getContext());
                    TextView tv0 = new TextView(mimenu.getContext());
                    tv0.setText("NOMBRE");
                    tv0.setGravity(Gravity.CENTER);
                    tbrow0.addView(tv0);
                    stk.addView(tbrow0);

                    for (int i=0 ;i<response.length();i++){
                        TableRow tbrow = new TableRow(mimenu.getContext());
                        JSONObject jsonObject;
                        jsonObject = response.getJSONObject(i);
                        TextView t1v = new TextView(mimenu.getContext());
                        t1v.setText("" + jsonObject.getString("Nombre"));
                        t1v.setGravity(Gravity.CENTER);
                        tbrow.addView(t1v);


                        final Button btn = new Button(mimenu.getContext());
                        btn.setText("Ver Mas");
                        btn.setTag(jsonObject.getString("id"));
                        btn.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                Cliente.getByidClient(btn.getTag().toString(),null,new JsonHttpResponseHandler(){
                                    @Override
                                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                        try {
                                            AlertDialog.Builder alerta = new AlertDialog.Builder(mimenu.getContext());
                                            alerta.setMessage("Nombre: "+response.getString("Nombre")+"\nApellido: "+response.getString("apellido")+"\nEdad: "+response.getString("edad"));
                                            alerta.setCancelable(false);
                                            alerta.setPositiveButton("cerrar", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    onFinish();
                                                }
                                            });
                                            AlertDialog titulo = alerta.create();
                                            titulo.show();
                                        }catch (Exception e){

                                        }
                                    }
                                });

                            }
                        });

                        final Button btneli = new Button(mimenu.getContext());
                        btneli.setText("Eliminar");
                        btneli.setTag(jsonObject.getString("id"));
                        btneli.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                String id = btneli.getTag().toString();
                                Cliente.deleteClient(id,new JsonHttpResponseHandler(){
                                    @Override
                                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                        AlertDialog.Builder alerta = new AlertDialog.Builder(mimenu.getContext());
                                        alerta.setMessage("Eliminado correctamente");
                                        alerta.setCancelable(false);
                                        alerta.setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                onFinish();
                                            }
                                        });
                                        AlertDialog titulo = alerta.create();
                                        titulo.show();
                                        stk.removeAllViews();
                                    }

                                    @Override
                                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                                    }
                                });

                            }
                        });

                        final Button btnedit = new Button(mimenu.getContext());
                        btnedit.setText("Editar");
                        btnedit.setTag(jsonObject.getString("id"));
                        btnedit.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                Cliente.getByidClient(btn.getTag().toString(),null,new JsonHttpResponseHandler(){
                                    @Override
                                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                                        try {
                                            FirstFragment.creando = false;
                                            FirstFragment.llenar(response.getString("Nombre"),response.getString("apellido"),response.getString("edad"));
                                            FirstFragment.idedit = btn.getTag().toString();
                                        }catch (Exception e){

                                        }
                                    }
                                });

                            }
                        });

                        tbrow.addView(btn);
                        tbrow.addView(btnedit);
                        tbrow.addView(btneli);
                        stk.addView(tbrow);


                    }

                }catch (JSONException e){

                };
            }

        });

        return mimenu;
    }

    @Override
    public void editarU(boolean creando, String nombre, String apellido, String edad,String id) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("id",id);
        requestParams.put("Nombre",nombre);
        requestParams.put("apellido",apellido);
        requestParams.put("edad",Integer.parseInt(edad));
        requestParams.put("sexo","a");
        requestParams.put("direccion","a");
        requestParams.put("carrera","a");

        Cliente.putClient(id,requestParams,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                System.out.println("Actualizado");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }

    @Override
    public void crearU(boolean creando,String nombre, String apellido, String edad) {
        System.out.println("name:"+nombre);

        RequestParams requestParams = new RequestParams();
        requestParams.put("Nombre",nombre);
        requestParams.put("apellido",apellido);
        requestParams.put("edad",Integer.parseInt(edad));
        requestParams.put("sexo","a");
        requestParams.put("direccion","a");
        requestParams.put("carrera","a");

        Cliente.postClient(requestParams,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                System.out.println("Todo bien");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                System.out.println("Todo bien");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                System.out.println("Todo mal");
                System.out.println(errorResponse);
            }
        });


    }

}
