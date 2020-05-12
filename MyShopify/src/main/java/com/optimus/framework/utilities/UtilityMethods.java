package com.optimus.framework.utilities;

import java.io.File;

public class UtilityMethods {
	/**
	 * This method is used for getting name of child directory of specified
	 * directory
	 * 
	 * @param path
	 * 
	 *            - path of directory
	 * @return - child directory name
	 */
	public static String getChildDirectoryName(String path) {
		File file = new File(path);

		File[] files = file.listFiles();

		for (File f : files) {
			if (f.isDirectory()) {
				return f.getName();
			}
		}
		return null;
	}

}
