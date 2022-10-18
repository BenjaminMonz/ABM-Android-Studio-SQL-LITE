package adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.Grupo1.tp4.R;

import java.util.List;

import entidad.Categoria;
import entidad.Producto;

public class CategoryAdapter extends ArrayAdapter<Categoria> {
    public CategoryAdapter(Context context, List<Categoria> objetos) {
        super(context, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, objetos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(com.google.android.material.R.layout.support_simple_spinner_dropdown_item, null);

        /*
        TextView tvid = (TextView) item.findViewById(R.id.idC);
        TextView tvdni = (TextView) item.findViewById(R.id.dni);
        TextView tvnombre = (TextView) item.findViewById(R.id.nombre);
        TextView tvapellido = (TextView) item.findViewById(R.id.apellido);

        tvid.setText(getItem(position).getId()+"");
        tvdni.setText(getItem(position).getDni()+"");
        tvnombre.setText(getItem(position).getNombre());
        tvapellido.setText(getItem(position).getApellido());
        */
        return item;
    }
}
