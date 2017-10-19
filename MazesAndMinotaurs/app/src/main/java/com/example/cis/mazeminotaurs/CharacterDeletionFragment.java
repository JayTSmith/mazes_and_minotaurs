package com.example.cis.mazeminotaurs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by jsmith on 10/19/17.
 */

public class CharacterDeletionFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater li, ViewGroup vg, Bundle b) {
        super.onCreateView(li, vg, b);
        View view = li.inflate(R.layout.fragment_character_selection, vg, false);

        ListView charListView = (ListView) view.findViewById(R.id.character_list_view);
        charListView.setAdapter(new CharacterAdapter(getContext(), Portfolio.get().getPortfolio()));

        charListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Portfolio port = Portfolio.get();
                if (port.getPortfolio().size() > 1) {
                    port.deletePlayerCharacter(port.getPlayerCharacter(i));
                } else {
                    Toast.makeText(getContext(), R.string.error_delete_character, Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
