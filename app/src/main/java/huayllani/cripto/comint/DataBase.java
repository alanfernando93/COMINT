package huayllani.cripto.comint;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Switch;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataBase {

    private Connection connection = null;
    private Statement st = null;
    private ResultSet rs = null;

    protected String HOST = "192.168.1.105";
    protected String PORT = "3306";
    protected String USER = "arduino";
    protected String PASSWORD = "oracle";
    protected String DB_NAME = "dbcomint";

    private void init() throws SQLException {
        connection = DriverManager.getConnection(getUrl(), USER, PASSWORD);
        st = connection.createStatement();
    }

    private void close() throws SQLException {
        if(connection != null) connection.close();
    }

    public ResultSet query(final String sql) throws Exception {
        String driver = "com.mysql.jdbc.Driver";
        Class.forName(driver).newInstance();
        return new AsyncTask<String, Void, ResultSet>(){

            @Override
            protected ResultSet doInBackground(String... strings) {
                ResultSet res = null;
                try {
                    ArrayList<String[]> data = null;
                    connection = DriverManager.getConnection(getUrl(), USER, PASSWORD);
                    st = connection.createStatement();
                    rs = st.executeQuery(sql);
                }catch (SQLException ex) {
                    Log.e("Error query ", ex.getMessage());
                }
                return rs;
            }
        }.execute().get();
    }

    private String getUrl() {
        return "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME;
    }

    public ArrayList<String[]> getUser() {
        ArrayList<String[]> data = new ArrayList<String[]>();
        try {
            rs = query("select * from Usuario");
            while (rs.next()) {
                data.add(new String[]{
                        String.valueOf(rs.getInt("id")),
                        rs.getString("nombre"),
                        rs.getString("turno"),
                        rs.getString("paralelo"),
                        rs.getString("usuario"),
                        rs.getString("contraseña")
                });
            }
        } catch (Exception ex){
            Log.e("Error ", ex.getMessage());
        }finally {
            try{
                st.close();
                rs.close();
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return data;
    }

    public boolean isLogin(String username, String password) {
        ArrayList<String[]> data = new ArrayList<String[]>();
        Boolean is = false;
        try {
            rs = query("select * from Usuario where usuario='" + username + "' and contraseña=" + password);
            while (rs.next()) {
                data.add(new String[]{
                        String.valueOf(rs.getInt("id")),
                        rs.getString("nombre"),
                        rs.getString("turno"),
                        rs.getString("paralelo"),
                        rs.getString("usuario"),
                        String.valueOf(rs.getInt("contraseña"))
                });
            }
            if (data.size() > 0) is = true;
        } catch (Exception ex){
            Log.e("Error ", ex.getMessage());
        }finally {
            try{
                st.close();
                rs.close();
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return is;
    }

    public ArrayList<String[]> getTemas(){
        ArrayList<String[]> data = new ArrayList<String[]>();
        try {
            rs = query("select * from Temas");
            while (rs.next()) {
                data.add(new String[]{
                        String.valueOf(rs.getInt("id")),
                        rs.getString("titulo"),
                        rs.getString("contenido"),
                        rs.getString("imagen_tem")
                });
            }
        } catch (Exception ex){
            Log.e("Error ", ex.getMessage());
        }finally {
            try{
                st.close();
                rs.close();
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return data;
    }

    public ArrayList<String[]> getTemas(String id){
        ArrayList<String[]> data = new ArrayList<String[]>();
        try {
            rs = query("select * from Temas where id=" + Integer.parseInt(id));
            while (rs.next()) {
                data.add(new String[]{
                        String.valueOf(rs.getInt("id")),
                        rs.getString("titulo"),
                        rs.getString("contenido"),
                        rs.getString("imagen_tem")
                });
            }
        } catch (Exception ex){
            Log.e("Error ", ex.getMessage());
        }finally {
            try{
                st.close();
                rs.close();
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return data;
    }

    public ArrayList<String[]> getArancel(String character){
        ArrayList<String[]> data = new ArrayList<String[]>();
        try {
            rs = query("select * from Arancel where descripcion like '%" + character.trim() + "%'");
            while (rs.next()) {
                data.add(new String[]{
                        String.valueOf(rs.getInt("id")),
                        rs.getString("partida"),
                        String.valueOf(rs.getInt("sidunea")),
                        rs.getString("descripcion"),
                        rs.getString("ga"),
                        rs.getString("ICE/IEHD"),
                        rs.getString("uni_med"),
                        rs.getString("can"),
                        rs.getString("ace 36_merc"),
                        rs.getString("chi"),
                        rs.getString("prot"),
                        rs.getString("ace_66"),
                        rs.getString("ace_47"),
                        rs.getString("ven"),
                        String.valueOf(rs.getInt("idCapitulo")),
                        String.valueOf(rs.getInt("idDocumentos")),
                        String.valueOf(rs.getInt("idUsuario"))
                });
            }
        } catch (Exception ex){
            Log.e("Error ", ex.getMessage());
        }finally {
            try{
                st.close();
                rs.close();
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return data;
    }

    public ArrayList<String[]> getArancelById(String id){
        ArrayList<String[]> data = new ArrayList<String[]>();
        try {
            rs = query("select * from Arancel where id=" + id);
            while (rs.next()) {
                data.add(new String[]{
                        String.valueOf(rs.getInt("id")),
                        rs.getString("partida"),
                        String.valueOf(rs.getInt("sidunea")),
                        rs.getString("descripcion"),
                        rs.getString("ga"),
                        rs.getString("ICE/IEHD"),
                        rs.getString("uni_med"),
                        rs.getString("can"),
                        rs.getString("ace 36_merc"),
                        rs.getString("chi"),
                        rs.getString("prot"),
                        rs.getString("ace_66"),
                        rs.getString("ace_47"),
                        rs.getString("ven"),
                        String.valueOf(rs.getInt("idCapitulo")),
                        String.valueOf(rs.getInt("idDocumentos")),
                        String.valueOf(rs.getInt("idUsuario"))
                });
            }
        } catch (Exception ex){
            Log.e("Error ", ex.getMessage());
        }finally {
            try{
                st.close();
                rs.close();
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return data;
    }

    public ArrayList<String[]> getCapituloById(String id){
        ArrayList<String[]> data = new ArrayList<String[]>();
        try {
            rs = query("select * from Capitulo where id=" + id.trim());
            Log.i("Query", "select * from Capitulo where id=" + id.trim());
            while (rs.next()) {
                data.add(new String[]{
                        String.valueOf(rs.getInt("id")),
                        rs.getString("Nombre"),
                        rs.getString("Nota"),
                        String.valueOf(rs.getInt("idSeccion"))
                });
            }
        } catch (Exception ex){
            Log.e("Error ", ex.getMessage());
        }finally {
            try{
                st.close();
                rs.close();
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return data;
    }

    public ArrayList<String[]> getDocumentById(String id){
        ArrayList<String[]> data = new ArrayList<String[]>();
        try {
            rs = query("select * from Documentos where id=" + id.trim());
            Log.i("Query", "select * from Documentos where id=" + id.trim());
            while (rs.next()) {
                data.add(new String[]{
                        String.valueOf(rs.getInt("id")),
                        rs.getString("tipo"),
                        rs.getString("entidad"),
                        rs.getString("displegal")
                });
            }
        } catch (Exception ex){
            Log.e("Error ", ex.getMessage());
        }finally {
            try{
                st.close();
                rs.close();
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return data;
    }

    public ArrayList<String[]> getPracticas(){
        ArrayList<String[]> data = new ArrayList<String[]>();
        try {
            rs = query("select * from Preguntas");
            while (rs.next()) {
                data.add(new String[]{
                        String.valueOf(rs.getInt("id")),
                        rs.getString("contenido"),
                        rs.getString("imagen"),
                        String.valueOf(rs.getString("idTemas"))
                });
            }
        } catch (Exception ex){
            Log.e("Error ", ex.getMessage());
        }finally {
            try{
                st.close();
                rs.close();
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return data;
    }

    public ArrayList<String[]> getPracticas(String idTemas){
        ArrayList<String[]> data = new ArrayList<String[]>();
        try {
            rs = query("select * from Preguntas where idTemas=" + idTemas);
            while (rs.next()) {
                data.add(new String[]{
                        String.valueOf(rs.getInt("id")),
                        rs.getString("contenido"),
                        rs.getString("imagen"),
                        String.valueOf(rs.getString("idTemas"))
                });
            }
        } catch (Exception ex){
            Log.e("Error ", ex.getMessage());
        }finally {
            try{
                st.close();
                rs.close();
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return data;
    }

    public ArrayList<String[]> getRespuestas() {
        ArrayList<String[]> data = new ArrayList<String[]>();
        try {
            rs = query("select * from Respuestas");
            while (rs.next()) {
                data.add(new String[]{
                        String.valueOf(rs.getInt("id")),
                        rs.getString("contenido"),
                        String.valueOf(rs.getInt("estado")),
                        String.valueOf(rs.getString("idTemas"))
                });
            }
        } catch (Exception ex){
            Log.e("Error ", ex.getMessage());
        }finally {
            try{
                st.close();
                rs.close();
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return data;
    }

    public ArrayList<String[]> getRespuestas(String idPreguntas) {
        ArrayList<String[]> data = new ArrayList<String[]>();
        try {
            rs = query("select * from Respuestas where idPreguntas=" + idPreguntas);
            while (rs.next()) {
                data.add(new String[]{
                        String.valueOf(rs.getInt("id")),
                        rs.getString("contenido"),
                        String.valueOf(rs.getInt("estado")),
                        String.valueOf(rs.getString("idPreguntas")),
                        String.valueOf(rs.getString("idTemas"))
                });
            }
        } catch (Exception ex){
            Log.e("Error ", ex.getMessage());
        }finally {
            try{
                st.close();
                rs.close();
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return data;
    }
}
