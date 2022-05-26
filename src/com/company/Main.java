package com.company;

import src.com.company.services.Service;

import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws ParseException, InterruptedException, IllegalAccessException {
        Service service = Service.createInstance();
    }

}
