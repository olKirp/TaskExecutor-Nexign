package com.example.taskexecutor.utils;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MapperUtils {

    private final static Logger logger = LoggerFactory.getLogger(MapperUtils.class);

    private static ModelMapper mapper = new ModelMapper();

    public static <T> T mapObject(Object source, Class<T> type) {
        try {
            return mapper.map(source, type);
        } catch (Exception e) {
            logger.error("Exception during mapping object to type {}", type, e);
            return null;
        }
    }

    public static <T> List<T> mapList(List<?> list, Class<T> type) {
        return list.stream().map(log -> MapperUtils.mapObject(log, type)).toList();
    }
}
