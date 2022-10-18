package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.Grupo1.tp4.R;

import java.util.ArrayList;
import java.util.List;

import entidad.Producto;

public class ProductAdapter extends BaseAdapter {
    private ArrayList<Producto> elementos;
    private Context context;

    public ProductAdapter(Context context, ArrayList<Producto> elementos) {
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public int getCount() {
        return elementos.size();
    }

    @Override
    public Producto getItem(int position) {
        return elementos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view = convertView;

        if (convertView == null){
            view = inflater.inflate(R.layout.grid_template,null);
        }

        TextView txtIdProducto = (TextView) view.findViewById(R.id.txtIdProducto);
        TextView txtNombreProducto = (TextView) view.findViewById(R.id.txtNombreProducto);
        TextView txtStock = (TextView) view.findViewById(R.id.txtStock);

        txtIdProducto.setText("ID producto: " + getItem(position).getId()+"");
        txtNombreProducto.setText("Nombre de producto: " + getItem(position).getNombre()+"");
        txtStock.setText(String.valueOf("Stock: " + getItem(position).getStock()));

        return view;
    }
}
