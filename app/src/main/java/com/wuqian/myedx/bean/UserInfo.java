package com.wuqian.myedx.bean;

/**
 * Created by wuqian on 2016/5/13.
 */
public class UserInfo {
    private String username;
    private String email;
    private String mailing_address;
    private String goals;

    private ProfileImage profile_image;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMailing_address() {
        return mailing_address;
    }

    public void setMailing_address(String mailing_address) {
        this.mailing_address = mailing_address;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public ProfileImage getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(ProfileImage profile_image) {
        this.profile_image = profile_image;
    }


    @Override
    public String toString() {
        return "UserInfo{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", mailing_address='" + mailing_address + '\'' +
                ", goals='" + goals + '\'' +
                ", profile_image=" + profile_image +
                '}';
    }

    public static class ProfileImage{
        boolean has_image;
        private String image_url_full;
        private String image_url_large;
        private String image_url_medium;
        private String image_url_small;

        public boolean isHas_image() {
            return has_image;
        }

        public void setHas_image(boolean has_image) {
            this.has_image = has_image;
        }

        public String getImage_url_full() {
            return image_url_full;
        }

        public void setImage_url_full(String image_url_full) {
            this.image_url_full = image_url_full;
        }

        public String getImage_url_large() {
            return image_url_large;
        }

        public void setImage_url_large(String image_url_large) {
            this.image_url_large = image_url_large;
        }

        public String getImage_url_medium() {
            return image_url_medium;
        }

        public void setImage_url_medium(String image_url_medium) {
            this.image_url_medium = image_url_medium;
        }

        public String getImage_url_small() {
            return image_url_small;
        }

        public void setImage_url_small(String image_url_small) {
            this.image_url_small = image_url_small;
        }

        @Override
        public String toString() {
            return "ProfileImage{" +
                    "has_image=" + has_image +
                    ", image_url_full='" + image_url_full + '\'' +
                    ", image_url_large='" + image_url_large + '\'' +
                    ", image_url_medium='" + image_url_medium + '\'' +
                    ", image_url_small='" + image_url_small + '\'' +
                    '}';
        }
    }
}
