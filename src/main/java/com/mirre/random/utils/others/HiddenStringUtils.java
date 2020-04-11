package com.mirre.random.utils.others;

import java.nio.charset.Charset;
import org.bukkit.ChatColor;

public class HiddenStringUtils {

	private static final String SEQUENCE_HEADER;
	private static final String SEQUENCE_FOOTER;
	
	static {
		SEQUENCE_HEADER = new StringBuilder().append(ChatColor.RESET).append(ChatColor.STRIKETHROUGH).append(ChatColor.RESET).toString();
		SEQUENCE_FOOTER = new StringBuilder().append(ChatColor.RESET).append(ChatColor.UNDERLINE).append(ChatColor.RESET).toString();
	}

	public static String encodeString(String hiddenString) {
		return quote(stringToColors(hiddenString));
	}

	public static boolean hasHiddenString(String input) {
		return input != null && (input.indexOf(HiddenStringUtils.SEQUENCE_HEADER) > -1 && input.indexOf(HiddenStringUtils.SEQUENCE_FOOTER) > -1);
	}

	public static String extractHiddenString(String input) {
		return colorsToString(extract(input));
	}

	public static String replaceHiddenString(String input, String hiddenString) {
		if (input == null)
			return null;
		int start = input.indexOf(HiddenStringUtils.SEQUENCE_HEADER);
		int end = input.indexOf(HiddenStringUtils.SEQUENCE_FOOTER);
		if (start < 0 || end < 0)
			return null;
		return input.substring(0, start + HiddenStringUtils.SEQUENCE_HEADER.length()) + stringToColors(hiddenString) + input.substring(end, input.length());
	}

	private static String quote(String input) {
		if (input == null)
			return null;
		return HiddenStringUtils.SEQUENCE_HEADER + input + HiddenStringUtils.SEQUENCE_FOOTER;
	}

	private static String extract(String input) {
		if (input == null)
			return null;
		int start = input.indexOf(HiddenStringUtils.SEQUENCE_HEADER);
		int end = input.indexOf(HiddenStringUtils.SEQUENCE_FOOTER);
		if (start < 0 || end < 0)
			return null;
		return input.substring(start + HiddenStringUtils.SEQUENCE_HEADER.length(), end);
	}

	private static String stringToColors(String normal) {
		if (normal == null)
			return null;
		byte[] bytes = normal.getBytes(Charset.forName("UTF-8"));
		char[] chars = new char[bytes.length * 4];
		for (int i = 0; i < bytes.length; ++i) {
			char[] hex = byteToHex(bytes[i]);
			chars[i * 4] = 'ง';
			chars[i * 4 + 1] = hex[0];
			chars[i * 4 + 2] = 'ง';
			chars[i * 4 + 3] = hex[1];
		}
		return new String(chars);
	}

	private static String colorsToString(String colors) {
		if (colors == null)
			return null;
		colors = colors.toLowerCase().replace("ยง", "");
		if (colors.length() % 2 != 0) {
			colors = colors.substring(0, colors.length() / 2 * 2);
		}
		char[] chars = colors.toCharArray();
		byte[] bytes = new byte[chars.length / 2];
		for (int i = 0; i < chars.length; i += 2) {
			bytes[i / 2] = hexToByte(chars[i], chars[i + 1]);
		}
		return new String(bytes, Charset.forName("UTF-8"));
	}

	private static int hexToUnsignedInt(char c) {
		if (c >= '0' && c <= '9') {
			return c - '0';
		}
		if (c >= 'a' && c <= 'f') {
			return c - 'W';
		}
		throw new IllegalArgumentException("Invalid hex char: out of range");
	}

	private static char unsignedIntToHex(int i) {
		if (i >= 0 && i <= 9) {
			return (char)(i + 48);
		}
		if (i >= 10 && i <= 15) {
			return (char)(i + 87);
		}
		throw new IllegalArgumentException("Invalid hex int: out of range");
	}

	private static byte hexToByte(char hex1, char hex0) {
		return (byte)((hexToUnsignedInt(hex1) << 4 | hexToUnsignedInt(hex0)) - 128);
	}

	private static char[] byteToHex(byte b) {
		int unsignedByte = b + 128;
		return new char[] { unsignedIntToHex(unsignedByte >> 4 & 0xF), unsignedIntToHex(unsignedByte & 0xF) };
	}

}
