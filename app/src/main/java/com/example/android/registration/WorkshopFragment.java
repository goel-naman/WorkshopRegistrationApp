package com.example.android.registration;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.registration.Databases.WorkshopDatabaseHelper;

public class WorkshopFragment extends Fragment {

    View rootView;
    Context context;
    RecyclerView recyclerView;
    WorkshopAdaptor adaptor;

    WorkshopDatabaseHelper helper ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_workshop,container,false);
        helper = new WorkshopDatabaseHelper((FragmentActivity) context) ;
        recyclerView = rootView.findViewById(R.id.workshop_list);
        recyclerView.hasFixedSize();
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        adaptor = new WorkshopAdaptor(helper.getDemoList());
        recyclerView.setAdapter(adaptor);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

//    ArrayList<WorkshopItem> getDemoList(){
//        ArrayList<WorkshopItem> list = new ArrayList<>();
//        for(int i=0;i<10;i++){
//            WorkshopItem item= new WorkshopItem("Title "+i,"Description "+i);
//            list.add(item);
//        }
//        return list;
//    }


}
