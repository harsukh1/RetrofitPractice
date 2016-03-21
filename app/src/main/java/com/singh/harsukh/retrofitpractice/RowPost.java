package com.singh.harsukh.retrofitpractice;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by harsukh on 3/20/16.
 */
public class RowPost{

    private List<PostsBean> posts;

    public List<PostsBean> getPosts() {
        return posts;
    }

    public void setPosts(List<PostsBean> posts) {
        this.posts = posts;
    }

    public static class PostsBean implements Parcelable {
        private int id;
        private String title;
        private String content;
        private String excerpt;
        private String date;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getExcerpt() {
            return excerpt;
        }

        public void setExcerpt(String excerpt) {
            this.excerpt = excerpt;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        protected PostsBean(Parcel in) {
            id = in.readInt();
            title = in.readString();
            content = in.readString();
            excerpt = in.readString();
            date = in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeString(title);
            dest.writeString(content);
            dest.writeString(excerpt);
            dest.writeString(date);
        }

        @SuppressWarnings("unused")
        public static final Parcelable.Creator<PostsBean> CREATOR = new Parcelable.Creator<PostsBean>() {
            @Override
            public PostsBean createFromParcel(Parcel in) {
                return new PostsBean(in);
            }

            @Override
            public PostsBean[] newArray(int size) {
                return new PostsBean[size];
            }
        };
    }
}
