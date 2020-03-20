package com.example.retrofitasses.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable {

	@SerializedName("posts")
	private List<PostsItem> posts;

	public void setPosts(List<PostsItem> posts){
		this.posts = posts;
	}

	public List<PostsItem> getPosts(){
		return posts;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"posts = '" + posts + '\'' + 
			"}";
		}
}