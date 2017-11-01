package kmitl.kawin58070006.horyuni.api;

import kmitl.kawin58070006.horyuni.UserProfile;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 27/10/2560.
 */

public interface Api {
    String BASE = "https://us-central1-retrofit-course.cloudfunctions.net";

    @GET("/getProfile")
    Call<UserProfile> getProfile(@Query("user") String user);
}
