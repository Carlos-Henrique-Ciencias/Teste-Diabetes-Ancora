package com.example.ancora;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import android.widget.TextView;
import android.widget.Toast;

public class FinalActivity extends AppCompatActivity {

    private String nome, cpf, sexo;
    private int totalPontos;  // Alterado de 'double' para 'int'
    private TextView resultadoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        resultadoTextView = findViewById(R.id.resultadoTextView);

        // Recupere os dados das atividades anteriores
        Intent intent = getIntent();
        nome = intent.getStringExtra("nome");
        cpf = intent.getStringExtra("cpf");
        sexo = intent.getStringExtra("sexo");
        totalPontos = intent.getIntExtra("totalPontos", 0);  // Alterado de 'pontuacaoTotal' para 'totalPontos'

        // Exibir resultado do teste com base na pontuação
        avaliarRisco();

        // Salvar dados em um arquivo
        saveToFile();
    }

    private void avaliarRisco() {
        String resultado;

        if (totalPontos < 7) {
            resultado = "Baixo risco de desenvolver diabetes";
        } else if (totalPontos >= 7 && totalPontos <= 11) {
            resultado = "Risco levemente elevado de desenvolver diabetes";
        } else if (totalPontos >= 12 && totalPontos <= 14) {
            resultado = "Risco moderado de desenvolver diabetes";
        } else if (totalPontos >= 15 && totalPontos <= 20) {
            resultado = "Risco alto de desenvolver diabetes";
        } else {
            resultado = "Risco extremamente alto de desenvolver diabetes";
        }

        resultadoTextView.setText(resultado);
    }

    private void saveToFile() {
        String content = "Nome: " + nome + "\nCPF: " + cpf +
                "\nSexo: " + sexo +
                "\nPontuação Total: " + totalPontos;

        try {
            File dir = new File(getFilesDir(), "ancora_registros");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, "info_paciente.txt");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(content.getBytes());
            fos.close();
            Toast.makeText(this, "Informações salvas com sucesso!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Erro ao salvar o arquivo.", Toast.LENGTH_SHORT).show();
        }
    }
}
