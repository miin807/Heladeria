package com.min.heladeria;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    private Button btnFinalizar;
    private TextView resultadoHelado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        resultadoHelado = findViewById(R.id.resultadoHelado);
        btnFinalizar = findViewById(R.id.btnFinalizar);

        Intent intent = getIntent();
        int vainilla = parseToInt(intent.getStringExtra("vainilla"));
        int chocolate = parseToInt(intent.getStringExtra("chocolate"));
        int fresa = parseToInt(intent.getStringExtra("fresa"));
        String recipiente = intent.getStringExtra("recipiente");

        String heladoVainilla = generarBolas(vainilla, "O", "#FFFF00");
        String heladoChocolate = generarBolas(chocolate, "O", "#8B4513");
        String heladoFresa = generarBolas(fresa, "O", "#FF69B4");


        String representacionRecipiente = "";
        if ("cucurucho".equals(recipiente)) {
            representacionRecipiente = colorearTexto("V", "#D2B48C");
        } else if ("cucurucho choco".equals(recipiente)) {
            representacionRecipiente = colorearTexto("V", "#8B4513");
        } else if ("tarrina".equals(recipiente)) {
            representacionRecipiente = "U"; //
        }

        String representacionCompleta = heladoVainilla + heladoChocolate + heladoFresa + "\n" + representacionRecipiente;

        resultadoHelado.setText(android.text.Html.fromHtml(representacionCompleta, android.text.Html.FROM_HTML_MODE_LEGACY));

        btnFinalizar.setOnClickListener(v -> finish());

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main2), (v, insets) -> {
                    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                    return insets;
                }
        );
    }
    private String generarBolas(int cantidad, String simbolo, String color) {
        StringBuilder bolas = new StringBuilder();
        for (int i = 0; i < cantidad; i++) {
            bolas.append(colorearTexto(simbolo, color)).append("<br>"); // Cada bola en una nueva línea
        }
        return bolas.toString();
    }

    private String colorearTexto(String texto, String color) {
        return "<font color='" + color + "'>" + texto + "</font>";
    }


    private int parseToInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
