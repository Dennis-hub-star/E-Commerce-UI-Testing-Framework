package common;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import ui.utils.UiUtilities;

public class Utilities {

	public static Object[][] getJsonDataFromFile(String filePath) throws IOException {
		String path = System.getProperty("user.dir")
				+ filePath;
		List<HashMap<String, String>> data = UiUtilities.getJsonDataToMap(path);
		Object[][] prepared = new Object[data.size()][1];
		for (int i = 0; i < data.size(); i++) {
			prepared[i][0] = data.get(i);
		}
		return prepared;
	}
	
	public static String getGlobalValue(String key, String fileName) {
	    return ResourceBundle.getBundle(fileName).getString(key);
	}

}
