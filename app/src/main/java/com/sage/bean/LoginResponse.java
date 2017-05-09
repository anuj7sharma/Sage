package com.sage.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Anuj Sharma on 5/9/2017.
 */

public class LoginResponse extends GenericBean{

    @SerializedName("data")
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * uid : 6
         * first_name : anuj
         * last_name : sharma
         * email : anujs1991@gmail.com
         * profile_pic :
         * gender : 0
         */

        @SerializedName("uid")
        private int uid;
        @SerializedName("first_name")
        private String firstName;
        @SerializedName("last_name")
        private String lastName;
        @SerializedName("email")
        private String email;
        @SerializedName("profile_pic")
        private String profilePic;
        @SerializedName("gender")
        private String gender;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getProfilePic() {
            return profilePic;
        }

        public void setProfilePic(String profilePic) {
            this.profilePic = profilePic;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }
    }
}
