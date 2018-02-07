package com.example.cis.mazeminotaurs.character.classes;

import com.example.cis.mazeminotaurs.Equipment;
import com.example.cis.mazeminotaurs.EquipmentDB;
import com.example.cis.mazeminotaurs.R;
import com.example.cis.mazeminotaurs.Weapon;
import com.example.cis.mazeminotaurs.character.Gender;
import com.example.cis.mazeminotaurs.character.PlayerCharacter;
import com.example.cis.mazeminotaurs.character.stats.Score;
import com.example.cis.mazeminotaurs.rollDice.rollDice;
import com.example.cis.mazeminotaurs.util.CommonStrings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents the Thief that is in the game.
 *
 * @author jsmith on 9/11/17.
 */

public class Thief extends Specialist {

    /**
     * Blank constructor. Used primarily for reflection purposes.
     * <b>DO NOT USE THIS FOR UI DISPLAYS.</b>
     */
    public Thief() {
        this(null, null);
    }

    /**
     * Constructor that requires a {@link PlayerCharacter} instance and a weapon.
     * <p>If weaponOfChoice is invalid, it will automatically assign it an bow.</p>
     *
     * @param playerCharacter the character using this class
     * @param weaponOfChoice  the user desired weapon of choice
     */
    public Thief(PlayerCharacter playerCharacter, Weapon weaponOfChoice) {
        setPossibleStartWeapons(new Weapon[]{});
        setPossibleWeaponsOfChoice(new Weapon[]{
                EquipmentDB.getInstance().getWeapon(CommonStrings.BOW.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.SLING.getValue()),
                EquipmentDB.getInstance().getWeapon(CommonStrings.THROW_KNIFE.getValue()),
        });

        // The scores that can be used for level ups.
        setPossibleLevelScores(new Score[]{Score.WILL, Score.WILL, Score.SKILL});

        Score[] primAttrs = {Score.LUCK, Score.WITS};
        List<Score> primAttributes = new ArrayList<>();
        Collections.addAll(primAttributes, primAttrs);

        // Setting up for equipment check
        EquipmentDB equipmentDB = EquipmentDB.getInstance();
        List<Weapon> possibleWepsOfChoice = new ArrayList<>();
        List<Equipment> startGear = new ArrayList<>();

        for (String choiceId : new String[]{CommonStrings.DAGGER.getValue(), CommonStrings.SLING.getValue(), CommonStrings.THROW_KNIFE.getValue()}) {
            possibleWepsOfChoice.add(equipmentDB.getWeapon(choiceId));
        }

        if (possibleWepsOfChoice.contains(weaponOfChoice)) {
            setWeaponOfChoice(weaponOfChoice);
        } else {
            setWeaponOfChoice(possibleWepsOfChoice.get(0));
        }

        startGear.add(equipmentDB.getWeapon(CommonStrings.DAGGER.getValue()));
        // Equipment done

        int rolledGold = rollDice.roll(6, 3) * 5;

        setBasicHits(10);
        setCharacter(playerCharacter);
        setPrimaryAttributes(primAttributes);
        setResId(Classes.THIEF.getResId());
        setRequiredGender(Gender.EITHER);
        setSpecialScoreId(R.string.thief_talent);
        setStartMoney(rolledGold);
        setStartGear(startGear);
    }

    /**
     * The addition made to the EDC of the character, unless surprised or wearing a
     * breastplate. Only applies to melee attacks.
     *
     * @return Character's modifier of their Wits score.
     */
    public int getEvasionBonus() {
        return getCharacter().getScore(Score.WITS).getScore();
    }
}
