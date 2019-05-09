package huayllani.cripto.comint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Temas extends AppCompatActivity {

    TextView txt_title, txt_content;

    DataBase db = new DataBase();

    String id;
    String[] content = null;
    ArrayList<String[]> value = new ArrayList<String[]>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temas);

        txt_title = (TextView)findViewById(R.id.txt_preg);
        txt_content = (TextView)findViewById(R.id.txt_content);

        this.id = getIntent().getStringExtra("id");

        value = db.getTemas(id);

        if (value.isEmpty()) {
            Toast.makeText(this, "vacio", Toast.LENGTH_LONG).show();
        } else {
            content = value.get(0);
        }

        txt_title.setText(content[1]);
        txt_content.setText(content[2]);

    }
}
