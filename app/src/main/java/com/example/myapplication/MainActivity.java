package com.example.myapplication;
//desenvolvido por @bittner

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText  etCidade ;
    TextView tvResultado;

    //API 'http://api.openweathermap.org/data/2.5/weather?q=' + city + '&appid=' + api_key

    //chave da api nova = ae62bd1776aa45e103463f7a139e0556
   //AQUI

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etCidade = findViewById(R.id.etCidade);
        tvResultado = findViewById(R.id.tvResultado);
    }
    //aqui quando clica no botão buscar temperatura ele busca os dados dentro da api.
    public void get(View view){
        String apikey = "ae62bd1776aa45e103463f7a139e0556";
        String cidade = etCidade.getText().toString();
        String url = "https://api.openweathermap.org/data/2.5/weather?q="+cidade+"&appid=ae62bd1776aa45e103463f7a139e0556";
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            //aqui volta com os dados impressos na tela o resultado da api.
            public void onResponse(JSONObject response) {
                try {
                    JSONObject object = response.getJSONObject("main");
                    String temperature = object.getString("temp");
                    Double temp = Double.parseDouble(temperature )-273.15;
                    tvResultado.setText("Temperatura = "+ temp.toString().substring(0,4)+ "°C");
                } catch (JSONException e) {
                 Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
      //aqui quando digitam o nome da cidade errado ele aparece erro.
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(MainActivity.this,"DIGITE UMA CIDADE VÁLIDA !!!".toString(),Toast.LENGTH_SHORT).show();
            }
        });
       queue.add(request);
    }
    //aqui faz a saída do app pelo botão e finaliza.
    public void  gerarSair(View view){
        finish();
    }
}