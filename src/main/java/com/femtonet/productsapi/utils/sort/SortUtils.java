package com.femtonet.productsapi.utils.sort;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.femtonet.productsapi.payload.ProductResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class SortUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(SortUtils.class);

    public static void setWeightedSum(ProductResponse product, Map<String, String> criterias) {
        LOGGER.info("---- setWeightedSum() - Starting... ----");
        LOGGER.info("setWeightedSum() - Product ID: {}, Criterias: {}", product.getId(), criterias);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.convertValue(product, Map.class);

        try {
            int sum = 0;
            for (String key : criterias.keySet()) {
                Integer criteriaValue = 0;
                if (map.get(key) instanceof Integer) {
                    criteriaValue = (Integer) map.get(key);
                } else if ("stock".equals(key)) {
                    Map stockMap = (HashMap) map.get(key);
                    criteriaValue = (Integer) stockMap.get("l") + (Integer) stockMap.get("m") + (Integer) stockMap.get("s");
                }

                sum = sum + Integer.parseInt(criterias.get(key)) * criteriaValue; // criterias.get(key) -> criteria weight
            }

            LOGGER.info("setWeightedSum() - sum: {}", sum);
            product.setSortPoint(sum);
        } catch (Exception e) {
            LOGGER.error("setWeightedSum() - Error found: {}", e.getMessage());
        }
        LOGGER.info("setWeightedSum() - End.");
    }


}
