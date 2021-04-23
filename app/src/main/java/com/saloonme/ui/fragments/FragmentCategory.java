package com.saloonme.ui.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saloonme.R;
public class FragmentCategory extends Fragment{

    public static FragmentCategory newInstance() {
        Bundle args = new Bundle();
        FragmentCategory fragment = new FragmentCategory();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_category, container, false);
        return v;
    }
}