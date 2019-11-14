package contrato.com.activities.administrador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

import contrato.com.R;

public class Administrador extends AppCompatActivity {

    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);

       }

    public void tpPrestador(View view) {
        Intent intent = new Intent(Administrador.this, TTipoPrestador.class);
        intent.putExtra("info","This is activity from card item index  ");
        startActivity(intent);

    }


}
