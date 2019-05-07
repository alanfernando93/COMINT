package huayllani.cripto.comint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class search extends AppCompatActivity {
    DataBase db = new DataBase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    public void onSearch(View view) {
        EditText txt_search = (EditText)findViewById(R.id.edit_search);

        String character = txt_search.getText().toString();

        ArrayList<String[]> data = db.getArancel(character);

        ListView list = (ListView) findViewById(R.id.list_view_arancel);
        list.setAdapter(new ArancelAdapter(this, data));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(search.this, data.get(position)[3].toString(), Toast.LENGTH_LONG).show();

                Intent i = new Intent(search.this, ViewArancel.class);
                i.putExtra("id", data.get(position)[0]);
                startActivity(i);
            }
        });
    }
}
