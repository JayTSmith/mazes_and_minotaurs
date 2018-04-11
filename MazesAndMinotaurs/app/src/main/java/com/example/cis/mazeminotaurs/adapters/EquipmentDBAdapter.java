package com.example.cis.mazeminotaurs.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cis.mazeminotaurs.Equipment;
import com.example.cis.mazeminotaurs.EquipmentDB;
import com.example.cis.mazeminotaurs.R;

import java.util.List;

public class EquipmentDBAdapter extends BaseAdapter {
    /**
     * A constant used as a flag for the armor list in EquipmentDB.
     */
    public static final int ARMOR_LIST = 1;

    /**
     * A constant used as a flag for the equipment list in EquipmentDB.
     */
    public static final int EQUIPMENT_LIST = 2;

    /**
     * A constant used as a flag for the weapon list in EquipmentDB.
     */
    public static final int WEAPONS_LIST = 3;

    /**
     * A context to get the layout from.
     */
    private Context mContext;

    /**
     * The selected list dependent on flag.
     */
    private List mCurrentList;

    /**
     * Default constructor.
     *
     * @param flag one of the specified constants in {@link EquipmentDBAdapter} that determines the list returned.
     * @param context a context from a fragment or activity.
     */
    public EquipmentDBAdapter(int flag, Context context) throws IllegalArgumentException{

        mContext = context;
        EquipmentDB ins = EquipmentDB.getInstance();

        switch (flag) {
            case ARMOR_LIST:
                mCurrentList = ins.getArmors();
                break;
            case EQUIPMENT_LIST:
                mCurrentList = ins.getEquipments();
                break;
            case WEAPONS_LIST:
                mCurrentList = ins.getWeapons();
                break;
            default:
                throw new IllegalArgumentException("flag must be one of the constants of EquipmentDBAdapter!");
        }
    }


    /**
     * Helper method to remove an item from the data set. Calls notifyDataSetChanged when done.
     * @param i index of item in list.
     */
    public void removeItem(int i) {
        mCurrentList.remove(i);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() { return mCurrentList.size();}

    @Override
    public Equipment getItem(int i) {return (Equipment) mCurrentList.get(i);}

    @Override
    public long getItemId(int i) {return getItem(i).getResId().hashCode();}

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(mContext)
                    .inflate(R.layout.spinner_item_equipment, viewGroup, false);
        }

        Equipment equipment = getItem(i);

        TextView nameView = (TextView) view.findViewById(R.id.equipment_name_view);
        TextView encumbView = (TextView) view.findViewById(R.id.equipment_encumb_view);
        TextView costView = (TextView) view.findViewById(R.id.equipment_cost_view);
        TextView quanView = (TextView) view.findViewById(R.id.equipment_quantity_view);

        if (equipment != null) {
            nameView.setText(equipment.getResId());
            encumbView.setText(mContext.getString(R.string.format_equip_encumb,
                               equipment.getEncumberance()));
            costView.setText(mContext.getString(R.string.format_equip_cost,
                    equipment.getCostInSp()));
            quanView.setVisibility(View.GONE);
        }
        return view;
    }

}
