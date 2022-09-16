package com.learntocode.springbootdemotutorial.actuator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Endpoint(id = "features")
public class FeatureEndpoint {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Feature{
        private boolean isEnabled;
    }

    private final Map<String, Feature> featureMap = new ConcurrentHashMap<String, Feature>();

    public FeatureEndpoint() {
        featureMap.put("Department", new Feature(true));
        featureMap.put("Student", new Feature(false));
        featureMap.put("Authentication", new Feature(false));
    }

    @ReadOperation
    public Map<String, Feature> features(){
        return featureMap;
    }

    @ReadOperation
    public Feature feature(@Selector String featureName){
        return featureMap.get(featureName);
    }
}
