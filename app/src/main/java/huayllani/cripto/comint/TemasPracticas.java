package huayllani.cripto.comint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TemasPracticas extends AppCompatActivity {
    TextView txt_title;
    ListView listView;

    DataBase db = new DataBase();

    Map<String, Class> instance = new HashMap<String, Class>();

    String title;
    String[] keys = null;
    String[] values = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temas_practicas);

        instance.put("Temas", Temas.class);
        instance.put("Practicas", Preguntas.class);

        title = getIntent().getStringExtra("title");

        Intent Pross = new Intent(this, instance.get(title));

        txt_title = (TextView) findViewById(R.id.txt_preg);
        txt_title.setText(title);

        ArrayList<String[]> query = db.getTemas();
        this.values = getList(query);

        if (this.values.length == 0) Toast.makeText(this, "lista vacio", Toast.LENGTH_LONG).show();
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item_temas, this.values);
        listView = (ListView) findViewById(R.id.listview_data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                parent.getItemAtPosition(position);
                Pross.putExtra("id", keys[position]);
                Pross.putExtra("content", listView.getItemIdAtPosition(position));
                startActivity(Pross);
            }
        });
    }

    public String[] getList(ArrayList<String[]> query) {
        String[] list = new String[query.size()];
        this.keys = new String[query.size()];
        for (int i = 0; i < query.size(); i++) {
            list[i] =query.get(i)[1];
            this.keys[i] = query.get(i)[0];
        }
        return list;
    }
}