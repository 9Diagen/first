package diag.playmine.first.items;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;

public interface Boostable extends KeyItem {

    void applyBoost(PlayerArmorChangeEvent e);

    void clearBoost(PlayerArmorChangeEvent e);

}
