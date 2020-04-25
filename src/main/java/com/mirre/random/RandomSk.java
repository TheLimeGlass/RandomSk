package com.mirre.random;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.mirre.random.lang.abstracts.Register;
import com.mirre.random.packets.listeners.SleepListener;
import com.mirre.random.utils.ClassUtility;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import ch.njol.skript.util.FileUtils;

public class RandomSk extends JavaPlugin implements Listener {

	private ProtocolManager protocolManager;
	public final String VERSION = "2.7";
	public boolean JAVA_SCRIPT_ENABLED;
	private static RandomSk instance;
	private SkriptAddon addon;

	public void onEnable() {

		instance = this;
		this.addon = Skript.registerAddon(this).setLanguageFileDirectory("lang");
		this.protocolManager = ProtocolLibrary.getProtocolManager();

		// Listeners and Managers
		Bukkit.getPluginManager().registerEvents(new SleepListener(this), this);
		//new SimplePacketListener(this);

		// JavaScript engine
		File javaScriptsDirectory = new File(getDataFolder().getAbsolutePath() + "/javascripts/");
		File guide = new File(getDataFolder(), "javascripts/JavaScriptGuide.txt");
		if (!javaScriptsDirectory.exists())
			javaScriptsDirectory.mkdirs();
		if (!guide.exists()) {
			try {
				ZipFile zip = new ZipFile(getFile());
				ZipEntry entry = zip.getEntry("javascripts/JavaScriptGuide.txt");
				FileUtils.save(zip.getInputStream(entry), guide);
				zip.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(guide));
				String[] lines = reader.lines().toArray(String[]::new);
				String lastLine = lines[lines.length - 1];
				if (lastLine.equalsIgnoreCase("Enable Javascript: true"))
					JAVA_SCRIPT_ENABLED = true;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		saveDefaultConfig();
		// Register
		try {
			registerFeatures();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SkriptAddon getAddonInstance() {
		return addon;
	}

	public static RandomSk getInstance() {
		return instance;
	}

	private void registerFeatures() throws Exception {
		List<String> loadedFeatures = new ArrayList<>();
		for (String info : ClassUtility.getClassNamesInPackage("com/mirre/random/elements/")) {
			String check = info.replace(".class", "");
			if (check.substring(check.length() - 2, check.length()).matches(String.valueOf(Pattern.quote("$")) + "[0-9]"))
				continue;
			loadedFeatures.add(info);
			info = info.replaceAll("/", ".");
			Class<?> c = Class.forName(info.substring(0, info.length() - 6));
			Object o = c.newInstance();
			if (!(o instanceof Register))
				continue;
			Register register = (Register)o;
			register.register();
		}
		Bukkit.getLogger().info("[RandomSK] A total of " + loadedFeatures.size() + " classes have been loaded!");
	}

	public ProtocolManager getProtocolManager() {
		return protocolManager;
	}

}
