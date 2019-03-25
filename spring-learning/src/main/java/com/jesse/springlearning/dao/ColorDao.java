package com.jesse.springlearning.dao;


import org.springframework.stereotype.Repository;

@Repository
public class ColorDao {
    private String label = "1";

    public ColorDao(String label) {
        this.label = label;
    }

    public ColorDao() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "ColorDao{" +
                "label='" + label + '\'' +
                '}';
    }
}
