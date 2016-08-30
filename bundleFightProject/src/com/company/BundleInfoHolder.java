package com.company;

public class BundleInfoHolder {
    private String bundleKey;
    private String bundleValue;
    private String bundleComment;

    BundleInfoHolder(String bundleKey, String bundleValue, String bundleComment){
        this.bundleComment = bundleComment;
        this.bundleKey = bundleKey;
        this.bundleValue = bundleValue;
    }

    BundleInfoHolder(){};

    public String getBundleComment() {
        return bundleComment;
    }

    public void setBundleComment(String bundleComment) {
        this.bundleComment = bundleComment;
    }

    public String getBundleValue() {
        return bundleValue;
    }

    public void setBundleValue(String bundleValue) {
        this.bundleValue = bundleValue;
    }

    public String getBundleKey() {
        return bundleKey;
    }

    public void setBundleKey(String bundleKey) {
        this.bundleKey = bundleKey;
    }
}
