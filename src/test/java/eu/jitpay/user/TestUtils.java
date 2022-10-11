package eu.jitpay.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

import java.util.Map;

public class TestUtils {

    public static Object convertJsonToObject(String content) {
        ObjectMapper mapper = new ObjectMapper();
        JsonParser jsonParser = JsonParserFactory.getJsonParser();
        Map<String, Object> map = jsonParser.parseMap(content);
        return mapper.convertValue(map.get("message"), Object.class);

    }
}
