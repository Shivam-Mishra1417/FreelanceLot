package controllers;

import play.mvc.Call;

public class RouteController {

    public static String EncodeURL(String url) throws java.io.UnsupportedEncodingException {
        url = java.net.URLEncoder.encode(url, "UTF-8");
        String Base="skill/";
        url = url.replaceAll("/","");
        url = url.replaceAll("\\)","");
        url = url.replaceAll("\\(","");
        url = url.replaceAll(",","");
        url = url.replaceAll(".","");
        url =url.replaceAll("\\+","%20");
        Base=Base.concat(url);
//        System.out.println("url is--->"+url);
        return Base;
    }
}
