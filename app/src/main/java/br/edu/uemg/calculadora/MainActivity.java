package br.edu.uemg.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    int i = 0;

    int count = 0;
    int[] Result;

    int total;

    static int INVALID = 9999999;

    String operador;

    //Variáveis da Tela
    TextView RESULTSCREEN;
    Button btn00, btn01, btn02, btn03, btn04, btn05, btn06, btn07, btn08, btn09;
    Button btnSoma, btnSub, btnMult, btnDiv, btnIgual, btnLimpar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Result = new int[2]; //iniciando o array

        //LINK COM OS COMPONENTES DA TELA
        // Lançando na variável local JAVA o componente de tela do XML
        RESULTSCREEN = (TextView) findViewById(R.id.RESULTSCREEN);
        btn00 = (Button) findViewById(R.id.button0);
        btn01 = (Button) findViewById(R.id.button1);
        btn02 = (Button) findViewById(R.id.button2);
        btn03 = (Button) findViewById(R.id.button3);
        btn04 = (Button) findViewById(R.id.button4);
        btn05 = (Button) findViewById(R.id.button5);
        btn06 = (Button) findViewById(R.id.button6);
        btn07 = (Button) findViewById(R.id.button7);
        btn08 = (Button) findViewById(R.id.button8);
        btn09 = (Button) findViewById(R.id.button9);

        btnSoma   = (Button) findViewById(R.id.buttonSoma);
        btnSub    = (Button) findViewById(R.id.buttonSub);
        btnMult   = (Button) findViewById(R.id.buttonMult);
        btnDiv    = (Button) findViewById(R.id.buttonDiv);
        btnLimpar = (Button) findViewById(R.id.buttonLimpar);
        btnIgual  = (Button) findViewById(R.id.buttonIgual);

        //botões numéricos
        btn00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarDigito(0);
            }
        });
        btn01.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                adicionarDigito(1);
            }
        });
        btn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarDigito(2);
            }
        });
        btn03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarDigito(3);
            }
        });
        btn04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarDigito(4);
            }
        });
        btn05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarDigito(5);
            }
        });
        btn06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarDigito(6);
            }
        });
        btn07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarDigito(7);
            }
        });
        btn08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarDigito(8);
            }
        });
        btn09.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarDigito(9);
            }
        });

        //botões de operação
        btnSoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operador = "soma";
                proximoNumero();
            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operador = "sub";
                proximoNumero();
            }
        });
        btnMult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operador = "mult";
                proximoNumero();
            }
        });
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operador = "div";
                proximoNumero();
            }
        });

        btnIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcular();
                exibirResultado();
                total = 0;
                count = 0;
            }
        });
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpar();
            }
        });

        limpar();

    }

    //Adiciona o dígito pressionado ao número atual
    private void adicionarDigito(int digito){
        if(count < 7) {
            Result[i] = (Result[i] * 10) + digito;
            if(Result[i] != 0) {
                count++;
            }
        }
        exibirResultado(); //troca o resultado na tela
        total = 0; //zera o valor total para não dar erro
    }

    //Troca o resultado da tela
    private void exibirResultado(){
        if(total != 0 && total < INVALID){
            //se o valor total for válido
            String tela = String.valueOf(total);//String.valueOf é um conversor de inteiro para string
            RESULTSCREEN.setText(tela);
        }else if (total >= INVALID){
            //se o valor total for inválido
            String tela = "ERROR";
            RESULTSCREEN.setText(tela);
        }else{
            //se ainda não foi calculado o resultado
            String tela = String.valueOf(Result[i]);
            RESULTSCREEN.setText(tela);
        }
    }

    //Passa para a próxima posição do vetor
    private void proximoNumero(){
        count = 0; //zera a qte de número
        i = 1;     //passa para o primeiro número do vetor
    }

    //Limpar a tela
    private void limpar(){
        i = 0;
        Result[0] = 0;
        Result[1] = 0;
        total = 0;
        count = 0;
        operador = null;
        exibirResultado();
    }

    //Realiza o cálculo de acordo com a operação selecionada
    private void calcular(){
        if(operador == null){
            return;
        }
        double value = 0;
        switch (operador){
            case "soma":
                value = (double) Result[0] + Result[1];
                break;
            case "sub":
                value = (double) Result[0] - Result[1];
                break;
            case "mult":
                value = (double) Result[0] * Result[1];
                break;
            case "div":
                if(Result[1] != 0){
                    value = (double) Result[0] / Result[1];
                }else{
                    value = INVALID + 1; //evita divisão por zero exibindo ERROR
                }
                break;
        }
        if(value >= INVALID || value <= -INVALID){
            total = INVALID + 1;
        }else{
            total = (int) value;
            Result[0] = total;
            Result[1] = 0;
            i = 1;
        }
    }









}