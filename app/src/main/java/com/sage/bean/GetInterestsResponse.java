package com.sage.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Anuj Sharma on 5/11/2017.
 */

public class GetInterestsResponse extends GenericBean {

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
         * id : 1
         * name : Hamburger
         * image : http://www.bk.com/sites/default/files/Thumb_0005_Whopper_0.jpg
         * creation_date : 2017-05-05T03:10:15Z
         */

        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;
        @SerializedName("image")
        private String image;
        @SerializedName("creation_date")
        private String creationDate;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getCreationDate() {
            return creationDate;
        }

        public void setCreationDate(String creationDate) {
            this.creationDate = creationDate;
        }
    }
}
