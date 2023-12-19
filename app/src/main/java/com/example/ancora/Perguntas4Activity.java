package com.example.ancora;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Perguntas4Activity extends AppCompatActivity {

    private RadioGroup historicoDiabetesRadioGroup;
    private Button buttonProximo;
    private String nome;
    private String cpf;
    private int idade;
    private String sexo;
    private int totalPontos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas4);

        historicoDiabetesRadioGroup = findViewById(R.id.radioGroupHistoricoDiabetes);
        buttonProximo = findViewById(R.id.buttonProximo);

        // Recuperar os dados extras das Activities anteriores
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nome = extras.getString("nome");
            cpf = extras.getString("cpf");
            idade = extras.getInt("idade");
            sexo = extras.getString("sexo");
            totalPontos = extras.getInt("totalPontos");
        }

        buttonProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedId = historicoDiabetesRadioGroup.getCheckedRadioButtonId();

                if (checkedId == -1) {
                    Toast.makeText(Perguntas4Activity.this, "Selecione uma opção", Toast.LENGTH_SHORT).show();
                    return;
                }

                int pontuacaoHistoricoDiabetes = obterPontuacaoHistoricoDiabetes(checkedId);

                // Aqui você soma o ponto da pergunta atual ao total acumulado
                totalPontos += pontuacaoHistoricoDiabetes;

                // Navegar para a próxima página (FinalActivity) e passar os dados extras
                Intent intent = new Intent(Perguntas4Activity.this, FinalActivity.class);
                intent.putExtra("nome", nome);
                intent.putExtra("cpf", cpf);
                intent.putExtra("idade", idade);
                intent.putExtra("sexo", sexo);
                intent.putExtra("totalPontos", totalPontos);
                startActivity(intent);
            }
        });
    }

    private int obterPontuacaoHistoricoDiabetes(int radioButtonId) {
        if (radioButtonId == R.id.radioButtonHistoricoDiabetesNao) {
            return 0;
        } else if (radioButtonId == R.id.radioButtonHistoricoDiabetesSim1) {
            return 3;
        } else if (radioButtonId == R.id.radioButtonHistoricoDiabetesSim2) {
            return 5;
        } else {
            return 0;
        }
    }
}
