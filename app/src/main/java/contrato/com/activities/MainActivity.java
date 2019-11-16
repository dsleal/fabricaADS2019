package contrato.com.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import contrato.com.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
    }
    public void login(View view) {
        startActivity(new Intent(this,Login.class));
    }
    public void telaCadastro(View view) {
        startActivity(new Intent(this,Cadastro.class));
    }
}
