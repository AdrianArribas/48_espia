package ejercicio47.a48_espia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import javabeans.DatosPersona;

/**
 * Created by USUARIO on 24/04/2017.
 */

public class AdaptadorListaDatos extends BaseAdapter {
    Context ctx;
    LayoutInflater lf;
    ArrayList<DatosPersona> todos;
    public AdaptadorListaDatos(Context ctx, ArrayList<DatosPersona> todos){
        this.ctx=ctx;
        this.todos=todos;
        lf=LayoutInflater.from(ctx);
    }
    @Override
    public int getCount() {
        return todos.size();
    }

    @Override
    public Object getItem(int position) {
        return todos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=lf.inflate(R.layout.fila,null);
        TextView tvFilaNombre=(TextView)convertView.findViewById(R.id.tvFilaNombre);
        tvFilaNombre.setText(todos.get(position).getNombre());
        TextView tvFilaLongitud=(TextView)convertView.findViewById(R.id.tvFilaLongitud);
        tvFilaLongitud.setText(String.valueOf(todos.get(position).getLongitud()));
        TextView tvFilaLatitud=(TextView)convertView.findViewById(R.id.tvFilaLatitud);
        tvFilaLatitud.setText(String.valueOf(todos.get(position).getLatitud()));
        return convertView;
    }
}
