package com.wes.webflux.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.var;

public class Utils {

	private Utils() {
	}

	private static Logger logger = Logger.getLogger(Utils.class.getName());

	public static String mapObjectToJson(Object item) {
		try {
			ObjectMapper objMap = new ObjectMapper();
			return objMap.writeValueAsString(item);
		} catch (JsonProcessingException e) {
			logger.info(String.format("%s", e));
			return null;
		}
	}

	public static List<String> intoList(List<String> list, Object obj) {
		if (Objects.isNull(list)) {
			list = new ArrayList<>();
		}
		list.add(mapObjectToJson(obj));
		return list;
	}

	public static String stringToGetParam(String str) {
		return "/{" + str + "}";
	}

	public static Long longValue(Object o) {
		if (Objects.isNull(o))
			return null;
		if (o instanceof Long) {
			return (Long) o;
		}
		if (o instanceof Integer) {
			Integer i = (Integer) o;
			return i.longValue();
		}
		var s = o.toString();
		return Long.valueOf(s);
	}

	public static Integer integerValue(Object o) {
		if (Objects.isNull(o))
			return null;
		if (o instanceof Integer) {
			return (Integer) o;
		}
		if (o instanceof Integer) {
			Integer i = (Integer) o;
			return i.intValue();
		}
		var s = o.toString();
		return Integer.valueOf(s);
	}

	public static String stringValue(Object o) {
		return Objects.isNull(o) ? null : o.toString();
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> mapValue(Object o) {
		return (Map<String, Object>) o;
	}

	public static Long truncateNumber(Object number) {
		return longValue(String.valueOf(number).substring(0, 2));
	}

}
