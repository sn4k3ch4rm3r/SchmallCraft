package schmallcraft.items;

import java.util.List;

import schmallcraft.game.objects.GameObject;
import schmallcraft.game.objects.entities.Player;

public interface UsableItem {
	default public List<GameObject> use(Player player, GameObject target) {
		return null;
	}
}
