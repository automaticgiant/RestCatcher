import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.SparkBase;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

/**
 * Created by hunt8886 on 2015-06-18.
 */
public class RestCatcher {
    public static void main(String[] args){
        SparkBase.port(12345);
        Spark.get("*", RestCatcher::reflector);
        Spark.put("*", RestCatcher::reflector);
        Spark.post("*", RestCatcher::reflector);
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
