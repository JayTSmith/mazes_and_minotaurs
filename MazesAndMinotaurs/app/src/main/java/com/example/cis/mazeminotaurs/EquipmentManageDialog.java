package com.example.cis.mazeminotaurs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
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

    /**
     * The editable text box widget for the equipment's id.
     */
    private EditText idEdit;

    /**
     * The editable text box widget for the equipment's cost.
     */
    private EditText costEdit;

    /**
     * The editable text box widget for the equipment's encumbrance.
     */
    private EditText encumbEdit;



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
            idEdit = (EditText) rootView.findViewById(R.id.manage_equipment_id_edit_text);
            costEdit = (EditText) rootView.findViewById(R.id.manage_equipment_cost_edit_text);
            encumbEdit = (EditText) rootView.findViewById(R.id.manage_equipment_encumb_edit_text);

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

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

        if (!costEdit.getText().toString().trim().isEmpty()) {
            double newCost = Double.valueOf(costEdit.getText().toString());
            if (newCost != data.getCostInSp()) {
                Log.d(TAG, String.format("Updating equipment's cost to %.2f.", newCost));
                data.setCostInSp(newCost);
            }
        }

        if (!encumbEdit.getText().toString().trim().isEmpty()) {
            int newEncumb = Integer.valueOf(encumbEdit.getText().toString());
            if (newEncumb != data.getEncumberance()) {
                Log.d(TAG, String.format("Updating equipment's encumbrance to %d.", newEncumb));
                data.setEncumberance(newEncumb);
            }
        }
    }

    public EquipmentManageListener getListener() {
        return mListener;
    }

    public void setListener(EquipmentManageListener listener) {
        mListener = listener;
    }
}
