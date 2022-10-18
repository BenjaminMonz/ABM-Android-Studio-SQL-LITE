package com.Grupo1.tp4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import database.DBCategoria;
import database.DBProducto;
import database.DataDB;
import entidad.Producto;

public class Alta extends Fragment {

    private Spinner spnCategoriaAlta;
    private EditText txtId;
    private EditText txtNombreProducto;
    private EditText txtStock;
    private Button btnAgregar;

    public Alta(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_alta, container, false);
        spnCategoriaAlta = (Spinner) view.findViewById(R.id.spnCategoriaAlta);
        txtId = (EditText) view.findViewById(R.id.txtIdAlta);
        txtNombreProducto = (EditText) view.findViewById(R.id.txtNombreProductoAlta);
        txtStock = (EditText) view.findViewById(R.id.txtStockAlta);
        btnAgregar = (Button) view.findViewById(R.id.btnAgregar);
        DBCategoria dbCategoria = new DBCategoria(getContext(), spnCategoriaAlta);
        dbCategoria.execute();
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickAdd(v);
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

    public String obtainIdFromSpinner(){
        String selectedCategory = spnCategoriaAlta.getSelectedItem().toString();
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(selectedCategory);
        if (matcher.find())
        {
            // we're only looking for one group, so get it
            return matcher.group(0);
        }
        return null;
    }

    public void OnClickAdd(View view){
        if(!isFormValid())
            return;
        DBProducto insert = new DBProducto(view.getContext(), txtId, txtNombreProducto, txtStock, true, false, spnCategoriaAlta);
        Producto producto = new Producto();
        producto.setId(Integer.parseInt(txtId.getText().toString()));
        producto.setStock(Integer.parseInt(txtStock.getText().toString()));
        producto.setNombre(txtNombreProducto.getText().toString());
        producto.setCategoria(spnCategoriaAlta.getSelectedItemPosition() + 1);
        insert.setProducto(producto);
        insert.execute();
    }
}