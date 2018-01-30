package com.example.cis.mazeminotaurs.NewCharacter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.cis.mazeminotaurs.MainMazes;
import com.example.cis.mazeminotaurs.R;

/**
 * This activity serves as a container for the character sheet and related fragments.
 * The user does nothing to interact with this.
 *
 * @author jsmith, 1/30/18
 */

public class CharacterCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_character_create);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.create_content_frame, new CharacterCreationFragment())
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpTo(this, new Intent(this, MainMazes.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
