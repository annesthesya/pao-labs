package com.company.assignments;

import java.util.Comparator;

public class WorkComparator implements Comparator<Work> {
    @Override
    public int compare(Work a, Work b){
        return a.getTurnInTime().compareTo(b.getTurnInTime());
    }
}
