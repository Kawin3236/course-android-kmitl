package demo.com.mylazyinstagram.api;

import demo.com.mylazyinstagram.*;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by student on 10/6/2017 AD.
 */

public interface LazyInstagramApi {
    String BASE = "https://us-central1-retrofit-course.cloudfunctions.net";

    @GET("/getProfile")
    Call<demo.com.mylazyinstagram.UserProfile> getProfile(@Query("user") String user);

}
