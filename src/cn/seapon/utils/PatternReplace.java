package cn.seapon.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternReplace {

	public static String yesbean(String inputpath, String pat) throws IOException {
		return yesbean(inputpath, Pattern.compile(pat));
	}

	public static String yesbean(String inputpath, Pattern pattern) throws IOException {
		DirectoryStream<Path> entries = Files.newDirectoryStream(Paths.get(inputpath));
		String result = "";
		for (Path entry : entries) {
			File file = entry.toFile();
			if (file.isDirectory()) {
				Logger.getLogger("a").info("Scan path:" + file.getAbsolutePath());
				result += yesbean(file.getAbsolutePath(), pattern);
			} else {
				BufferedReader bfReader = null;
				Logger.getLogger("a").info("Scan file:" + file.getAbsolutePath());
				try {
					bfReader = new BufferedReader(new FileReader(file), 8192);
					String content = "";
					String line = null;
					while (null != (line = bfReader.readLine())) {
						content += line;
					}
					Logger.getLogger("a").info("content:" + content);
					Matcher matcher = pattern.matcher(content);
					while (matcher.find()) {
						int start = matcher.start();
						int end = matcher.end();
						String string = content.substring(start, end);
						Logger.getLogger("a").info("match file:" + string);
						result += string + System.lineSeparator();
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					bfReader.close();
				}
			}
		}
		return result;
	}

}
