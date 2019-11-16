package contrato.com.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import contrato.com.R;
import contrato.com.activities.administrador.Administrador;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
    }
    public void administrador(View view) {
        startActivity(new Intent(this, Administrador.class));
    }
    public void cadastro(View view) {
        startActivity(new Intent(this, Cadastro.class));
    }
}
