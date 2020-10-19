package com.hr_system;

public class AgeGroupStatistics {
    final String ageGroup;
    final Integer count;

    AgeGroupStatistics(String ageGroup, Integer count) {
        this.ageGroup = ageGroup;
        this.count = count;
    }
    public String getAgeGroup() {
        return ageGroup;
    }
    public Integer getCount() {
        return count;
    }
}
