package com.example.ancora;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNome, editTextCPF, editTextDataNascimento;
    private RadioGroup radioGroupSexo;
    private Button buttonComecar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNome = findViewById(R.id.editTextNome);
        editTextCPF = findViewById(R.id.editTextCPF);
        editTextDataNascimento = findViewById(R.id.editTextDataNascimento);
        radioGroupSexo = findViewById(R.id.radioGroupSexo);
        buttonComecar = findViewById(R.id.buttonComecar);

        buttonComecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = editTextNome.getText().toString();
                String cpf = editTextCPF.getText().toString();
                String dataNascimento = editTextDataNascimento.getText().toString();

                if (TextUtils.isEmpty(nome) || TextUtils.isEmpty(cpf) || TextUtils.isEmpty(dataNascimento) || radioGroupSexo.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(MainActivity.this, "Por favor, preencha todos os campos!", Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioButton radioButtonSexo = findViewById(radioGroupSexo.getCheckedRadioButtonId());
                String sexo = radioButtonSexo.getText().toString();

                Intent intent = new Intent(MainActivity.this, Perguntas2Activity.class);
                intent.putExtra("NOME", nome);
                intent.putExtra("CPF", cpf);
                intent.putExtra("DATA_NASCIMENTO", dataNascimento);
                intent.putExtra("SEXO", sexo);
                startActivity(intent);
            }
        });
    }
}
