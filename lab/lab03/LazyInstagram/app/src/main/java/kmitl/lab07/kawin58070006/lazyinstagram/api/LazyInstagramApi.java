package kmitl.lab07.kawin58070006.lazyinstagram.api;

import kmitl.lab07.kawin58070006.lazyinstagram.UserProfile;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 11/10/2560.
 */

public interface LazyInstagramApi {
    String BASE = "https://us-central1-retrofit-course.cloudfunctions.net";

    @GET("/getProfile")
    Call<UserProfile> getProfile(@Query("user") String user);

}
