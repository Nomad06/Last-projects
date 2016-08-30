package com.company;

import com.company.bundlewriter.BundleWriter;
import com.company.bundlewriter.BundleWriterWithFormat;

import java.io.*;
import java.nio.charset.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws Exception {

        final String path = args[0];
        String fileName = (new Main()).getFileName(path, "[\\\\]*([a-zA-Z0-9]*)\\.java");
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        BundleInfoGetter bundleInfoGetter = new BundleInfoGetter();
        BundleWriter bundleWriter = new BundleWriterWithFormat(bundleInfoGetter.codeParsing(bufferedReader), fileName, true);
        bundleWriter.writeDataToFile();
    }

    public String getFileName(String path, String patternText){
        Pattern pattern = Pattern.compile(patternText);
        Matcher matcher = pattern.matcher(path);
        if(matcher.find())
        {
            return matcher.group(1);
        }
        return "default";
    }


}
