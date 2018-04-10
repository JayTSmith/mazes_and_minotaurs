package com.example.cis.mazeminotaurs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.cis.mazeminotaurs.adapters.CharacterAdapter;

/**
 * This is the old fragment for selecting the active character.
 * @see CharacterManageActivity
 * @deprecated
 *
 * @author jsmith on 10/18/17.
 */

public class CharacterSelectionFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater li, ViewGroup vg, Bundle b) {
        super.onCreateView(li, vg, b);
        View view = li.inflate(R.layout.fragment_character_selection, vg, false);

        ListView charListView = (ListView) view.findViewById(R.id.character_list_view);
        charListView.setAdapter(new CharacterAdapter(getContext()));

        charListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Portfolio.get().setActiveCharacterIndex(i);
            }
        });

        return view;
    }
}
