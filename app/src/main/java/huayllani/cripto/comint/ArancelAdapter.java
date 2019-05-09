package huayllani.cripto.comint;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ArancelAdapter extends BaseAdapter {
    DataBase db = new DataBase();

    private static LayoutInflater inflater = null;
    Context contexto;

    ArrayList<String[]> data;
    ArrayList<String[]> data_capitulo = new ArrayList<String[]>();
    ArrayList<String[]> data_document= new ArrayList<String[]>();

    public ArancelAdapter(Context conexto, ArrayList<String[]> content)
    {
        this.contexto = conexto;
        this.data = content;

        inflater = (LayoutInflater)conexto.getSystemService(conexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Integer.parseInt(data.get(position)[0]);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final View vista = inflater.inflate(R.layout.item_arancel, null);

        data_capitulo = db.getCapituloById(data.get(position)[14]);

        TextView title = (TextView) vista.findViewById(R.id.txt_title);
        TextView partida = (TextView) vista.findViewById(R.id.txt_partida);
        TextView doc = (TextView) vista.findViewById(R.id.txt_doc);
        TextView capitulo = (TextView) vista.findViewById(R.id.txt_capitulo);
        TextView unidad = (TextView) vista.findViewById(R.id.txt_unidad);

        title.setText(data.get(position)[3]);
        partida.setText(data.get(position)[1]);
        doc.setText(data.get(position)[6] + " " +  data.get(position)[7] + " " + data.get(position)[8]);
        capitulo.setText(data_capitulo.get(0)[1]);
        unidad.setText(data.get(position)[4]);


        return vista;
    }
}
