package com.howtodoinjava.jerseydemo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleMapsTrafficInfo {

    private Route[] routes;

    public Route[] getRoutes() {
        return routes;
    }

    public void setRoutes(Route[] routes) {
        this.routes = routes;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Route {
        private Leg[] legs;

        public Leg[] getLegs() {
            return legs;
        }

        public void setLegs(Leg[] legs) {
            this.legs = legs;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Leg {
        private DurationInTraffic duration_in_traffic;

        public DurationInTraffic getDurationInTraffic() {
            return duration_in_traffic;
        }

        public void setDurationInTraffic(DurationInTraffic duration_in_traffic) {
            this.duration_in_traffic = duration_in_traffic;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DurationInTraffic {
        private String text;
        private int value;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}