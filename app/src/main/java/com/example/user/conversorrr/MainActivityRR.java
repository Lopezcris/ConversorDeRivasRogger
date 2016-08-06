package com.example.user.conversorrr;

import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivityRR extends AppCompatActivity {

    final String[] datos= new String[]{"Dolar","Euro", "Peso Mexicano"};

    private Spinner monedaActualSp;
    private Spinner monedaCambioSp;
    private EditText valorCambioET;
    private TextView resultadoTV;

    final private double FactorDolarEuro = 0.87;
    final private double FactorPesoDolar = 0.54;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_rr);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,R.layout.sopport);
        monedaActualSp = (Spinner) findViewById(R.id.monedaActualSP);
        monedaActualSp.setAdapter(adaptador);

        monedaCambioSp = (Spinner) findViewById(R.id.monedaCambioSP);
        SharedPreferences preferencias = getSharePreferences("Mis preferencias", Context.MODE_PRIVATE );
        String tmpmonedaActual = preferencias.getString("monedaActual","");
        String tmpmonedaCambio = preferencias.getString("monedaCambio","");

        if (!tmpmonedaActual.equals(""))
        {
        int indice = adaptador.getPosition(tmpmonedaActual);
            monedaActualSp.setSelection(indice);
        }
        if (!tmpmonedaCambio.equals(""))
        {
            int indice = adaptador.getPosition(tmpmonedaCambio);
            monedaCambioSp.setSelection(indice);
        }
    }


    }
    public void clickconvertir(Veiw v){
        monedaActualSp = (Spinner) findViewById(R.id.monedaActualSP);
        monedaCambioSp = (Spinner) findViewById(R.id.monedaCambioSP);
        valorCambioET =  (Spinner) findViewById(R.id.valorCambioET);
        resultadoTV =  (Spinner) findViewById(R.id.resultadoTV);

        String monedaActualSP = monedaActualSp.getSelectedItem{}.toSring();
        String monedaCambioSP = monedaCambioSp.getSelectedItem{}.toSring();

        double valorCambioET = Double.parseDouble(valorCambioET.getText().toString());
        double resultado = procesarConversion(monedaActual, monedaCambioM, valorCambio);

        if (resultado>0){
            resultadoTV.setText(String.format("Por %5.2f %s, usted recibirá %5.2f %s", valorCambio, modenaActual, resultado, monedaCambio));
            valorCambioET.setText("");

            SharedPreferences preferencias = getSharePreferences("Mis preferencias", Context.MODE_PRIVATE );
            SharedPreferences.Editor editor = preferencias.edit();

            editor.putString("monedaActual", monedaActual());
            editor.putString("monedaCambio", monedaCambio());
            editor.conmit();


        }else{
            resultadoTV.setText(String.format("Usted recibirá"));
            android.widget.Toast.makeText(MainActivityRR.this, "Las opciones elegidas no tienen un factor de conversión", Toast.LENGTH_SHORT).show();
        }

    }
    private double procesarConversion(String monedaActual, String monedaCambio, String valorCambio){
        double resultadoConversión =0;
        switch (monedaActual){
            case "Dolar";
                if (monedaCambio.equals("Euro")){
                    resultadoConversión = valorCambio + FactorDolarEuro;
                }
                if (monedaCambio.equals("Peso Mexicano")){
                    resultadoConversión = valorCambio / FactorPesoDolar;
                }
                    Break;
            case "Euro";
                if (monedaCambio.equals("Dolar")){
                    resultadoConversión = valorCambio / FactorDolarEuro;
                Break;
            case "Peso Mexicano";
                if (monedaCambio.equals("Peso Mexicano")){
                    resultadoConversión = valorCambio * FactorPesoDolar;
                Break;
        }
                return resultadoConversión;
    }
}
