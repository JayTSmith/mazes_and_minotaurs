package com.example.cis.mazeminotaurs.util;

/**
 * An Enumeration of default equipment values.
 * Also, contains the arguments used for keys in bundle arguments.
 * @author jsmith on 10/25/17.
 */

public enum CommonStrings {
    BOEOTIAN("Boeotian Helmet"),
    BREASTPLATE("Breastplate"),
    HELMET("Helmet"),
    LINOTHORAX("Linothorax"),
    PELTAST("Peltast Shield"),
    SHIELD("Shield"),
    AXE("Axe"),
    BARB_AXE("Barbarian Axe"),
    BARB_CLUB("Barbarian Cudgel"),
    BARB_MACE("Barbarian Mace"),
    BARB_SWORD("Barbarian Sword"),
    CLUB("Club"),
    DAGGER("Dagger"),
    MACE("Mace"),
    SPEAR("Spear"),
    SWORD("Sword"),
    ARROWS("Quiver of Arrows"),
    BOW("Bow"),
    JAVELIN("Javelin"),
    SLING("Sling"),
    SLINGSHOT("Sling Shot"),
    THROW_KNIFE("Throwing Knife"),
    ROWBOAT("Rowing Boat"),
    SMALL_SAIL("Small Sailing Ship"),
    MERCHANT_SHIP("Merchant Ship"),
    WAR_GALLEY("War Galley"),
    HORSE("Horse"),
    WARHORSE("War Horse"),
    MULE("Mule"),
    STAFF("Staff"),
    OIL_FLASK("Flask of Oil"),
    TORCH("Torch"),
    FLINT_AND_TINDER("Flint & Tinder"),
    ROPE("Rope (30 ft)"),
    BEDROLL("Bedroll"),
    RATIONS("Day\'s Rations"),
    WATERSKIN("Waterskin"),
    LODGING("Night\'s Lodging"),
    MEAL("One Meal (with Wine)"),
    WINE_JUG("Jug of Wine"),

    // Bundle keys
    ATTR_PRIORITY_ARGS("ATTR_PRIORITY_ARGS"),
    CHARACTER_ARG("CHARACTER_ARG"),
    EQUIPMENT_KEY("EQUIPMENT_ARG"),
    EQUIPMENT_LIST_KEY("EQUIPMENT_LIST_ARG"),
    SCORE_ARG("SCORE_ARG");

    /**
     * The string value of the enum's value name.
     */
    String value;

    /**
     * Default constructor.
     *
     * @param value Arbitrary value.
     */
    CommonStrings(String value) {
        this.value = value;
    }

    /**
     * The string value of the enum.
     * @return a string.
     */
    public String getValue() {
        return value;
    }
}
