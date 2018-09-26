package com.example.android.registration;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.registration.Databases.LoginDatabaseHelper;

public class LoginFragment extends Fragment {

    Activity myActivity ;
    LoginDatabaseHelper helper;

    View rootView;
    Button buttonSkip, SignUpButton, SignInButton;
    EditText editTextEmail,editTextPassword;
    FragmentActionListener fragmentActionListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView= inflater.inflate(R.layout.fragment_login,container,false);
        helper = new LoginDatabaseHelper((FragmentActivity) myActivity) ;
        init();
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        myActivity = (MainActivity) context;
        fragmentActionListener = (MainActivity) context;
    }

    void init()
    {
        SignUpButton = rootView.findViewById(R.id.email_sign_up_button);
        SignInButton = rootView.findViewById(R.id.email_sign_in_button);
        buttonSkip = rootView.findViewById(R.id.skip);

        editTextEmail =rootView.findViewById(R.id.userName);
        editTextPassword =rootView.findViewById(R.id.password);

        SignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string = editTextEmail.getText().toString();
                String pass = editTextPassword.getText().toString();

                String password = helper.searchPass(string);

                if (pass.equals(password))
                {
                    fragmentActionListener.actionPerformed("workshop");
                }

                else
                {
                    Toast temp = Toast.makeText(getActivity(), "Oh, Username and Password didn't match!", Toast.LENGTH_SHORT);
                    temp.show();
                }
            }
        });

        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentActionListener.actionPerformed("signup");
            }
        });

        buttonSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentActionListener.actionPerformed("workshop");
            }
        });
    }

}
