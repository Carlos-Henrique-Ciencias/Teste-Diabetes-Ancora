package com.example.ancora;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Perguntas2Activity extends AppCompatActivity {

    private RadioGroup idadeRadioGroup;
    private EditText pesoEditText;
    private EditText alturaEditText;
    private Button calcularIMCButton;
    private TextView resultadoIMCTextView;
    private Button proximoButton;

    private String nome;
    private String cpf;
    private String dataNascimento;
    private String sexo;
    private int totalPontos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas2);

        idadeRadioGroup = findViewById(R.id.radioGroupIdade);
        pesoEditText = findViewById(R.id.editTextPeso);
        alturaEditText = findViewById(R.id.editTextAltura);
        calcularIMCButton = findViewById(R.id.buttonCalcularIMC);
        resultadoIMCTextView = findViewById(R.id.textViewResultadoIMC);
        proximoButton = findViewById(R.id.buttonProximo);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nome = extras.getString("NOME");
            cpf = extras.getString("CPF");
            dataNascimento = extras.getString("DATA_NASCIMENTO");
            sexo = extras.getString("SEXO");
            totalPontos = extras.getInt("totalPontos", 0);
        }

        calcularIMCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = idadeRadioGroup.getCheckedRadioButtonId();

                String pesoString = pesoEditText.getText().toString().replace(",", ".");
                String alturaString = alturaEditText.getText().toString().replace(",", ".");

                if (pesoString.isEmpty() || alturaString.isEmpty() || selectedId == -1) {
                    Toast.makeText(Perguntas2Activity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                float peso = Float.parseFloat(pesoString);
                float altura = Float.parseFloat(alturaString);
                float imc = calcularIMC(peso, altura);

                resultadoIMCTextView.setText(String.format("IMC: %.2f", imc));

                int idadePontos = obterPontuacaoIdade(selectedId);
                int imcPontos = obterPontuacaoIMC(imc);

                totalPontos += idadePontos + imcPontos;  // Acumulando os pontos
            }
        });

        proximoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perguntas2Activity.this, Perguntas3Activity.class);
                intent.putExtra("NOME", nome);
                intent.putExtra("CPF", cpf);
                intent.putExtra("DATA_NASCIMENTO", dataNascimento);
                intent.putExtra("SEXO", sexo);
                intent.putExtra("totalPontos", totalPontos);
                startActivity(intent);
            }
        });
    }

    private int obterPontuacaoIdade(int selectedId) {
        if (selectedId == R.id.radioButtonAbaixo45) {
            return 0;
        } else if (selectedId == R.id.radioButtonEntre45e59) {
            return 2;
        } else if (selectedId == R.id.radioButtonEntre60e74) {
            return 3;
        } else if (selectedId == R.id.radioButtonAcima75) {
            return 4;
        } else {
            throw new IllegalArgumentException("ID de RadioButton inesperado: " + selectedId);
        }
    }

    private float calcularIMC(float peso, float altura) {
        return peso / (altura * altura);
    }

    private int obterPontuacaoIMC(float imc) {
        if (imc < 25) {
            return 0;
        } else if (imc < 30) {
            return 1;
        } else {
            return 3;
        }
    }
}
