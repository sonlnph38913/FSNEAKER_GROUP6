package com.sonlnph38913.fpoly.fsneaker.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sonlnph38913.fpoly.fsneaker.R;
import com.sonlnph38913.fpoly.fsneaker.adapter.GiayAdapter;
import com.sonlnph38913.fpoly.fsneaker.dao.GiayDao;
import com.sonlnph38913.fpoly.fsneaker.model.Giay;

import java.util.ArrayList;

public class GiayFragment extends Fragment {
    GiayDao giayDao;
    RecyclerView rcvGiay;
    SearchView searchgiay;
    GiayAdapter adapter;
    ArrayList<Giay>list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.giay_fragment, container, false);
        rcvGiay = view.findViewById(R.id.rcvGiay);
//        searchgiay = view.findViewById(R.id.svGiay);
       // FloatingActionButton floatAddGiay = view.findViewById(R.id.fltAddGiay);
        giayDao = new GiayDao(getContext());
        list = giayDao.getDSGiay();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        adapter = new GiayAdapter(getContext(),list,giayDao);
        rcvGiay.setLayoutManager(gridLayoutManager);
        rcvGiay.setAdapter(adapter);

        return view;

    }



}
