package com.mirre.random.utils.enums;

import org.bukkit.block.CreatureSpawner;
import org.bukkit.block.Furnace;
import org.bukkit.block.BrewingStand;
import org.bukkit.Material;
import org.bukkit.block.Block;

public enum BlockFeature {

	BREWINGTIME(0, 0) {
		@Override
		public void set(Block block, final Number value) {
			if (block.getType() != Material.BREWING_STAND) {
				return;
			}
			final BrewingStand stand = (BrewingStand)block.getState();
			stand.setBrewingTime(value.intValue());
			stand.update(true);
		}
		
		@Override
		public Number get(Block block) {
			if (block.getType() != Material.BREWING_STAND) {
				return null;
			}
			final BrewingStand stand = (BrewingStand)block.getState();
			return stand.getBrewingTime();
		}
	}, 
	BURNTIME(1, 1) {
		@SuppressWarnings("deprecation")
		@Override
		public void set(Block block, final Number value) {
			if (block.getType() != Material.FURNACE && block.getType() != Material.LEGACY_BURNING_FURNACE) {
				return;
			}
			final Furnace furnace = (Furnace)block.getState();
			furnace.setBurnTime(value.shortValue());
			furnace.update(true);
		}
		
		@SuppressWarnings("deprecation")
		@Override
		public Number get(Block block) {
			if (block.getType() != Material.FURNACE && block.getType() != Material.LEGACY_BURNING_FURNACE) {
				return null;
			}
			final Furnace furnace = (Furnace)block.getState();
			return furnace.getBurnTime();
		}
	}, 
	COOKTIME(2, 2) {
		@SuppressWarnings("deprecation")
		@Override
		public void set(Block block, final Number value) {
			if (block.getType() != Material.FURNACE && block.getType() != Material.LEGACY_BURNING_FURNACE) {
				return;
			}
			final Furnace furnace = (Furnace)block.getState();
			furnace.setCookTime(value.shortValue());
			furnace.update(true);
		}
		
		@SuppressWarnings("deprecation")
		@Override
		public Number get(Block block) {
			if (block.getType() != Material.FURNACE && block.getType() != Material.LEGACY_BURNING_FURNACE) {
				return null;
			}
			final Furnace furnace = (Furnace)block.getState();
			return furnace.getCookTime();
		}
	}, 
	SPAWNDELAY(3, 3) {
		@Override
		public void set(Block block, final Number value) {
			if (block.getType() != Material.SPAWNER) {
				return;
			}
			final CreatureSpawner spawner = (CreatureSpawner)block.getState();
			spawner.setDelay(value.intValue());
			spawner.update(true);
		}
		
		@Override
		public Number get(Block block) {
			if (block.getType() != Material.SPAWNER) {
				return null;
			}
			final CreatureSpawner spawner = (CreatureSpawner)block.getState();
			return spawner.getDelay();
		}
	};
	
	private int parseMark;
	
	private BlockFeature(final int ordinal, final int mark) {
		this.parseMark = mark;
	}
	
	public abstract void set(Block p0, final Number p1);
	
	public abstract Number get(Block p0);
	
	public static BlockFeature getBlockFeature(int mark) {
		BlockFeature[] values;
		for (int length = (values = values()).length, i = 0; i < length; ++i) {
			final BlockFeature bf = values[i];
			if (mark == bf.getMark()) {
				return bf;
			}
		}
		return null;
	}
	
	public int getMark() {
		return this.parseMark;
	}

}
