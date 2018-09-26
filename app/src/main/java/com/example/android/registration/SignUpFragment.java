package com.example.android.registration;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.registration.Databases.LoginDatabaseHelper;

public class SignUpFragment extends Fragment
{
    Activity myActivity ;
    FragmentActionListener fragmentActionListener;
    View view ;
    LoginDatabaseHelper helper;
    Button signUp ;
    EditText name, email, uname, pass1, pass2 ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        helper = new LoginDatabaseHelper((FragmentActivity) myActivity) ;
        buttonListener() ;
        return view ;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        myActivity = (MainActivity) context;
        fragmentActionListener = (MainActivity) context;
    }

    void buttonListener()
    {
        signUp = (Button) view.findViewById(R.id.Bsignupbutton) ;
        name = (EditText) view.findViewById(R.id.TFName);
        email = (EditText) view.findViewById(R.id.TFemail);
        uname = (EditText) view.findViewById(R.id.TFuname);
        pass1 = (EditText) view.findViewById(R.id.TFpass1);
        pass2 = (EditText) view.findViewById(R.id.TFpass2);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namestr = name.getText().toString();
                String emailstr = email.getText().toString();
                String unamestr = uname.getText().toString();
                String pass1str = pass1.getText().toString();
                String pass2str = pass2.getText().toString();

                if (!pass1str.equals(pass2str))
                {
                    //Popup message if passwords don't match
                    Toast pass = Toast.makeText(myActivity, "Hmmm...Passwords didn't match!", Toast.LENGTH_SHORT);
                    pass.show();
                }
                else if(namestr.equals("") || emailstr.equals("") || unamestr.equals("") || pass1str.equals("") || pass2str.equals("")) {
                    Toast blank = Toast.makeText(myActivity, "Incomplete Information!", Toast.LENGTH_SHORT);
                    blank.show();
                }
                else{
                    //Insert details in the database
                    Contact contact = new Contact();
                    contact.setName(namestr);
                    contact.setEmail(emailstr);
                    contact.setUname(unamestr);
                    contact.setPass(pass1str);

                    helper.insertContact(contact);
                    fragmentActionListener.actionPerformed("login");
                }
            }
        });
    }
}
