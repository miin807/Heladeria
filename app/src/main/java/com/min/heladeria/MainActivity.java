package com.min.heladeria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class MainActivity extends AppCompatActivity {
private Button botonEnviar;
private EditText cantVainilla, cantChocolate, cantFresa;
private Spinner tipoHelado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        botonEnviar = findViewById(R.id.enviar);
        cantVainilla = findViewById(R.id.numeroVainilla);
        cantChocolate = findViewById(R.id.numeroChocolate);
        cantFresa = findViewById(R.id.numeroFresa);
        tipoHelado = findViewById(R.id.recipiente);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.tipo_recipiente, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoHelado.setAdapter(adapter);

        botonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               enviarDatos();
            }

            });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void enviarDatos() {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);

        // Enviar directamente los valores de los campos de texto y Spinner
        intent.putExtra("vainilla", cantVainilla.getText().toString());
        intent.putExtra("chocolate", cantChocolate.getText().toString());
        intent.putExtra("fresa", cantFresa.getText().toString());
        intent.putExtra("recipiente", tipoHelado.getSelectedItem().toString());

        startActivity(intent);
    }

}