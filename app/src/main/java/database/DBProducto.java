package database;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.Grupo1.tp4.Alta;
import com.google.android.material.snackbar.Snackbar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import adapter.CategoryAdapter;
import entidad.Categoria;
import entidad.Producto;

public class DBProducto extends AsyncTask<String, Void, String> {
    private Producto producto;
    private Context context;
    private EditText txtId;
    private EditText txtNombreProducto;
    private EditText txtStock;
    private Boolean isInsertion;
    private Boolean isIdSearch;
    private Boolean wasRecordInserted = false;
    private Spinner spnCategoria;
    private Boolean returnAllRecords = false;
    public DBProducto(Context context, EditText txtId, EditText txtNombreProducto, EditText txtStock, Boolean isInsertion, Boolean isIdSearch, Spinner spinner) {
        this.context = context;
        this.txtId = txtId;
        this. txtNombreProducto = txtNombreProducto;
        this.txtStock = txtStock;
        this.isInsertion = isInsertion;
        this.isIdSearch = isIdSearch;
        this.spnCategoria = spinner;
    }

    public Boolean getIdSearch() {
        return isIdSearch;
    }

    public void setIdSearch(Boolean idSearch) {
        isIdSearch = idSearch;
    }

    public Boolean isInsertion(){
        return this.isInsertion;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public boolean existeArticulo(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from articulo where id = " + producto.getId());
            boolean exist = rs.next();
            return exist;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean fillModifyControls(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from articulo where id = " + producto.getId());
            if(rs.next()){
                txtNombreProducto.setText(rs.getString(2));
                txtStock.setText(String.valueOf(rs.getInt(3)).toString());
                spnCategoria.setSelection(rs.getInt(4));
                return true;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public String insert(){
        int insertedRows = 0;
        String msg = "";
        try {
            if(!existeArticulo())
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
                PreparedStatement preparedStatement = con.prepareStatement("insert into articulo (id, nombre, stock, idCategoria) values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setInt(1, producto.getId());
                preparedStatement.setString(2, producto.getNombre());
                preparedStatement.setInt(3, producto.getStock());
                preparedStatement.setInt(4, producto.getCategoria());
                insertedRows = preparedStatement.executeUpdate();
            }
            else
            {
                msg = "Ya existe ese ID en un producto.";
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        if(insertedRows == 1){
            wasRecordInserted = true;
            msg = "Se guardo el articulo correctamente";
        }
        return msg;
    }

    public String Modify(){
        int insertedRows = 0;
        String msg = "";
        try {
            if(existeArticulo())
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
                PreparedStatement preparedStatement = con.prepareStatement("update articulo set nombre = ?, stock = ?, idCategoria = ? where id = ?", Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, producto.getNombre());
                preparedStatement.setInt(2, producto.getStock());
                preparedStatement.setInt(3, producto.getCategoria());
                preparedStatement.setInt(4, producto.getId());
                insertedRows = preparedStatement.executeUpdate();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        if(insertedRows == 1){
            wasRecordInserted = true;
            return "Se el articulo se guardo correctamente";
        }
        return msg = "No se encontro un articulo con ese ID.";
    }

    @Override
    protected String doInBackground(String... String) {
        if(isIdSearch && existeArticulo())
        {
            fillModifyControls();
            return "Se encontro el articulo.";
        }
        else if(isIdSearch)
        {
            return "No se encontro un articulo con ese ID";
        }
        if(isInsertion)
            return insert();
        return Modify();
    }

    @Override
    protected void onPostExecute(String msg) {
        if(wasRecordInserted)
        {
            txtStock.setText("");
            txtNombreProducto.setText("");
            txtId.setText("");
        }
        wasRecordInserted = false;
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
