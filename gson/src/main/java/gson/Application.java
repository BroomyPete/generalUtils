package gson;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class Application {

	public static void main(String[] args) {

		String compactJson = "{\"id\": \"pb1\", \"objectType\": \"User\", \"User.password\": \"so\","
				+ "\"User.Location.Preference\": {\"locations\": \"xml\"},\"User.Source\": \"pb1\"}";
		String prettyJson = toPrettyFormat(compactJson);
		System.out.println("Pretty Print:\n" + prettyJson);

		String str = getValueFromJson(compactJson);
		System.out.println("\n" + "key" + " = " + str);
	}

	/**
	 * Returns the matching value for a key. Note: Does not work with JSON Arrays.
	 * @param jsonString String to search in.
	 * @param key Key to use to retrieve value.
	 * @return Value if found, otherwise empty string.
	 */
	public static String getValueFromJson(String jsonString) {
		try {
			return new JsonParser().parse(jsonString).getAsJsonObject()
					.get("User.Location.Preference").getAsJsonObject()
					.get("locations").getAsString();	
		} catch (NullPointerException e) {
			return "Unable to return value from JSON. Invalid key provided.";
		} catch (JsonSyntaxException e) {
			return "String is not valid JSON.";
		}
	}

	/**
	 * Converts a JSON string to pretty print version.
	 * @param jsonString String to convert.
	 * @return Formatted string if Json, otherwise empty string.
	 */
	public static String toPrettyFormat(String jsonString) {
		try {
			String prettyJson = new GsonBuilder().setPrettyPrinting().create()
					.toJson(new JsonParser().parse(jsonString).getAsJsonObject());
			return prettyJson;
		} catch (JsonSyntaxException ex) {
			return "String provided is not valid JSON.";
		}
	}
}
