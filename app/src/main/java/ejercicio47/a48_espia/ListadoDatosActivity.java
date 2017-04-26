package ejercicio47.a48_espia;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import javabeans.DatosPersona;
import modelo.GestionComunicacion;

public class ListadoDatosActivity extends AppCompatActivity {
    ListView lstDatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_datos);
        lstDatos=(ListView)this.findViewById(R.id.lstDatos);
        String nombre;
        //recuperamos el valor del nombre enviado
        //desde la actividad principal
        Intent intent=this.getIntent();
        nombre=intent.getStringExtra("nombre");
        Comunicacion com=new Comunicacion();
        com.execute(nombre);
        lstDatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //creamos un intent asociado a la actividad de los mapas
                Intent intent=new Intent(ListadoDatosActivity.this,MapaActivity.class);
                //recogemos los datos de la fila seleccionada
                String nombre=((TextView)view.findViewById(R.id.tvFilaNombre)).getText().toString();
                String longitud=((TextView)view.findViewById(R.id.tvFilaLongitud)).getText().toString();
                String latitud=((TextView)view.findViewById(R.id.tvFilaLatitud)).getText().toString();
                //se los pasamos como extras a la actividad de los mapas
                intent.putExtra("nombre",nombre);
                intent.putExtra("longitud",longitud);
                intent.putExtra("latitud",latitud);
                ListadoDatosActivity.this.startActivity(intent);

            }
        });
    }

    class Comunicacion extends AsyncTask<String,Void, ArrayList<DatosPersona>>{
        @Override
        protected void onPostExecute(ArrayList<DatosPersona> datosPersonas) {
            AdaptadorListaDatos adp=new AdaptadorListaDatos(ListadoDatosActivity.this,datosPersonas);
            lstDatos.setAdapter(adp);
        }

        @Override
        protected ArrayList<DatosPersona> doInBackground(String... params) {
            String nombre=params[0];
            GestionComunicacion gcom=new GestionComunicacion();
            ArrayList<DatosPersona> resultado;
            if(nombre.equals("")){
                resultado=gcom.recuperarTodas();
            }else{
                //si solamente hay una persona, debemos crear un arraylist
                //con ese objeto para poder crear después el adaptador
                DatosPersona dt=gcom.recuperarDatosPersona(nombre);
                resultado=new ArrayList<>();
                resultado.add(dt);
            }
            return resultado;

        }
    }
}
