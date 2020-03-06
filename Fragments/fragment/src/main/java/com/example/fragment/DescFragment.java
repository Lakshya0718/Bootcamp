package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class DescFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_desc, container, false);
        View view1 = view.findViewById(R.id.title);
        View view2 = view.findViewById(R.id.desc);

        Bundle bundle = getArguments();
        int position = bundle.getInt("itemPosition");

        String Title = getTitle(position);
        String Desc = getDesc(position);

        ((TextView)view1).setText(Title);
        ((TextView)view2).setText(Desc);

        return view;
    }

    private String getDesc(int position) {

        ArrayList<String> desc = new ArrayList<>();
        desc.add("Sachin Ramesh Tendulkar is an Indian former international cricketer and a former captain of the Indian national team. He is widely regarded as one of the greatest batsmen in the history of cricket. He is the highest run scorer of all time in International cricket.");
        desc.add("Yuvraj Singh is a former Indian cricketer who played in all forms of the game. An all-rounder who bats left-handed in the middle order and bowls slow left-arm orthodox, One of the greatest limited over players to play for India, Yuvraj was particularly noted for his aggressive stroke play of the ball and his fielding.");
        desc.add("Mahendra Singh Dhoni, commonly known as MS Dhoni, is an Indian international cricketer who captained the Indian national team in limited-overs formats from 2007 to 2016 and in Test cricket from 2008 to 2014.");
        desc.add("Virat Kohli is an Indian cricketer who currently captains the India national team. A right-handed top-order batsman, Kohli is regarded as one of the best batsmen in the world. He plays for Royal Challengers Bangalore in the Indian Premier League, and has been the team's captain since 2013.");

        String Desc = desc.get(position);

        return Desc;
    }

    private String getTitle(int position) {

        ArrayList<String> title = new ArrayList<>();
        title.add("Sachin");
        title.add("Yuvraj");
        title.add("Dhoni");
        title.add("Kohli");

        String Title = title.get(position);

        return Title;
    }


}