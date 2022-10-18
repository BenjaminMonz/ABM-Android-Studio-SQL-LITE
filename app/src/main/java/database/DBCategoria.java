package database;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.Grupo1.tp4.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import adapter.CategoryAdapter;
import entidad.Categoria;
import entidad.Producto;

public class DBCategoria extends AsyncTask<String, Void, String>{
    private Spinner spnCategoriaAlta;
    private CategoryAdapter categoryAdapter;
    private List<Categoria> categoriaArrayList;
    private Context context;
    public DBCategoria() {
    }

    public DBCategoria(Spinner spinner){
        spnCategoriaAlta = spinner;
    }

    public DBCategoria(Context context, Spinner spinner){
        spnCategoriaAlta = spinner;
        this.context = context;
    }

    public Spinner getSpnCategoriaAlta() {
        return spnCategoriaAlta;
    }

    public void setSpnCategoriaAlta(Spinner spnCategoriaAlta) {
        this.spnCategoriaAlta = spnCategoriaAlta;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from categoria");

            Categoria categoria;
            categoriaArrayList = new ArrayList<>();
            while (rs.next()) {
                categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setDescripcion(rs.getString("descripcion"));

                categoriaArrayList.add(categoria);
            }
            this.categoryAdapter = new CategoryAdapter(spnCategoriaAlta.getContext(), categoriaArrayList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    protected void onPostExecute(String response) {
        ArrayAdapter<Categoria> categoriaArrayAdapter = new CategoryAdapter(context, categoriaArrayList);
        spnCategoriaAlta.setAdapter(categoriaArrayAdapter);
    }
}
