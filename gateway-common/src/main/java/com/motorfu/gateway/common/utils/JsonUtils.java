package com.motorfu.gateway.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * json处理类
 *
 * @author motorfu
 */
public class JsonUtils {
  private static final Logger LOGGER =  LoggerFactory.getLogger(JsonUtils.class);

  private static final ObjectMapper MAPPER = new ObjectMapper();
  private static final ObjectMapper PRETTY_MAPPER = new ObjectMapper();

  static {
//    SimpleModule module = new SimpleModule();
//    module.addSerializer(Date.class, new JsonDateForLongSerializer());
//    MAPPER.registerModule(module);
    MAPPER.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
    MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
    MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
    MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    MAPPER.setFilterProvider(new SimpleFilterProvider().addFilter("ignore", SimpleBeanPropertyFilter.serializeAllExcept("")));

    PRETTY_MAPPER.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
    PRETTY_MAPPER.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
    PRETTY_MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    PRETTY_MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
    PRETTY_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    PRETTY_MAPPER.configure(SerializationFeature.INDENT_OUTPUT, true);
    PRETTY_MAPPER.setSerializationInclusion(JsonInclude.Include.USE_DEFAULTS);
    PRETTY_MAPPER.setFilterProvider(new SimpleFilterProvider().addFilter("ignore", SimpleBeanPropertyFilter.serializeAllExcept("")));

  }

  private JsonUtils() {
  }

  public static ObjectMapper getMapper() {
    return MAPPER;
  }

  public static String encode(Object obj) {
    try {
      if (StringUtils.isEmpty(obj)) return null;
      return MAPPER.writeValueAsString(obj);
    } catch (Exception e) {
      LOGGER.error("转换json字符失败!obj={}, error={}", obj, e.getMessage(), e);
    }
    return null;
  }

  public static String encode(Object obj, String defaultValue) {
    try {
      String result = encode(obj);
      if (StringUtils.isEmpty(result)) {
        return defaultValue;
      }
      return result;
    } catch (Exception e) {
      return defaultValue;
    }
  }

  public static Map<String, Object> encodeMap(Object obj) {
    try {
      if (StringUtils.isEmpty(obj)) return null;
      return MAPPER.convertValue(obj, Map.class);
    } catch (Exception e) {
      return new HashMap();
    }
  }


  /**
   * jackson 解析成对象,json中属性必须与对象中的变量一致
   *
   * @param json
   * @param classze
   * @param <T>
   * @return
   */
  public static <T> T decode(String json, Class<T> classze) {
    try {
      if (StringUtils.isEmpty(json)) {
        return null;
      }
      return MAPPER.readValue(json, classze);
    } catch (Exception e) {
      LOGGER.error("json转换对象失败! json={}, error={}", json, e.getMessage(), e);

    }
    return null;
  }

  public static <T> T decode(String json, JavaType javaType) {
    try {
      if (StringUtils.isEmpty(json)) {
        return null;
      }
      return MAPPER.readValue(json, javaType);
    } catch (Exception e) {
      LOGGER.error("json转换对象失败! json={}, error={}", json, e.getMessage(), e);

    }
    return null;
  }

  public static <T> T decode(String json, TypeReference<T> type) {
    try {
      if (StringUtils.isEmpty(json)) {
        return null;
      }
      return MAPPER.readValue(json, type);
    } catch (Exception e) {
      LOGGER.error("json转换对象失败! json={}, error={}", json, e.getMessage(), e);

    }
    return null;
  }

  public static <T> T decode(Object obj, Class<T> classze) {
    try {
      if (obj == null) {
        return null;
      }
      return MAPPER.readValue(JsonUtils.encode(obj), classze);
    } catch (Exception e) {
      LOGGER.error("obj转换obj失败! json={}, error={}", obj, e.getMessage(), e);
    }
    return null;
  }

  public static <T ,S > T decode(String json, Class<T> class1, Class<S> class2) {
    try {
      if (StringUtils.isEmpty(json)) {
        return null;
      }
      JavaType type = TypeFactory.defaultInstance().constructParametricType(class1, class2);
      return decode(json, type);
    } catch (Exception e) {
      LOGGER.error("json转换对象失败! json={}, error={}", json, e.getMessage(), e);
      return null;
    }
  }

  public static <T> T decode(String json, Class<?> class1, Class<?> class2, Class<?> class3) {
    try {
      if (StringUtils.isEmpty(json)) {
        return null;
      }
      JavaType type1 = TypeFactory.defaultInstance().constructParametricType(class2, class3);

      JavaType type = TypeFactory.defaultInstance().constructParametricType(class1, type1);
      return decode(json, type);
    } catch (Exception e) {
      LOGGER.error("json转换对象失败! json={}, error={}", json, e.getMessage(), e);
      return null;
    }
  }

  public static <T> T decodeForDate(String json, Class<T> classze) {
    try {
      if (StringUtils.isEmpty(json)) {
        return null;
      }

      return MAPPER.readValue(json, classze);
    } catch (Exception e) {
      LOGGER.error("json转换对象失败! json={}, error={}", json, e.getMessage(), e);

    }
    return null;
  }

  public <T> T toObject(String json, Class<T> clazz) {
    try {
      if (StringUtils.isEmpty(json)) {
        return null;
      }
      return MAPPER.readValue(json, clazz);
    } catch (IOException e) {
      LOGGER.error("将json字符转换为对象时失败! json={}, error={}", json, e.getMessage(), e);

    }
    return null;
  }

  /**
   * 转化指定格式的Date
   */
  public static class JsonDateSerializer extends JsonSerializer<Date> {
    private final String format;

    public JsonDateSerializer(String format) {
      this.format = format;
    }

    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
        throws IOException {
      gen.writeString(new SimpleDateFormat(this.format).format(date));
    }
  }

  public static class JsonDateForLongSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
        throws IOException {
      gen.writeNumber(date.getTime());
    }
  }


  public static String encodePrettily(Object data) {
    String prettyJsonString = null;
    if (!StringUtils.isEmpty(data)) {
      try {
        prettyJsonString = PRETTY_MAPPER.writeValueAsString(data);
      } catch (Exception e) {
        LOGGER.error("data--->formateJson failed, data={}", encode(data));
        return null;
      }
    }
    return prettyJsonString;
  }

  public static String encodePrettily(Object data, String dateformat) {
    String prettyJsonString = null;
    if (!StringUtils.isEmpty(data)) {
      try {
        PRETTY_MAPPER.setDateFormat(new SimpleDateFormat(dateformat));
        prettyJsonString = PRETTY_MAPPER.writeValueAsString(data);
      } catch (Exception e) {
        LOGGER.error("data--->formateJson failed, data={}", encode(data));
        return null;
      }
    }
    return prettyJsonString;
  }

  public static String encode4Document(Object data, boolean isPrettily) {
    String prettyJsonString = null;
    if (!StringUtils.isEmpty(data)) {
      try {
        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
        filterProvider.addFilter("ignore", SimpleBeanPropertyFilter.serializeAllExcept("apiVersion", "serverVersion",
            "collectServerVersion", "collectId",
            "userId", "appId", "installationId", "channelId", "platform", "ignoreLocation"));
        if (isPrettily) {
          PRETTY_MAPPER.setFilterProvider(filterProvider);
          prettyJsonString = PRETTY_MAPPER.writeValueAsString(data);
        } else {
          MAPPER.setFilterProvider(filterProvider);
          prettyJsonString = MAPPER.writeValueAsString(data);
        }
      } catch (Exception e) {
        LOGGER.error("data--->formateJson failed, data={}", encode(data));
        return null;
      }
    }
    return prettyJsonString;
  }

}