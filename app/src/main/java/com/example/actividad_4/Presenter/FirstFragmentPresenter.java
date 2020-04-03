package com.example.actividad_4.Presenter;


import android.view.View;


public interface FirstFragmentPresenter {
    View obtenerDatos(View menu);
    void editarU(boolean creando,String nombre, String apellido, String edad, String id);
    void crearU(boolean creando,String nombre, String apellido, String edad);

}
