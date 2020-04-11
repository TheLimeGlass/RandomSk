package com.mirre.random.utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.bukkit.plugin.java.JavaPlugin;

import com.mirre.random.RandomSk;

public class ClassUtility {

	public static List<String> getClassNamesInPackage(String packageName) throws IOException {
		List<String> classes = new ArrayList<>();
		JarFile jar = getJar(RandomSk.getInstance());
		for (Enumeration<JarEntry> enumeration = jar.entries(); enumeration.hasMoreElements();) {
			JarEntry jarEntry = enumeration.nextElement();
			if (jarEntry.getName().startsWith(packageName) && jarEntry.getName().endsWith(".class")) {
				classes.add(jarEntry.getName());
			}
		}
		jar.close();
		return classes;
	}

	private static JarFile getJar(RandomSk instance) {
		try {
			Method method = JavaPlugin.class.getDeclaredMethod("getFile");
			method.setAccessible(true);
			File file = (File) method.invoke(RandomSk.getInstance());
			return new JarFile(file);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Type[] getTypeArgument(final Class<?> clazz) {
		return ((ParameterizedType)clazz.getGenericSuperclass()).getActualTypeArguments();
	}

}
