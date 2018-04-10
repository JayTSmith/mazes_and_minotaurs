package com.example.cis.mazeminotaurs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cis.mazeminotaurs.adapters.DetailedWeaponAdapter;
import com.example.cis.mazeminotaurs.character.PlayerCharacter;
import com.example.cis.mazeminotaurs.character.SaveAndLoadDialog;
import com.example.cis.mazeminotaurs.character.classes.Magician;
import com.example.cis.mazeminotaurs.character.classes.Specialist;
import com.example.cis.mazeminotaurs.character.classes.Warrior;
import com.example.cis.mazeminotaurs.character.stats.Score;
import com.example.cis.mazeminotaurs.fragments.ExperienceDialogFragment;
import com.example.cis.mazeminotaurs.fragments.HitsChangeFragment;
import com.example.cis.mazeminotaurs.fragments.InventoryFragment;
import com.example.cis.mazeminotaurs.fragments.StatChangeFragment;
import com.example.cis.mazeminotaurs.rollDice.rollDice;
import com.example.cis.mazeminotaurs.util.CommonStrings;

/**
 * This fragment displays a majority of character information regarding stats.
 * The user can perform rolls, display inventory, and level up on this
 * fragment.
 * @author Thorin Schmidt on 4/1/2017.
 */

public class CharacterSheetFragment extends Fragment
        implements StatChangeFragment.OnStatChangeListener, HitsChangeFragment.onHitsChangeListener,
        ExperienceDialogFragment.ChangeListener {
    /**
     * Serves as the TAG in certain functions. e.g. dialog.show() and logging.
     * Used by most of the dialogs in this fragment.
     */
    public static final String ROLL_RESULT = "RollResult";

    /**
     * Serves as the TAG in certain functions. e.g. dialog.show() and logging.
     */
    public static final String TAG = "CharacterSheetFragment";

    /**
     * The current character's index in the portfolio.
     */
    int mCurrentCharacterIndex;

    /**
     * A reference to the Portfolio singleton instance.
     */
    Portfolio mPortfolio;

    /**
     * The character found at the index specified by mCurrentCharacterIndex.
     */
    PlayerCharacter mSheetPlayerCharacter;

    /*
     * These are widgets found in the layout.
     */
    TextView mCharacterNameView;
    TextView mCharacterLevelView;
    TextView mCharacterClassView;
    TextView mAttackType;
    TextView mMagicTitleView;
    TextView mTalentBonusTitleView;
    TextView mMagicStrengthTitleView;
    TextView mTotalPowerTitleView;
    TextView mCurrentPowerTitleView;

    Button mMightButton;
    Button mSkillButton;
    Button mWitsButton;
    Button mLuckButton;
    Button mWillButton;
    Button mGraceButton;
    Button mEDCButton;
    Button mTotalHitsButton;
    Button mHitsButton;
    Button mAttackButton;
    Spinner mEquippedWeaponSpinner;
    Button mInitiativeButton;
    Button mAPbutton;
    Button mDEbutton;
    Button mMFbutton;
    Button mPVbutton;
    Button mTalentBonusButton;
    Button mMagicStrengthButton;
    Button mTotalPowerButton;
    Button mCurrentPowerButton;

    Button mInventoryButton;

    public CharacterSheetFragment() {
        mPortfolio = Portfolio.get();
    }

    @Override
    public View onCreateView(LayoutInflater li, ViewGroup vg, Bundle b) {
        super.onCreateView(li, vg, b);

        View rootView = li.inflate(R.layout.fragment_character_sheet, vg, false);

        mCurrentCharacterIndex = mPortfolio.getActiveCharacterIndex();
        Log.d(TAG, String.valueOf(mPortfolio.getActiveCharacterIndex()));

        mSheetPlayerCharacter = mPortfolio.getPlayerCharacter(mCurrentCharacterIndex);
        mCharacterLevelView = (TextView) rootView.findViewById(R.id.character_level_view);
        mCharacterLevelView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLevelClick();
            }
        });
        mCharacterNameView = (TextView) rootView.findViewById(R.id.character_name_view);
        mCharacterClassView = (TextView) rootView.findViewById(R.id.character_class_view);

        mMightButton = (Button) rootView.findViewById(R.id.might_score_button);
        mMightButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onScoreLongClick(Score.MIGHT, "Might");
                return true;
            }
        });
        mMightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onScoreClick(Score.MIGHT, "Might");
            }
        });

        mSkillButton = (Button) rootView.findViewById(R.id.skill_score_button);
        mSkillButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onScoreLongClick(Score.SKILL, "Skill");
                return true;
            }
        });
        mSkillButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onScoreClick(Score.SKILL, "Skill");
            }
        });

        mWitsButton = (Button) rootView.findViewById(R.id.wits_score_button);
        mWitsButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onScoreLongClick(Score.WITS, "Wits");
                return true;
            }
        });
        mWitsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onScoreClick(Score.WITS, "Wits");
            }
        });

        mLuckButton = (Button) rootView.findViewById(R.id.luck_score_button);
        mLuckButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onScoreLongClick(Score.LUCK, "Luck");
                return true;
            }
        });
        mLuckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onScoreClick(Score.LUCK, "Luck");
            }
        });

        mWillButton = (Button) rootView.findViewById(R.id.will_score_button);
        mWillButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onScoreLongClick(Score.WILL, "Will");
                return true;
            }
        });
        mWillButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onScoreClick(Score.WILL, "Will");
            }
        });

        mGraceButton = (Button) rootView.findViewById(R.id.grace_score_button);
        mGraceButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onScoreLongClick(Score.GRACE, "Grace");
                return true;
            }
        });
        mGraceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onScoreClick(Score.GRACE, "Grace");
            }
        });

        mAttackType = (TextView) rootView.findViewById(R.id.attack_title_view);

        mAttackButton = (Button) rootView.findViewById(R.id.attack_button);
        mAttackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAttackClick();
            }
        });

        mEquippedWeaponSpinner = (Spinner) rootView.findViewById(R.id.equipped_weapon_spinner);
        DetailedWeaponAdapter weaponAdapter = new DetailedWeaponAdapter(getContext(), mSheetPlayerCharacter.getWeapons());
        mEquippedWeaponSpinner.setAdapter(weaponAdapter);
        //Get equipped weapon from character Class
        //int equippedWeaponID = R.string.barb_axe;
        /*Weapon equippedWeapon = mSheetPlayerCharacter.getCurrentWeapon();
        if (equippedWeapon != null) {
            int equippedWeaponID = equippedWeapon.getResId();
            mEquippedWeaponSpinner.setText(equippedWeaponID);
        } else {
            mEquippedWeaponSpinner.setText("-");
        }*/

        mEquippedWeaponSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mSheetPlayerCharacter.setCurrentWeapon((Weapon) adapterView.getItemAtPosition(i));
                populateSheet();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        mInitiativeButton = (Button) rootView.findViewById(R.id.initiative_modifier_button);
        mInitiativeButton.setText(Integer.toString(mSheetPlayerCharacter.getInitiative()));


        mAPbutton = (Button) rootView.findViewById(R.id.athletic_prowess_button);
        mAPbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveClick(mAPbutton.getId(), "AP");
            }
        });

        mDEbutton = (Button) rootView.findViewById(R.id.danger_evasion_button);
        mDEbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveClick(mDEbutton.getId(), "DE");
            }
        });

        mMFbutton = (Button) rootView.findViewById(R.id.mystic_fortitude_button);
        mMFbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveClick(mMFbutton.getId(), "MF");
            }
        });

        mPVbutton = (Button) rootView.findViewById(R.id.physical_vigor_button);
        mPVbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveClick(mPVbutton.getId(), "PV");
            }
        });

        mEDCButton = (Button) rootView.findViewById(R.id.edc_button);
        mTotalHitsButton = (Button) rootView.findViewById(R.id.total_hits_button);

        mHitsButton = (Button) rootView.findViewById(R.id.current_hits_button);
        mHitsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onHitsClick();
            }
        });

        mInventoryButton = (Button) rootView.findViewById(R.id.inventory_button);
        mInventoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .addToBackStack(InventoryFragment.TAG)
                        .replace(R.id.play_content_frame, new InventoryFragment())
                        .commit();
            }
        });

        // Magical and specialist section
        mMagicTitleView = (TextView) rootView.findViewById(R.id.magic_title_view);
        mTalentBonusTitleView = (TextView) rootView.findViewById(R.id.talent_bonus_title_view);
        mMagicStrengthTitleView = (TextView) rootView.findViewById(R.id.magic_strength_title_view);
        mTotalPowerTitleView = (TextView) rootView.findViewById(R.id.total_power_title_view);
        mCurrentPowerTitleView = (TextView) rootView.findViewById(R.id.current_power_title_view);

        mTalentBonusButton = (Button) rootView.findViewById(R.id.talent_bonus_button);
        mTalentBonusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Put something here.
            }
        });
        mMagicStrengthButton = (Button) rootView.findViewById(R.id.magic_strength_button);
        mTotalPowerButton = (Button) rootView.findViewById(R.id.total_power_button);
        mCurrentPowerButton = (Button) rootView.findViewById(R.id.current_power_button);

        populateSheet();
        return rootView;
    }

    /**
     * Shows the experience management dialog.
     *
     * @see ExperienceDialogFragment
     */
    public void onLevelClick() {
        Bundle args = new Bundle();
        args.putInt(CommonStrings.CHARACTER_ARG.getValue(), mCurrentCharacterIndex);

        ExperienceDialogFragment dialog = new ExperienceDialogFragment();
        dialog.setListener(this);
        dialog.setArguments(args);

        FragmentManager fm = getFragmentManager();
        dialog.show(fm, ExperienceDialogFragment.TAG);
    }

    /**
     * Allows the user to make a roll with a score.
     * @param skill a Score of the character.
     * @param name the name of the score.
     * @see RollResultFragment
     */
    public void onScoreClick(Score skill, String name){
        int modifier =  mSheetPlayerCharacter.getScore(skill).getModifier();
        int dieRoll = rollDice.roll(20);
        FragmentManager fm = getFragmentManager();
        RollResultFragment dialog = RollResultFragment.newInstance(dieRoll, modifier, name);
        dialog.show(fm, ROLL_RESULT);
    }

    /**
     * Allows the user to modify the value of a score.
     * @param skill a Score of the character.
     * @param name the name of the score.
     * @see StatChangeFragment
     */
    public void onScoreLongClick(Score skill, String name) {
        FragmentManager fm = getFragmentManager();
        StatChangeFragment dialog = StatChangeFragment.newInstance(name,
                mSheetPlayerCharacter.getScore(skill).getScore());
        dialog.setStatChangeListener(this);
        dialog.show(fm, ROLL_RESULT);
    }

    /**
     * Allows the user to make an attacking roll with current weapon equipped.
     * @see AttackResultFragment
     */
    public void onAttackClick(){
        FragmentManager fm = getFragmentManager();
        AttackResultFragment dialog = AttackResultFragment.newInstance(mCurrentCharacterIndex);
        dialog.show(fm, ROLL_RESULT);
    }

    /**
     * Allows the user to make a saving throw.
     * @param buttonID The button that was clicked
     * @param saveName the name of the save being rolled.
     * @see SaveResultFragment
     */
    void onSaveClick(int buttonID, String saveName){
        int modifier;
        int saveRoll = rollDice.roll(20);
        FragmentManager fm = getFragmentManager();
        SaveResultFragment dialog = null;
        switch (buttonID){
            case R.id.athletic_prowess_button:
                modifier = mSheetPlayerCharacter.getAthleticProwess();
                dialog = SaveResultFragment.newInstance(saveRoll, modifier, saveName);
                break;
            case R.id.danger_evasion_button:
                modifier = mSheetPlayerCharacter.getDangerEvasion();
                dialog = SaveResultFragment.newInstance(saveRoll, modifier, saveName);
                break;
            case R.id.mystic_fortitude_button:
                modifier = mSheetPlayerCharacter.getMysticFortitude();
                dialog = SaveResultFragment.newInstance(saveRoll, modifier, saveName);
                break;
            case R.id.physical_vigor_button:
                modifier = mSheetPlayerCharacter.getPhysicalVigor();
                dialog = SaveResultFragment.newInstance(saveRoll, modifier, saveName);
                break;
        }
        dialog.show(fm, ROLL_RESULT);
    }

    /**
     * Allows the user to modify how many hits their character has.
     * @see HitsChangeFragment
     */
    void onHitsClick() {
        int curHits = mSheetPlayerCharacter.getCurHits();
        HitsChangeFragment dialog = HitsChangeFragment.newInstance(curHits);
        FragmentManager fm = getFragmentManager();
        dialog.setHitsChangeListener(this);
        dialog.show(fm, ROLL_RESULT);
    }

    /**
     * Saves the current character.
     * @deprecated
     * @see SaveAndLoadDialog
     */
    public void onSaveCharacterClick(){
        FragmentManager fm = getFragmentManager();
        SaveAndLoadDialog dialog = SaveAndLoadDialog.newInstance(mCurrentCharacterIndex);
        dialog.show(fm, ROLL_RESULT);
    }

    @Override
    public void onStatChange(Score score, int newValue) {
        if (mSheetPlayerCharacter.getScore(score).getScore() != newValue) {
            mSheetPlayerCharacter.getScore(score).setScore(newValue);
            mSheetPlayerCharacter.validateScores();
            refreshStats();
        }
    }

    @Override
    public void onHitsChange(int newValue) {
        if (mSheetPlayerCharacter.getCurHits() != newValue) {
            mSheetPlayerCharacter.setCurHits(newValue);
            // Manually update since nothing else would change
            mHitsButton.setText(Integer.toString(mSheetPlayerCharacter.getCurHits()));
        }
    }

    @Override
    public void onExperienceChange() {
        return;
    }

    @Override
    public void onLevelChange() {
        populateSheet();
    }

    /**
     * Helping shorthand to update the text in the character sheet with
     * mSheetPlayerCharacter's values.
     */
    private void populateSheet() {
        populateSheet(mSheetPlayerCharacter);
    }

    /**
     * Updates the text in the fragment with the character supplied.
     * @param character a character to display
     */
    private void populateSheet(PlayerCharacter character) {
        // Hides specific sections(Magician and Specialist) by default
        mMagicTitleView.setVisibility(View.GONE);
        mTalentBonusTitleView.setVisibility(View.GONE);
        mMagicStrengthTitleView.setVisibility(View.GONE);
        mTotalPowerTitleView.setVisibility(View.GONE);
        mCurrentPowerTitleView.setVisibility(View.GONE);

        mTalentBonusButton.setVisibility(View.GONE);
        mMagicStrengthButton.setVisibility(View.GONE);
        mTotalPowerButton.setVisibility(View.GONE);
        mCurrentPowerButton.setVisibility(View.GONE);

        if (character != null) {
            mCharacterNameView.setText(character.getName());
            mCharacterLevelView.setText(Integer.toString(character.getCharClass().getLevel()));
            mCharacterClassView.setText(character.getCharClass().getResId());
            Weapon wepToDisplay = null;

            if (character.getCurrentWeapon() != null) {
                wepToDisplay = character.getCurrentWeapon();
            } else if (character.getWeapons() != null && character.getWeapons().size() > 0){
                wepToDisplay = character.getWeapons().get(0);
            }

            if (wepToDisplay != null) {
                DetailedWeaponAdapter adapter = (DetailedWeaponAdapter) mEquippedWeaponSpinner.getAdapter();
                mEquippedWeaponSpinner.setSelection(adapter.getDataset().indexOf(wepToDisplay));
                mAttackType.setText(wepToDisplay.getWeaponType());
                if (wepToDisplay.getWeaponType() == R.string.melee) {
                    mAttackButton.setText(Integer.toString(character.getMeleeMod()));
                } else {
                    mAttackButton.setText(Integer.toString(character.getMissileMod()));
                }
            } else {
                mAttackButton.setText("-");
                mAttackType.setText("-");
            }

            mMightButton.setText(Integer.toString(character.getScore(Score.MIGHT).getScore()));
            mWitsButton.setText(Integer.toString(character.getScore(Score.WITS).getScore()));
            mLuckButton.setText(Integer.toString(character.getScore(Score.LUCK).getScore()));
            mWillButton.setText(Integer.toString(character.getScore(Score.WILL).getScore()));
            mGraceButton.setText(Integer.toString(character.getScore(Score.GRACE).getScore()));
            mSkillButton.setText(Integer.toString(character.getScore(Score.SKILL).getScore()));
            mInitiativeButton.setText(Integer.toString(character.getInitiative()));
            mAPbutton.setText(Integer.toString(character.getAthleticProwess()));
            mDEbutton.setText(Integer.toString(character.getDangerEvasion()));
            mMFbutton.setText(Integer.toString(character.getMysticFortitude()));
            mPVbutton.setText(Integer.toString(character.getPhysicalVigor()));
            mHitsButton.setText(Integer.toString(character.getHitTotal()));

            mEDCButton.setText(Integer.toString(character.getEDC()));
            mTotalHitsButton.setText(Integer.toString(character.getHitTotal()));

            if (!(character.getCharClass() instanceof Warrior)) {
                mMagicTitleView.setVisibility(View.VISIBLE);
                if (character.getCharClass() instanceof Magician) {
                    Magician instance = (Magician) character.getCharClass();
                    mTalentBonusTitleView.setText(instance.getSpecialTalentResId());
                    mMagicStrengthButton.setText(Integer.toString(instance.getMysticalStrength()));
                    mTotalPowerButton.setText(Integer.toString(instance.getPowerPoints()));
                    mCurrentPowerButton.setText(Integer.toString(instance.getPowerPoints()));
                    mTalentBonusButton.setText(Integer.toString(instance.getSpecialTalent()));

                    mMagicStrengthTitleView.setVisibility(View.VISIBLE);
                    mTotalPowerTitleView.setVisibility(View.VISIBLE);
                    mCurrentPowerTitleView.setVisibility(View.VISIBLE);
                    mTalentBonusTitleView.setVisibility(View.VISIBLE);
                    mMagicStrengthButton.setVisibility(View.VISIBLE);
                    mTotalPowerButton.setVisibility(View.VISIBLE);
                    mCurrentPowerButton.setVisibility(View.VISIBLE);
                    mTalentBonusButton.setVisibility(View.VISIBLE);
                }
                if (character.getCharClass() instanceof Specialist) {
                    mTalentBonusTitleView.setText(((Specialist) character.getCharClass()).getSpecialScoreId());
                    mTalentBonusButton.setText(Integer.toString(((Specialist) character.getCharClass()).getSpecialTalent()));

                    mTalentBonusTitleView.setVisibility(View.VISIBLE);
                    mTalentBonusButton.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    /**
     * Resets all of the components that could be changed by an attribute edit.
     */
    private void refreshStats() {
        mMightButton.setText(Integer.toString(mSheetPlayerCharacter.
                getScore(Score.MIGHT).getScore()));
        mSkillButton.setText(Integer.toString(mSheetPlayerCharacter.
                getScore(Score.SKILL).getScore()));
        mWitsButton.setText(Integer.toString(mSheetPlayerCharacter.
                getScore(Score.WITS).getScore()));
        mLuckButton.setText(Integer.toString(mSheetPlayerCharacter.
                getScore(Score.LUCK).getScore()));
        mWillButton.setText(Integer.toString(mSheetPlayerCharacter.
                getScore(Score.WILL).getScore()));
        mGraceButton.setText(Integer.toString(mSheetPlayerCharacter.
                getScore(Score.GRACE).getScore()));
        mInitiativeButton.setText(Integer.toString(mSheetPlayerCharacter.getInitiative()));
        mAPbutton.setText(Integer.toString(mSheetPlayerCharacter.getAthleticProwess()));
        mDEbutton.setText(Integer.toString(mSheetPlayerCharacter.getDangerEvasion()));
        mMFbutton.setText(Integer.toString(mSheetPlayerCharacter.getMysticFortitude()));
        mPVbutton.setText(Integer.toString(mSheetPlayerCharacter.getPhysicalVigor()));
        if(mSheetPlayerCharacter.getCurrentWeapon().getWeaponType() == R.string.melee) {
            mAttackButton.setText(Integer.toString(mSheetPlayerCharacter.getMeleeMod()));
        }
        else{
            mAttackButton.setText(Integer.toString(mSheetPlayerCharacter.getMissileMod()));
        }
    }
}
