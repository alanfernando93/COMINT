package huayllani.cripto.comint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

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

        data = db.getArancelById(id);
        data_capitulo = db.getCapituloById(data.get(0)[14]);
        data_document = db.getDocumentById(data.get(0)[15]);

        descripcion = (TextView) findViewById(R.id.txt_descripcion);
        sidunea= (TextView) findViewById(R.id.txt_sidunea);
        ga = (TextView) findViewById(R.id.txt_ga);
        nota = (TextView) findViewById(R.id.txt_nota);
        capitulo = (TextView) findViewById(R.id.txt_capitulo);
        documento = (TextView) findViewById(R.id.txt_doc);
        partida = (TextView) findViewById(R.id.txt_partida);

        descripcion.setText(data.get(0)[3] == "" ? "Vacio" : data.get(0)[3]);
        sidunea.setText(data.get(0)[2] == "" ? "Vacio" : data.get(0)[2]);
        ga.setText(data.get(0)[4] == "" ? "Vacio" : data.get(0)[4]);
        partida.setText(data.get(0)[1] == "" ? "Vacio" : data.get(0)[1]);

        capitulo.setText(data_capitulo.get(0)[1]);
        nota.setText(data_capitulo.get(0)[2]);

        Toast.makeText(this, data_capitulo.get(0)[2], Toast.LENGTH_LONG).show();

//        documento.setText(data_document.get(0)[1] + " " + data_document.get(0)[2] + " " + data_document.get(0)[3]);
    }
}
