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

public class AdaptadorListaNombres extends BaseAdapter{
    Context ctx;
    LayoutInflater lf;
    ArrayList<DatosPersona> todos;
    public AdaptadorListaNombres(Context ctx, ArrayList<DatosPersona> todos){
        this.ctx=ctx;
        this.todos=todos;
        lf=LayoutInflater.from(ctx);
    }
    @Override
    public int getCount() {
        return todos.size()+2;
    }

    @Override
    public Object getItem(int position) {
        //queremos que el primer elemento del spinner
        //sea una cadena vacía y en la posición 1 la palabra todos
        if(position==0){
            return "";
        }else if(position==1) {
            return "Todos";
        }else{
            return todos.get(position-2);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=lf.inflate(android.R.layout.simple_list_item_1,null);
        TextView tv=(TextView)convertView.findViewById(android.R.id.text1);
        if(position==0){
            tv.setText("");
        }else if(position==1) {
            tv.setText("Todos");
        }else{
            tv.setText(todos.get(position-2).getNombre());
        }
        return convertView;
    }
}
