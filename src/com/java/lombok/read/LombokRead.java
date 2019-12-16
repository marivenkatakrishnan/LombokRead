package com.java.lombok.read;

import java.util.List;
import java.util.Set;
import java.io.FileReader;
import java.util.ArrayList;
import org.apache.commons.configuration2.JSONConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.java.lombok.read.Application;

public class LombokRead {
	public static void main(String[] args) throws Exception {
		constructApplicationPayload(System.getProperty("user.dir").replace("\\", "/") + "/src/com/java/lombok/read/input.json".replace("\\", "/"), "LombokReadJson");
	}

	public static List<Application> constructApplicationPayload(String jsonpath, String... searchList) throws Exception {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(jsonpath));
		JSONConfiguration configs = new JSONConfiguration();
		List<Application> applications = new ArrayList<>();
		configs.read(new FileReader(jsonpath));

		@SuppressWarnings("unchecked")
		Set<Object> jsonkeySet = jsonObject.keySet();

		for (String searchKey : searchList) {

			for (Object key : jsonkeySet) {
				String keyStr = (String) key;

				if (searchKey.equalsIgnoreCase(keyStr)) {

					JSONObject testData = (JSONObject) jsonObject.get(keyStr);

					if (searchList[0] != "TestCreateApplicationWithSameOpcRetryTokenButDiffCompartmentId") {

						Application application = Application.builder()
								.displayName(configs.getString(keyStr + ".displayName"))
								.className(configs.getString(keyStr + ".className")).type("BATCH")
								.lang(configs.getString(keyStr + ".lang"))
								.type(configs.getString(keyStr + ".type"))
								//.applicationConfiguration(configs.getList(keyStr + ".configuration")).configuration(configList)
								.build();

						applications.add(application);
						System.out.println(application);
					}
				}
			}
		}
		return applications;
	}	
 }
