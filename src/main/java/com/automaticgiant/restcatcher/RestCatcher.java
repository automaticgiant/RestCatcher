package com.automaticgiant.restcatcher;

import spark.Request;
import spark.Response;
import spark.Spark;
import spark.SparkBase;

/**
 * Created by hunt8886 on 2015-06-18.
 */
public class RestCatcher {
    public static void main(String[] args){
        SparkBase.port(12345);
        final String all = "*";
        Spark.get(all, RestCatcher::reflector);
        Spark.post(all, RestCatcher::reflector);
        Spark.put(all, RestCatcher::reflector);
        Spark.delete(all, RestCatcher::reflector);
        Spark.options(all, RestCatcher::reflector);
    }

    private static String reflector(Request request, Response response) {
        StringBuilder r = new StringBuilder();
        r.append(request.url()).append("\n");
        r.append(request.requestMethod()).append("\n");
        r.append("----------HEADERS:\n");
        request.headers().stream().map(s ->
                s + ": " + request.headers(s)).forEach(s1 -> r.append(s1).append("\n"));
        r.append("----------BODY:\n");
        r.append(request.body()).append("\n");
        response.status(202);
        System.out.println(r.toString());
        return r.toString();
    }
}
