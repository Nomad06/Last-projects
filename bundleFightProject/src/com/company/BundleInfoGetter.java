package com.company;

import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.lang.*;

public class BundleInfoGetter {

    private final String REGEXP = "\\s*\\{\\s*\"([A-Za-z0-9]*)\",\\s*\"(.*)\"},\\s*([/]{2}\\s*(.*))*";

    private ArrayList<BundleInfoHolder> bundleList = new ArrayList<>();

    public ArrayList<BundleInfoHolder> codeParsing(BufferedReader reader) throws IOException {

        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = reader.readLine()) != null){
            checkForConcurrence(line);
        }
        System.out.println("Даров");

        return bundleList;
    }

    public void checkForConcurrence(String line){
        Pattern pattern = Pattern.compile(REGEXP);
        Matcher matcher = pattern.matcher(line);
        String key, value, comment;
        if (matcher.find()){
            key = matcher.group(1);
            value = matcher.group(2);
            comment = matcher.group(4);
            bundleList.add(new BundleInfoHolder(key, value, comment));
        }
    }

}
