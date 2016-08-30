package com.company.bundlewriter;

import com.company.BundleInfoHolder;
import java.util.ArrayList;

/**
 * Created by bubal on 28.08.2016.
 */
public class BundleWriterWithFormat extends BundleWriterImpl{


    public BundleWriterWithFormat(ArrayList<BundleInfoHolder> bundleList, String fileName, boolean comments) {
        super(bundleList, fileName, comments);
    }

    public BundleWriterWithFormat(ArrayList<BundleInfoHolder> bundleList, String fileName)
    {
        super(bundleList, fileName);
    }

    public BundleWriterWithFormat(){}

    public void setBundleList(ArrayList<BundleInfoHolder> bundleList)
    {
        super.setBundleList(bundleList);
    }

    @Override
    public void fullBundleGetter() {
        String key, value, comment;
        StringBuffer stringBuffer = super.getStringBuffer();

        for (BundleInfoHolder bundle: super.getBundleList()){
            comment = bundle.getBundleComment();
            if(super.getComments() && !comment.equals("")){
                stringBuffer.append("#" + comment);
                stringBuffer.append("\n");
            }
            charArrayToHex((bundle.getBundleKey()+ " = " + bundle.getBundleValue()).toCharArray(), stringBuffer);
            stringBuffer.append("\n");
        }
        System.out.println(stringBuffer);
    }

    private void charArrayToHex(char[] chars, StringBuffer stringBuffer){
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] > 127){
                stringBuffer.append(Integer.toHexString(chars[i]));
            }
            else{
                stringBuffer.append(chars[i]);
            }
        }
    }

}
