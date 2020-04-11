package com.mirre.random.elements.conditions;

import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Bed;

import com.mirre.random.lang.RandomPropertyCondition;
import com.mirre.random.lang.annonations.Pattern;

@Pattern(patterns = {"[bed] %block% (is|are) occupied", "{!}[bed] %block% (is|are)(n't| not) occupied"})
public class CondBedIsOccupied extends RandomPropertyCondition<Block> {

	@Override
	public boolean inspect(Block from) {
		BlockData data = from.getBlockData();
		if (!(data instanceof Bed))
			return false;
		return ((Bed)data).isOccupied();
	}

}
