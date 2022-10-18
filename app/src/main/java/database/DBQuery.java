package database;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import adapter.ProductAdapter;
import entidad.Producto;

public class DBQuery extends AsyncTask<String, Void, String> {
    private ArrayList<Producto> productos;
    private GridView gridView;
    private Context context;
    public DBQuery(Context context, GridView gridView) {
        this.gridView = gridView;
        this.context = context;
    }

    public void getAllProducts(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from articulo inner join categoria on articulo.idCategoria = categoria.id");
            productos = new ArrayList<>();
            while (rs.next()){
                Producto producto = new Producto();
                producto.setId(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setStock(rs.getInt(3));
                productos.add(producto);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(String... String) {
        getAllProducts();
        return "";
    }

    @Override
    protected void onPostExecute(String msg) {
        gridView.setAdapter(new ProductAdapter(context, productos));
    }
}
