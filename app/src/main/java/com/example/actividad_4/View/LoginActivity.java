package com.example.actividad_4.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.actividad_4.Model.LoginModel;
import com.example.actividad_4.Presenter.LoginPresenter;
import com.example.actividad_4.R;
import com.example.actividad_4.Tools.Cliente;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity implements Login{
    EditText usuario;
    EditText password;
    Button entrar;

    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new LoginModel(LoginActivity.this);
        setContentView(R.layout.activity_login);
        usuario = (EditText) findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        entrar = (Button)findViewById(R.id.login);
    }
    public void logear(View view){
        presenter.performLogin(usuario.getText().toString(),password.getText().toString());
        System.out.println("JALA");
    }


    @Override
    public void loginSuccess(String username) {
        Intent intent = new Intent(LoginActivity.this,Main2Activity.class);
        intent.putExtra("dato",username);
        startActivity(intent);
    }

    @Override
    public void loginError() {
        System.out.println("MORIDO");
    }
}
