package contrato.com.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import contrato.com.R;
import contrato.com.activities.administrador.Administrador;
import contrato.com.activities.cliente.PainelCliente;
import contrato.com.activities.prestador.PainelPrestador;

public class Login extends AppCompatActivity {

    public int idPrestador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
    }
    public void administrador(View view) {
        startActivity(new Intent(this, Administrador.class));
    }

    public void prestador(View view) {
        idPrestador = 99993;
        Intent intent = new Intent(Login.this, PainelPrestador.class);
        intent.putExtra("ID",idPrestador);
        startActivity(intent);
    }
    public void cliente(View view) {
        startActivity(new Intent(this, PainelCliente.class));
    }
}
