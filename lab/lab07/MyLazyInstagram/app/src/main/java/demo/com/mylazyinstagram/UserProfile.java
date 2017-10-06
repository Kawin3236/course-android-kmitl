package demo.com.mylazyinstagram;

/**
 * Created by student on 10/6/2017 AD.
 */

public class UserProfile {
    private String follower;
    private String bio;

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    private String following;
    private String urlProfile;

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public String getUrlProfile() {
        return urlProfile;
    }

    public void setUrlProfile(String urlProfile) {
        this.urlProfile = urlProfile;
    }

    private String user;

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    private String post;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
