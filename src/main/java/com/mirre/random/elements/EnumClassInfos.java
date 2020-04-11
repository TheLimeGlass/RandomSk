package com.mirre.random.elements;

import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.World;
import org.bukkit.event.player.PlayerFishEvent;
import org.eclipse.jdt.annotation.Nullable;

import com.comphenix.protocol.wrappers.EnumWrappers.ResourcePackStatus;
import com.mirre.random.lang.abstracts.Register;
import com.mirre.random.lang.parser.EnumClassInfo;
import com.mirre.random.utils.enums.Age;
import com.mirre.random.utils.enums.Animation;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.util.coll.CollectionUtils;

public class EnumClassInfos implements Register {

	@Override
	public void register() {
		EnumClassInfo.create(PlayerFishEvent.State.class, "fishingstate").register();
		EnumClassInfo.create(ResourcePackStatus.class, "resourcestatus").register();
		EnumClassInfo.create(World.Environment.class, "environment").register();
		EnumClassInfo.create(Animation.class, "animation").register();
		EnumClassInfo.create(Statistic.class, "statistic").register();
		EnumClassInfo.create(Age.class, "age").register();

		EnumClassInfo.create(Sound.class, "sound")
				.defaultExpression(new EventValueExpression<Sound>(Sound.class, new Changer<Sound>() {
					@Override
					@Nullable
					public Class<?>[] acceptChange(ChangeMode mode) {
						if (mode == ChangeMode.SET)
							return CollectionUtils.array(Sound.class);
						return null;
					}

					@Override
					public void change(Sound[] sounds, @Nullable Object[] delta, ChangeMode mode) {
						sounds = new Sound[] {(Sound)delta[0]};
					}
				}))
				.register();
	}

}
