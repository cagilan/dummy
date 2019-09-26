package application;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JSONMapperUtil {


    public static Map<String, Object> sizesMap=new HashMap<>();
    public static Map<String, Object> materialPriceMap=new HashMap<>();
    public static Map<String, Object> drillingPriceMap=new HashMap<>();
    public static Map<String, Object> finishingPriceMap=new HashMap<>();


    public static void loadProductPriceDetails() throws IOException, URISyntaxException {
        Path path = Paths.get(JSONMapperUtil.class.getClassLoader().getResource("prices.json").toURI());
        Stream<String> lines = Files.lines(path);
        String data = lines.collect(Collectors.joining("\n"));
        lines.close();
        sizesMap=jsonToMap(new JSONObject(data).getJSONObject("sizes"));
        materialPriceMap=jsonToMap(new JSONObject(data).getJSONObject("prices").getJSONObject("material"));
        drillingPriceMap=jsonToMap(new JSONObject(data).getJSONObject("prices").getJSONObject("drilling_holes"));
        finishingPriceMap=jsonToMap(new JSONObject(data).getJSONObject("prices").getJSONObject("finish"));
    }


    public static Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
        Map<String, Object> retMap = new HashMap<>();

        if(json != JSONObject.NULL) {
            retMap = toMap(json);
        }
        return retMap;
    }

    public  static Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();

        Iterator<String> keysItr = object.keys();
        while(keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if(value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

    public static List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<>();
        for(int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if(value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }
}
