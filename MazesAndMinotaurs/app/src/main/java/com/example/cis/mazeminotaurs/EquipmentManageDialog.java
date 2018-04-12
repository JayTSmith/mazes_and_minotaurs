package com.example.cis.mazeminotaurs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cis.mazeminotaurs.adapters.EquipmentDBAdapter;
import com.example.cis.mazeminotaurs.util.CommonStrings;

import java.util.Locale;

public class EquipmentManageDialog extends DialogFragment {

    /**
     * Serves as the TAG in certain functions. e.g. dialog.show() and logging.
     */
    public static final String TAG = EquipmentManageDialog.class.getName();

    public interface EquipmentManageListener {
        void onDelete(int i);
    }

    /**
     * The object that is listening to this dialog's results.
     */
    private EquipmentManageListener mListener;

    /**
     * The equipment to be displayed.
     */
    private Equipment data;

    /**
     * The position of the item in its array.
     */
    private int pos;


    public static EquipmentManageDialog newInstance(Equipment equipment, int pos) {

        EquipmentManageDialog frag = new EquipmentManageDialog();

        frag.data = equipment;
        frag.pos = pos;
        // This works since we are still within the class

        return frag;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater li = LayoutInflater.from(getContext());
        View rootView = li.inflate(R.layout.dialog_equipment_manage, null);

        if (data != null) {
            EditText idEdit = (EditText) rootView.findViewById(R.id.manage_equipment_id_edit_text);
            EditText costEdit = (EditText) rootView.findViewById(R.id.manage_equipment_cost_edit_text);
            EditText encumbEdit = (EditText) rootView.findViewById(R.id.manage_equipment_encumb_edit_text);

            idEdit.setEnabled(false); // TODO Change this field to a textView.

            idEdit.setText(data.getResId());
            costEdit.setText(String.valueOf(data.getCostInSp()));
            encumbEdit.setText(String.valueOf(data.getEncumberance()));

            rootView.findViewById(R.id.manage_equipment_delete_button)
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (data.isUserMade()) {
                                mListener.onDelete(pos);
                            } else {
                                EquipmentManageDialog.this.dismiss();
                                Toast.makeText(getActivity(),
                                        R.string.manage_equipment_default_error,
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }


        Dialog dialog = new AlertDialog.Builder(getContext()).setView(rootView).create();

        return dialog;
    }

    public EquipmentManageListener getListener() {
        return mListener;
    }

    public void setListener(EquipmentManageListener listener) {
        mListener = listener;
    }
}
