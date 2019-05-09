package huayllani.cripto.comint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewArancel extends AppCompatActivity {
    DataBase db = new DataBase();
    TextView descripcion, sidunea, ga, nota, capitulo, documento, partida;

    ArrayList<String[]> data = new ArrayList<String[]>();
    ArrayList<String[]> data_capitulo = new ArrayList<String[]>();
    ArrayList<String[]> data_document= new ArrayList<String[]>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_arancel);
        String id = getIntent().getStringExtra("id");

        ImageButton atras = (ImageButton) findViewById(R.id.btn_atras);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent search = new Intent(ViewArancel.this, search.class);
                startActivity(search);
            }
        });
        ImageButton house= (ImageButton) findViewById(R.id.btn_house);
        house.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menu = new Intent(ViewArancel.this, menu.class);
                startActivity(menu);
            }
        });

        data = db.getArancelById(id);
        data_capitulo = db.getCapituloById(data.get(0)[14]);
        data_document = db.getDocumentById(data.get(0)[15]);

        descripcion = (TextView) findViewById(R.id.txt_descripcion);
        sidunea= (TextView) findViewById(R.id.txt_sidunea);
        ga = (TextView) findViewById(R.id.txt_unidad);
        nota = (TextView) findViewById(R.id.txt_nota);
        capitulo = (TextView) findViewById(R.id.txt_capitulo);
        documento = (TextView) findViewById(R.id.txt_document);
        partida = (TextView) findViewById(R.id.txt_partida);

        nota.setText("Vacio");
        capitulo.setText("Vacio");
        documento.setText("Vacio");


        descripcion.setText(data.get(0)[3] == "" ? "Vacio" : data.get(0)[3]);
        sidunea.setText(data.get(0)[2] == "" ? "Vacio" : data.get(0)[2]);
        ga.setText(data.get(0)[4] == "" ? "Vacio" : data.get(0)[4]);
        partida.setText(data.get(0)[1] == "" ? "Vacio" : data.get(0)[1]);

        if (!data_capitulo.isEmpty()) {
            capitulo.setText(data_capitulo.get(0)[1] == "" ? "Vacio" : data_capitulo.get(0)[1]);
            nota.setText(data_capitulo.get(0)[2] == "" ? "Vacio" : data_capitulo.get(0)[2]);
        }

        if (!data_document.isEmpty()){
            String aux = data_document.get(0)[1] + " " + data_document.get(0)[2] + " " +data_document.get(0)[3];
            documento.setText(aux == "" ? "Vacio" : aux);
        }
    }
}
