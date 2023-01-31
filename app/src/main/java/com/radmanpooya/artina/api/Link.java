package com.radmanpooya.artina.api;

public class Link {
    public static final String BASE_URL="http://192.168.43.85:8000/";
    public static final String BASE_URL_IMAGE="http://192.168.43.85:8000";
    public static final String SEND_OTP=BASE_URL+"login/sendOtp";
    public static final String VERIFY_CODE=BASE_URL+"login/verifyCode";
    public static final String GET_CATEGORY=BASE_URL+"learn/cat";
    public static final String LEARN_LIST=BASE_URL+"learn/learn/";
    public static final String LSECTION_LIST=BASE_URL+"learn/section/";
    public static final String GET_PRODUCTS=BASE_URL+"catalogue/sortby";
    public static final String PRODUCT_DETAILS=BASE_URL+"catalogue/single";
    public static final String GET_LESSONS=BASE_URL+"learn/lesson/";
}
