package huayllani.cripto.comint;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PreguntaAdapter extends BaseAdapter {

    DataBase db = new DataBase();

    ArrayList<String[]> preg;
    ArrayList<String[]> resp;

    private static LayoutInflater inflater = null;

    int count;
    Map<Integer, Boolean> success = new HashMap<Integer, Boolean>();
    Context contexto;
    RadioButton btn_resp;

    public PreguntaAdapter(Context conexto, ArrayList<String[]> preg) {
        this.contexto = conexto;
        this.preg = preg;

        count = 0;
        inflater = (LayoutInflater)conexto.getSystemService(conexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return preg.size();
    }

    @Override
    public Object getItem(int position) {
        return preg.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public Map<Integer, Boolean> getPoints() {
        return success;
    }

    public void initPoint() {
        success.clear();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View vista = inflater.inflate(R.layout.item_pregunta, null);

        TextView title_preg= (TextView) vista.findViewById(R.id.txt_preg);
        RadioGroup group = (RadioGroup) vista.findViewById(R.id.radio_group);

        title_preg.setText(preg.get(position)[1]);
        resp = db.getRespuestas(preg.get(position)[0]);

        group.setId(Integer.parseInt(preg.get(position)[0]));
        for (String[] row: resp) {
            btn_resp = new RadioButton(contexto);

            btn_resp.setId(Integer.parseInt(row[0]));
            btn_resp.setText(row[1]);
            btn_resp.setTag(row[2]);
            btn_resp.setTextSize(10);

            group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    RadioButton rb= (RadioButton) vista.findViewById(checkedId);
                    success.put(position, rb.getTag() == "1");
                }
            });
            group.addView(btn_resp);
        }
        return vista;
    }
}
