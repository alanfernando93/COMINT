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

    protected String HOST = "sql10.freesqldatabase.com";
    protected String PORT = "3306";
    protected String USER = "sql10289788";
    protected String PASSWORD = "H7hJdnRyg3";
    protected String DB_NAME = "sql10289788";

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
                        rs.getString("contraseña")
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

}
