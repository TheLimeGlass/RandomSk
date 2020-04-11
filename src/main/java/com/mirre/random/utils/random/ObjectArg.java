package com.mirre.random.utils.random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectArg {

	private Number index;
	private Object object;
	
	public ObjectArg(Object o, Number index) {
		this.setIndex(index);
		this.setObject(o);
	}
	
	public Number getIndex() {
		return this.index;
	}
	
	public void setIndex(Number i) {
		this.index = i;
	}
	
	public Object getObject() {
		return this.object;
	}
	
	public void setObject(Object object) {
		this.object = object;
	}
	
	public static List<Object> toList(ObjectArg... args) {
		final List<Object> list = new ArrayList<>();
		for (ObjectArg o : args) {
			list.add(o.getIndex().intValue(), o.getObject());
		}
		return list;
	}
	
	public List<Object> toList(ObjectArg[] listargs, ObjectArg... args) {
		List<Object> list = new ArrayList<>();
		for (ObjectArg o : listargs) {
			list.set(o.getIndex().intValue(), o.getObject());
		}
		for (ObjectArg o : args) {
			list.set(o.getIndex().intValue(), o.getObject());
		}
		return list;
	}
	
	public static List<ObjectArg> sort(List<ObjectArg> list) {
		List<Number> nlist = new ArrayList<>();
		for (ObjectArg ob : list) {
			nlist.add(ob.getIndex().intValue());
		}
		Integer[] ilist = nlist.toArray(new Integer[nlist.size()]);
		Arrays.sort(ilist);
		List<ObjectArg> finallist = new ArrayList<>();
		Integer[] array;
		for (int length = (array = ilist).length, j = 0; j < length; ++j) {
			Integer integer = array[j];
			for (int i = 0; i < list.size(); ++i) {
				if (integer == list.get(i).getIndex().intValue() && !finallist.contains(list.get(i))) {
					finallist.add(list.get(i));
				}
			}
		}
		return finallist;
	}

}
