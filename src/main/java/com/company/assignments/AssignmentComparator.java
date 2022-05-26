package com.company.assignments;

import java.util.Comparator;

public class AssignmentComparator implements Comparator<Assignment> {
    @Override
    public int compare(Assignment a, Assignment b){
        return a.getDeadline().compareTo(b.getDeadline());
    }
}
