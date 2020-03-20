package com.example.retrofitasses.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PostsItem implements Serializable {

	@SerializedName("name")
	private String name;

	@SerializedName("profileImage")
	private String profileImage;

	@SerializedName("message")
	private String message;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setProfileImage(String profileImage){
		this.profileImage = profileImage;
	}

	public String getProfileImage(){
		return profileImage;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"PostsItem{" + 
			"name = '" + name + '\'' + 
			",profileImage = '" + profileImage + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}