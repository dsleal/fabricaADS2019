package contrato.com.activities.cliente;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.sql.Date;
import java.util.InputMismatchException;

import contrato.com.R;
import contrato.com.activities.Login;
import contrato.com.boostrap.APIClient;
import contrato.com.model.Cliente;
import contrato.com.model.Endereco;
import contrato.com.resource.ClienteResource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CadastroCliente extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtCpf_Cnpj;
    private EditText edtEmail;
    private EditText edtIdentidade;
    private EditText edtLogradouro;
    private EditText edtCEP;
    private EditText edtCidade;
    private EditText edtEstado;
    private EditText edtTelefone;
    private EditText edtSenha;
    private EditText edtConfirmarSenha;
    private RadioButton rbCPF;
    private RadioButton rbCNPJ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        inserirMascaras();
    }


    public void limparCli(View view) {
        setCampos();

        edtNome.setText(" ");
        edtCpf_Cnpj.setText(" ");
        edtEmail.setText(" ");
        edtIdentidade.setText(" ");
        edtLogradouro.setText(" ");
        edtCEP.setText(" ");
        edtCidade.setText(" ");
        edtEstado.setText(" ");
        edtSenha.setText(" ");
        edtConfirmarSenha.setText(" ");
    }

    public void adicionarCli(View view) {
        Cliente cliente = getDadosTela();
        if (!validarDados()) {
            return;
        }
        ;
        Retrofit retrofit = APIClient.getClient();
        ClienteResource clienteResource = retrofit.create(ClienteResource.class);
        Call<Cliente> post = clienteResource.post(cliente);
        post.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                if (response.isSuccessful()) {
                    Cliente cli = response.body();
                    Toast.makeText(CadastroCliente.this, "Cliente  " + cli.getNome() + " cadastrado!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(CadastroCliente.this, Login.class));
                } else {
                    switch (response.code()) {
                        case 404:
                            Toast.makeText(CadastroCliente.this, "404 - not found", Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            Toast.makeText(CadastroCliente.this, "500 - internal server error", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(CadastroCliente.this, "unknown error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                Toast.makeText(CadastroCliente.this, "Favor verificar sua conexão.", Toast.LENGTH_SHORT).show();
                Log.e(this.getClass().getName(), "onFailure: " + t.getMessage());
            }
        });
    }


    private Cliente getDadosTela() {

        setCampos();

        Endereco endereco = new Endereco();
        Cliente cli = new Cliente();

        /*
        endereco.setLogradouro(edtLogradouro.getText().toString());
        endereco.setCep(edtCEP.getText().toString());
        endereco.setCidade(edtCidade.getText().toString());
        endereco.setEstado(edtEstado.getText().toString());
        */
        endereco.setId(99991);

        cli.setEndereco(endereco);
        cli.setNome(edtNome.getText().toString());
        cli.setCpfCnpj(edtCpf_Cnpj.getText().toString());
        cli.setEmail(edtEmail.getText().toString());
        cli.setDtNascimento(new Date(System.currentTimeMillis()));
        cli.setIdentidade(edtIdentidade.getText().toString());
        //cli.setTe(edtTelefone.getText().toString());
        cli.setDtCadastro(new Date(System.currentTimeMillis()));
        return cli;
    }

    private void setCampos() {
        edtNome = findViewById(R.id.edtNome);
        edtCpf_Cnpj = findViewById(R.id.edtCpf_Cpnpj);
        edtEmail = findViewById(R.id.edtEmail);
        edtIdentidade = findViewById(R.id.edtIdentidade);
        edtLogradouro = findViewById(R.id.edtLogradouro);
        edtCEP = findViewById(R.id.edtCEP);
        edtCidade = findViewById(R.id.edtCidade);
        edtEstado = findViewById(R.id.edtEstado);
        edtTelefone = findViewById(R.id.edtTelefone);
        edtSenha = findViewById(R.id.edtSenha);
        edtConfirmarSenha = findViewById(R.id.edtConfirmarSenha);
        rbCNPJ = findViewById(R.id.rbCNPJ);
        rbCPF = findViewById(R.id.rbCPF);

    }


    private boolean validarDados() {
        //campos não preenchidos
        if (edtNome.length() == 0 || edtCpf_Cnpj.length() == 0 || edtEmail.length() == 0 || edtIdentidade.length() == 0 || edtLogradouro.length() == 0 ||
                edtCEP.length() == 0 || edtCidade.length() == 0 || edtEstado.length() == 0 ||
                edtTelefone.length() == 0 || edtSenha.length() == 0 || edtConfirmarSenha.length() == 0) {
            Toast.makeText(CadastroCliente.this, "Preencha todos os campos!", Toast.LENGTH_LONG).show();
            return false;
        }

        //senha e confirmar senha iguais
        if (!edtSenha.getText().toString().equals(edtConfirmarSenha.getText().toString())) {
            Toast.makeText(CadastroCliente.this, "Informe senhas iguais!", Toast.LENGTH_LONG).show();
            return false;
        }

        //tamanho cpf / cnpj
        if (edtCpf_Cnpj.length() != 11 & edtCpf_Cnpj.length() != 14) {
            Toast.makeText(CadastroCliente.this, "Digite um cpf ou cnpj!", Toast.LENGTH_LONG).show();
            return false;
        }

        //cpf valido
        if (!isCPF(edtCpf_Cnpj.getText().toString())) {
            Toast.makeText(CadastroCliente.this, "CPF Invalido!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }


    private boolean isCPF(String CPF) {
        if (CPF.equals("00000000000") ||
                CPF.equals("11111111111") ||
                CPF.equals("22222222222") ||
                CPF.equals("44444444444") ||
                CPF.equals("55555555555") ||
                CPF.equals("66666666666") ||
                CPF.equals("77777777777") ||
                CPF.equals("88888888888") ||
                CPF.equals("99999999999") ||
                (CPF.length() != 11)) {
            return (false);
        }
        char dig10, dig11;
        int sm, i, r, num, peso;
        try {
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48);
            }

            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
                return true;
            } else {
                return false;
            }

        } catch (InputMismatchException erro) {
            return false;
        }

    }

    private void inserirMascaras(){
        setCampos();
        /*
        //telefone
        SimpleMaskFormatter tel = new SimpleMaskFormatter("(NN)NNNNN-NNNN");
        MaskTextWatcher mtw = new MaskTextWatcher(edtTelefone, tel);
        edtTelefone.addTextChangedListener(mtw);
         */
        //CEP
        SimpleMaskFormatter cep = new SimpleMaskFormatter("NNNNN-NNN");
        MaskTextWatcher mtw = new MaskTextWatcher(edtCEP, cep);
        edtCEP.addTextChangedListener(mtw);

        //CPF / CNPJ
        SimpleMaskFormatter cpfMask = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher cpf = new MaskTextWatcher(edtCpf_Cnpj, cpfMask);
        edtCpf_Cnpj.addTextChangedListener(cpf);

        SimpleMaskFormatter cnpjMask = new SimpleMaskFormatter("NN.NNN.NNN/NNNN-NN");
        MaskTextWatcher cnpj = new MaskTextWatcher(edtCpf_Cnpj, cnpjMask);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioGroup.getCheckedRadioButtonId() == rbCPF.getId()) {
                    edtCpf_Cnpj.setText(" ");
                    edtCpf_Cnpj.removeTextChangedListener(cnpj);
                    edtCpf_Cnpj.addTextChangedListener(cpf);

                } else {
                    edtCpf_Cnpj.setText(" ");
                    edtCpf_Cnpj.removeTextChangedListener(cpf);
                    edtCpf_Cnpj.addTextChangedListener(cnpj);
                }
            }
        });
    }
}