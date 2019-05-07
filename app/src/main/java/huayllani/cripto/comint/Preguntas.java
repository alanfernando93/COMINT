package huayllani.cripto.comint;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Preguntas extends AppCompatActivity {


    private static LayoutInflater inflater = null;

    DataBase db = new DataBase();

    ListView listView;

    String idTema;
    ArrayList<String[]> preg = new ArrayList<String[]>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);

        listView = (ListView) findViewById(R.id.list_view_pregunta);
        idTema = getIntent().getStringExtra("id");

        preg = db.getPracticas(idTema);

        listView.setAdapter(new PreguntaAdapter(this, preg));
    }
}
