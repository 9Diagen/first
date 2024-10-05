package diag.playmine.first.menu.keys;

import diag.playmine.first.First;
import diag.playmine.first.events.itemInteract.Interactable;
import diag.playmine.first.items.Boostable;
import diag.playmine.first.items.armor.Armor;
import diag.playmine.first.items.weapons.Weapon;
import org.bukkit.NamespacedKey;

import java.util.HashSet;
import java.util.Set;

public class Keys {

    public Keys() {

    }

    public static NamespacedKey FEAR_SWORD_KEY = new NamespacedKey(First.instance, "fearSword");

    public static NamespacedKey MENU_KEY = new NamespacedKey(First.instance, "menu");

    public static NamespacedKey BOOTS_OF_SPEED_KEY = new NamespacedKey(First.instance, "bootsOfSpeed");

    public static final Set<Boostable> boosts = new HashSet<>();

    public static final Set<Interactable> interactables = new HashSet<>();

    public static final Set<Weapon> weapons = new HashSet<>();

    public static final Set<Armor> armors = new HashSet<>();

}
