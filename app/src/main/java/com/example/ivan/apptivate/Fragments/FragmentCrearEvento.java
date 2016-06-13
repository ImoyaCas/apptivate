package com.example.ivan.apptivate.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.ivan.apptivate.R;
import com.example.ivan.apptivate.controlador.ServicioCrearEvento;
import com.example.ivan.apptivate.modelo.RestClient;
import com.example.ivan.apptivate.modelo.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentCrearEvento.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FragmentCrearEvento extends Fragment {

    private OnFragmentInteractionListener mListener;
    String evento;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_crear_evento, container, false);



        final EditText nombre;
        final EditText lugar;
        final EditText descripcion;
        final EditText precio;
        final EditText plazas;
        final EditText fecha;
        final EditText hora;

        if(view != null) {

            nombre = (EditText) view.findViewById(R.id.nombreEvento);
            lugar = (EditText) view.findViewById(R.id.lugarEvento);
            descripcion = (EditText) view.findViewById(R.id.descripcionEvento);
            precio = (EditText) view.findViewById(R.id.precioEvento);
            plazas = (EditText) view.findViewById(R.id.plazasEvento);
            fecha = (EditText) view.findViewById(R.id.fecha);
            hora = (EditText) view.findViewById(R.id.hora);

            Button crear = (Button) view.findViewById(R.id.crear);
            crear.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
//Obtengo valores de los edittext
                    String nombre1 = nombre.getText().toString();
                    String lugar1 = lugar.getText().toString();
                    String descripcion1 = descripcion.getText().toString();
                    String precio1 = precio.getText().toString();
                    Integer plazas1 = Integer.parseInt(plazas.getText().toString());
                    String fecha1 = fecha.getText().toString();
                    String hora1 = hora.getText().toString();

                    int idUsuario = Usuario.idVista;
                    System.out.print("Valor de idUser: " + Usuario.idVista + "Y el otro: " + idUsuario);
                    Double precio2 = Double.valueOf(precio1);
                    Log.i("variable fecha1 =",""+fecha1+fecha1);
                    Log.i("variable hora1 =",""+hora1+hora1);
                    RestClient restClient = new RestClient();
                    Retrofit retrofit = restClient.getRetrofit();

                    ServicioCrearEvento servicio = retrofit.create(ServicioCrearEvento.class);
                    final Call<String> respuesta = servicio.setUser(nombre1, lugar1, descripcion1, precio2, plazas1, idUsuario, fecha1, hora1);
                    respuesta.enqueue(new Callback<String>() {

                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {

                            nombre.setText("");
                            lugar.setText("");
                            descripcion.setText("");
                            precio.setText("");
                            plazas.setText("");
                            fecha.setText("");
                            hora.setText("");
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                }
            });
        }
        return view;

          }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
