package com.example.android.registration;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class LoginFragment extends Fragment {

    View rootView;
    Button buttonSkip;
    EditText editTextEmail,editTextPassword;
    FragmentActionListener fragmentActionListener;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView= inflater.inflate(R.layout.fragment_login,container,false);
        init();
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentActionListener = (MainActivity) context;
    }

    void init(){
        buttonSkip = rootView.findViewById(R.id.skip);
        editTextEmail =rootView.findViewById(R.id.email);
        editTextPassword =rootView.findViewById(R.id.password);
        buttonSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentActionListener.actionPerformed("workshop");
            }
        });
    }

}
