package com.example.ancora;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Perguntas3Activity extends AppCompatActivity {


    private RadioGroup circunferenciaCinturaRadioGroup;
    private RadioGroup atividadeFisicaRadioGroup;
    private RadioGroup consumoAlimentarRadioGroup;
    private Button proximoButton;

    private String nome;
    private String cpf;
    private int idade;
    private String sexo;
    private int totalPontos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas3);

        circunferenciaCinturaRadioGroup = findViewById(R.id.radioGroupCircunferenciaCintura);
        atividadeFisicaRadioGroup = findViewById(R.id.radioGroupAtividadeFisica);
        consumoAlimentarRadioGroup = findViewById(R.id.radioGroupConsumoAlimentar);
        proximoButton = findViewById(R.id.buttonProximo);

        // Recuperar os dados extras das Activities anteriores
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nome = extras.getString("nome");
            cpf = extras.getString("cpf");
            idade = extras.getInt("idade");
            sexo = extras.getString("sexo");
            totalPontos = extras.getInt("totalPontos");
        }

        proximoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pontuacao = calcularPontuacao();

                Intent intent = new Intent(Perguntas3Activity.this, Perguntas4Activity.class);
                intent.putExtra("nome", nome);
                intent.putExtra("cpf", cpf);
                intent.putExtra("idade", idade);
                intent.putExtra("sexo", sexo);
                intent.putExtra("totalPontos", totalPontos + pontuacao);  // Adicionando diretamente aqui
                startActivity(intent);
            }
        });
    }


    private int calcularPontuacao() {
        int pontuacao = 0;

        int selectedCircunferenciaCinturaId = circunferenciaCinturaRadioGroup.getCheckedRadioButtonId();
        int selectedAtividadeFisicaId = atividadeFisicaRadioGroup.getCheckedRadioButtonId();
        int selectedConsumoAlimentarId = consumoAlimentarRadioGroup.getCheckedRadioButtonId();

        if (selectedCircunferenciaCinturaId != -1) {
            RadioButton selectedCircunferenciaCinturaButton = findViewById(selectedCircunferenciaCinturaId);
            int circunferenciaCinturaPontuacao = obterPontuacaoCircunferenciaCintura(selectedCircunferenciaCinturaButton);
            pontuacao += circunferenciaCinturaPontuacao;
        }

        if (selectedAtividadeFisicaId != -1) {
            RadioButton selectedAtividadeFisicaButton = findViewById(selectedAtividadeFisicaId);
            int atividadeFisicaPontuacao = obterPontuacaoAtividadeFisica(selectedAtividadeFisicaButton);
            pontuacao += atividadeFisicaPontuacao;
        }

        if (selectedConsumoAlimentarId != -1) {
            RadioButton selectedConsumoAlimentarButton = findViewById(selectedConsumoAlimentarId);
            int consumoAlimentarPontuacao = obterPontuacaoConsumoAlimentar(selectedConsumoAlimentarButton);
            pontuacao += consumoAlimentarPontuacao;
        }

        return pontuacao;
    }

    private int obterPontuacaoCircunferenciaCintura(RadioButton radioButton) {
        int radioButtonId = radioButton.getId();
        if (radioButtonId == R.id.radioButtonCircunferenciaCintura1) {
            return 0;
        } else if (radioButtonId == R.id.radioButtonCircunferenciaCintura2) {
            return 3;
        } else if (radioButtonId == R.id.radioButtonCircunferenciaCintura3) {
            return 4;
        } else {
            return 0;
        }
    }

    private int obterPontuacaoAtividadeFisica(RadioButton radioButton) {
        int radioButtonId = radioButton.getId();
        if (radioButtonId == R.id.radioButtonAtividadeFisica1) {
            return 0;
        } else if (radioButtonId == R.id.radioButtonAtividadeFisica2) {
            return 2;
        } else {
            return 0;
        }
    }

    private int obterPontuacaoConsumoAlimentar(RadioButton radioButton) {
        int radioButtonId = radioButton.getId();
        if (radioButtonId == R.id.radioButtonConsumoAlimentar1) {
            return 0;
        } else if (radioButtonId == R.id.radioButtonConsumoAlimentar2) {
            return 1;
        } else {
            return 0;
        }
    }
}
