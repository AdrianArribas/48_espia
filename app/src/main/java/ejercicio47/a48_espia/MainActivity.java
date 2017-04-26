package ejercicio47.a48_espia;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import javabeans.DatosPersona;
import modelo.GestionComunicacion;

public class MainActivity extends AppCompatActivity {
    Spinner sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp=(Spinner)this.findViewById(R.id.spNombres);
        Comunicacion com=new Comunicacion();
        com.execute();
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String nombre="";
                //si se selecciona el primer elemento, que es una cadena vacía
                //abandonamos el método sin hacer nada
                if(position==0){
                    return;
                }
                //recogemos el nombre de la persona selecionada
                if(position>1){
                    TextView tv=(TextView)view.findViewById(android.R.id.text1);
                    nombre=tv.getText().toString();
                }
                //creamos un objeto Intent asociado a la actividad que vamos a cargar
                Intent intent=new Intent(MainActivity.this,ListadoDatosActivity.class);
                intent.putExtra("nombre",nombre);
                //cargamos actividad
                MainActivity.this.startActivity(intent);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }







    class Comunicacion extends AsyncTask<Void,Void,ArrayList<DatosPersona>>{
        @Override
        protected void onPostExecute(ArrayList<DatosPersona> datosPersonas) {
            //cuando tengamos la lista de respuesta, la volcamos
            //en el Spinner
            AdaptadorListaNombres adp=new AdaptadorListaNombres(MainActivity.this,datosPersonas);
            sp.setAdapter(adp);

        }

        @Override
        protected ArrayList<DatosPersona> doInBackground(Void... params) {
            //nos conectamos con el servidor para pedirle la lista de personas
            GestionComunicacion gcom=new GestionComunicacion();
            return gcom.recuperarTodas();
        }
    }
}
