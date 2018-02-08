// Requires gson-2.2.4.jar or later

package daeuiot.tsacharactercreator;

import com.google.gson.Gson;
import java.util.*;
import java.lang.reflect.*;
import com.google.gson.*;
import com.google.gson.reflect.*;
import com.google.gson.JsonParser;

public class Helper
{
	public static <T> String getJSON(T obj)
	{
		Gson g = new Gson();
		return g.toJson(obj);
	}

	public static <T> T getObject(String json, Class<T> theClass)
	{
		Gson g = new Gson();
		T obj = g.fromJson(json, theClass);
		return obj;
	}

	public static <T> String getJSONList(List<T> list, Class<T> theClass)
	{
		Gson g = new Gson();

		Type listType = new TypeToken<List<T>>() {}.getType();
		String s = g.toJson(list, listType);
		return s;
	}

 	public static <T> List<T> getObjectList(String json, Class<T> theClass)
 	{
        Gson gson = new Gson();

        //	Split JSON string into a JsonArray of elements
        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(json).getAsJsonArray();

		  // Convert each JSON element into an object and add to the list
        List<T> entityList = new ArrayList<T>();
        for(JsonElement jsonElement: array)
        {
            T entity = gson.fromJson(jsonElement, theClass);
            entityList.add(entity);
        }
        return entityList;
    }
 }

