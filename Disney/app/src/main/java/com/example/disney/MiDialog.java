package com.example.disney;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.disney.HiloConexion;

public class MiDialog extends DialogFragment implements View.OnClickListener {

    private final Handler handler;
    private final Context applicationContext;

    Spinner categories;

    public MiDialog(Handler handler, Context applicationContext){

        this.handler = handler;
        this.applicationContext = applicationContext;
    }

  /*  @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstance) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_dialog, null);

        categories = view.findViewById(R.id.categories_spinner);


        ArrayAdapter<CharSequence> adapter_categories = ArrayAdapter.createFromResource(getContext(),
                R.array.spinner_array_categories, android.R.layout.simple_spinner_item);

        adapter_categories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        categories.setAdapter(adapter_categories);


        Button cancelBtn = view.findViewById(R.id.cancelar);
        cancelBtn.setOnClickListener(view1 -> {
            Log.d("cancel dialog", "dismiss()");
            dismiss();
        });

        Button guardarBtn = view.findViewById(R.id.buscar_disney_btn);
        guardarBtn.setOnClickListener(this);


        builder.setView(view);

        return builder.create();
    } */

    @Override
    public void onClick(View view) {

   /*     Log.d("onClick Buscar", "buscar por filtros");


        String category = categories.getSelectedItem().toString();



        HiloConexion hiloDisney = new HiloConexion(handler, applicationContext, "https://api.disneyapi.dev/characters"+"&categories="+category, 0, false);
        hiloDisney.start();

        dismiss();
*/
    }

}
