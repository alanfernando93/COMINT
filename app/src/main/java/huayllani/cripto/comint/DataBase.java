package huayllani.cripto.comint;

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

//    public String[] query() {
//        String[] result = null;
//        try {
//            String driver = "com.mysql.jdbc.Driver";
//            Class.forName(driver).newInstance();
//            String sql = "select * from Usuario";
//            result = new consultas(sql).execute().get();
//        }catch (Exception ex) {
//            Log.d("Error al obtener result", ex.getMessage());
//        }
//        return result;
//    }

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

}
