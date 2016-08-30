package com.company.bundlewriter;

import com.company.BundleInfoHolder;

import java.util.*;
import java.io.*;

public class BundleWriterImpl implements BundleWriter {

    private StringBuffer stringBuffer = new StringBuffer();
    private ArrayList<BundleInfoHolder> bundleList;
    private boolean comments = false;
    private String fileName;


    public BundleWriterImpl(ArrayList<BundleInfoHolder> bundleList, String fileName, boolean comments) {
        System.out.println("Full parent constructor");
        this.bundleList = bundleList;
        this.comments = comments;
        this.fileName = fileName;

    }

    public BundleWriterImpl(ArrayList<BundleInfoHolder> bundleList, String fileName)
    {
        System.out.println("Parent constructor with bundle");
        this.bundleList = bundleList;
        this.fileName = fileName;
    }

    public BundleWriterImpl(){
        System.out.println("Empty parent constructor");
    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    public String getFileName(){
        return this.fileName;
    }

    public void setBundleList(ArrayList<BundleInfoHolder> bundleList)
    {
        this.bundleList = bundleList;
    }

    public ArrayList<BundleInfoHolder> getBundleList(){  return bundleList;    }

    public void setComments(boolean comments){ this.comments = comments;    }

    public boolean getComments(){return this.comments;    }

    public void setStringBuffer(StringBuffer stringBuffer) {
        this.stringBuffer = stringBuffer;
    }
    public StringBuffer getStringBuffer(){return this.stringBuffer;}

    public void writeDataToFile() throws IOException {
        FileWriter fileWriter = new FileWriter(this.fileName + ".properties");
        fullBundleGetter();
        fileWriter.write(String.valueOf(stringBuffer));
        fileWriter.flush();
        fileWriter.close();
    }


    public void fullBundleGetter() {
        String key, value, comment;

        for (BundleInfoHolder bundle: this.bundleList){
            comment = bundle.getBundleComment();
            if(!comment.equals("")){
                stringBuffer.append("#" + comment);
                stringBuffer.append("\n");
            }
            stringBuffer.append(bundle.getBundleKey() + " = " + bundle.getBundleValue());
            stringBuffer.append("\n");
        }
        System.out.println(stringBuffer);
    }


}
