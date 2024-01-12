package com.howtodoinjava.jerseydemo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.howtodoinjava.jerseydemo.Models.TrafficIncident;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MapQuestTrafficFlowResponse {

    private List<TrafficIncident> incidents;

    public List<TrafficIncident> getIncidents() {
        return incidents;
    }

    public void setIncidents(List<TrafficIncident> incidents) {
        this.incidents = incidents;
    }

    @Override
    public String toString() {
        return "MapQuestTrafficFlowResponse{" +
                "incidents=" + incidents +
                '}';
    }
}
