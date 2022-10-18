package com.Grupo1.tp4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import database.DBCategoria;
import database.DBProducto;
import entidad.Producto;


public class Modificacion extends Fragment {
    private Button btnSearch;
    private Spinner spnCategoriaAlta;
    private EditText txtId;
    private EditText txtNombreProducto;
    private EditText txtStock;
    private Button btnModificar;

    public Modificacion(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_modificacion, container, false);
        btnSearch = (Button) view.findViewById(R.id.btnSearch);
        spnCategoriaAlta = (Spinner) view.findViewById(R.id.spnCategoriaModif);
        txtId = (EditText) view.findViewById(R.id.txtIdModif);
        txtNombreProducto = (EditText) view.findViewById(R.id.txtNombreProductoModif);
        txtStock = (EditText) view.findViewById(R.id.txtStockModif);
        btnModificar = (Button) view.findViewById(R.id.btnModificar);
        DBCategoria dbCategoria = new DBCategoria(getContext(), spnCategoriaAlta);
        dbCategoria.execute();
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchId(v);
            }
        });
        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modify(v);
            }
        });
        return view;
    }

    public boolean isFormValid(){
        String requiredError = "Este campo es requerido";
        boolean isFormValid = true;
        if(txtId.getText().toString().isEmpty())
        {
            txtId.setError(requiredError);
            isFormValid = false;
        }
        if(txtStock.getText().toString().isEmpty())
        {
            txtStock.setError(requiredError);
            isFormValid = false;
        }
        if(txtNombreProducto.getText().toString().isEmpty())
        {
            txtNombreProducto.setError(requiredError);
            isFormValid = false;
        }
        return isFormValid;
    }

    public void searchId(View view){
        DBProducto dbProducto = new DBProducto(view.getContext(), txtId, txtNombreProducto, txtStock, true, true, spnCategoriaAlta);
        Producto producto = new Producto();
        producto.setId(Integer.parseInt(txtId.getText().toString()));
        dbProducto.setProducto(producto);
        dbProducto.execute();
    }

    public void modify(View view){
        if(!isFormValid())
            return;
        DBProducto insert = new DBProducto(view.getContext(), txtId, txtNombreProducto, txtStock, false, false, spnCategoriaAlta);
        Producto producto = new Producto();
        producto.setId(Integer.parseInt(txtId.getText().toString()));
        producto.setStock(Integer.parseInt(txtStock.getText().toString()));
        producto.setNombre(txtNombreProducto.getText().toString());
        producto.setCategoria(spnCategoriaAlta.getSelectedItemPosition() + 1);
        insert.setProducto(producto);
        insert.execute();
    }
}