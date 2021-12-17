package com.asier.aranda.examen1evaluacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> divisas;
    private ArrayList<Double> factoresCambio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializaDivisas();
        inicializaFactoresCambio();

        RecyclerView rvDivisas = findViewById(R.id.rvDivisas);
        LinearLayoutManager managerLayout = new LinearLayoutManager(this);
        rvDivisas.setLayoutManager(managerLayout);
        MiAdaptador adaptador = new MiAdaptador(this, divisas);//this=activity_main
        rvDivisas.setAdapter(adaptador);


        DividerItemDecoration decorador = new DividerItemDecoration(
                rvDivisas.getContext(), managerLayout.getOrientation());
        rvDivisas.addItemDecoration(decorador);


        Switch swVip = (Switch) findViewById(R.id.swVIP);
        swVip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //llamo al metodo que hace el cambio de divisa
                actualizarCambio(swVip.isChecked(),adaptador.getElemetoSeleccionado());
            }
        });



        Button btConvertir = (Button) findViewById(R.id.btConvertir);
        btConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //llamar a un metodo que hace el cambio de divisa
                actualizarCambio(swVip.isChecked(),adaptador.getElemetoSeleccionado());
            }
        });



    }
    public void actualizarCambio(boolean VIP,int cambioSeleccionado){
        TextView tvResultado = findViewById(R.id.tvResultado);
        if(cambioSeleccionado!=-1){
            try{
                EditText etCantidadInicial=(EditText) findViewById(R.id.etCantidadInicial);
                //guardar esa cantidad en un double
                double euros=Double.parseDouble(etCantidadInicial.getText().toString());
                //uso operador Ternaria tiene -->    expresion ? valor1:valor2    --> si expresion = true devuelve valor1 sino es true valor2
                //si es vip x1 y si no es vip x1.01
                double cambioDivisa=VIP?factoresCambio.get(cambioSeleccionado)*1.0:factoresCambio.get(cambioSeleccionado)*1.01;
                tvResultado.setText(""+cambioDivisa*euros);

            }catch(NumberFormatException e){
                tvResultado.setText("");
            }
        }else{
            tvResultado.setText("");
        }

    }

    private void inicializaFactoresCambio() {
        factoresCambio = new ArrayList<>();
        factoresCambio.add(1.1293946);
        factoresCambio.add(0.85447758);
        factoresCambio.add(1.4339265);
        factoresCambio.add(1.5788175);
        factoresCambio.add(128.17384);
        factoresCambio.add(85.36992);
        factoresCambio.add(1.6631981);
        factoresCambio.add(1.0441295);
        factoresCambio.add(18.030472);
        factoresCambio.add(83.219626);
    }

    private void inicializaDivisas() {
        divisas = new ArrayList<>();
        divisas.add("USD");
        divisas.add("GBP");
        divisas.add("CAD");
        divisas.add("AUD");
        divisas.add("JPY");
        divisas.add("INR");
        divisas.add("NZD");
        divisas.add("CHF");
        divisas.add("ZAR");
        divisas.add("RUB");
    }
}