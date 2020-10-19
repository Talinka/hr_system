package com.hr_system;

public class AverageAgeStatistics {
    private final String position;
    private  final Long count;
    private final Integer averageAge;

    public AverageAgeStatistics(String position, Long count, Double preciseAverageAge) {
        this.position = position;
        this.count = count;
        this.averageAge = Math.toIntExact(Math.round(preciseAverageAge));
    }

    public String getPosition() {
        return position;
    }

    public  Long getCount() {
        return count;
    }

    public  Integer getAverageAge() {
        return averageAge;
    }
}

