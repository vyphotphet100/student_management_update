package com.window_programming_api.utils;

import java.io.*;
import java.util.Base64;

public class MyFileUtil {
	
	public static String removeDoubleSlash(String str) {
		while (str.contains("//"))
			str = str.replace("//", "/");
		return str;
	}

	@SuppressWarnings("resource")
	public static byte[] getByteBySource(String source) {
		try {
			source = MyFileUtil.removeDoubleSlash(source);
			InputStream stream = new FileInputStream(source);
			if (stream != InputStream.nullInputStream()) {
				byte[] res = stream.readAllBytes();
				stream.close();
				return res;
			}
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String upFile(String fileNameWithFolder, String base64) {
		byte[] data = Base64.getDecoder().decode(base64);

		// Check if dir is not exist
		StringBuilder fileName = new StringBuilder("");
		StringBuilder rootPath = new StringBuilder("");

		Boolean check = false;
		for (int i = fileNameWithFolder.length() - 1; i >= 0; i--) {
			if (fileNameWithFolder.charAt(i) != '/' && !check)
				fileName.append(fileNameWithFolder.charAt(i));
			else
				check = true;
			if (check)
				rootPath.append(fileNameWithFolder.charAt(i));
		}
		fileName = fileName.reverse();
		rootPath = rootPath.reverse();
		rootPath = new StringBuilder(MyFileUtil.removeDoubleSlash("src/main/resources/sources/" + rootPath.toString()));
		File uploadRootDir = new File(rootPath.toString());
		if (!uploadRootDir.exists())
			uploadRootDir.mkdirs();

		// try to write file to drive
		try (OutputStream stream = new FileOutputStream(
				uploadRootDir.getAbsolutePath() + "/" + fileName)) {
			stream.write(data);
			stream.close();

			// Get fileLink return
			StringBuilder linkRoot = new StringBuilder(rootPath.toString().replace("src/main/resources/sources/", ""));
			String fileLink = "/api/file/" + linkRoot + fileName;
			fileLink = MyFileUtil.removeDoubleSlash(fileLink);

			return fileLink;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}
}
