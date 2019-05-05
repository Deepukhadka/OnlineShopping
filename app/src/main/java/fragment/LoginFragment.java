package fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.onlineshopping.DashboardActivity;
import com.example.onlineshopping.R;

import static android.content.Context.MODE_PRIVATE;

public class LoginFragment extends Fragment {

    private EditText etEmail, etPassword;
    private Button btnLogin;



    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login,container,false);

        etEmail = view.findViewById(R.id.etEmailLogin);
        etPassword = view.findViewById(R.id.etPasswordLogin);
        btnLogin = view.findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        return view;
    }

    private void signIn(){

        if (validate()){
            if(emailValidation()){
//      for valid email check user
                if (checkUser()){
//            when user is not registered check password and then register
                    SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("User", MODE_PRIVATE);
                    String email = sharedPreferences.getString("email","");
                    String password = sharedPreferences.getString("password","");
                    if(email.equals(etEmail.getText().toString()) && password.equals(etPassword.getText().toString())){
                        Toast.makeText(getActivity(), "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), DashboardActivity.class);
                        startActivity(intent);
                        etEmail.setText("");
                        etPassword.setText("");
                    }else {
                        Toast.makeText(getActivity(), "Either username or password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    private Boolean checkUser(){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("User",MODE_PRIVATE);
        String email = sharedPreferences.getString("email","");

        if (email.equals(etEmail.getText().toString())){
//            user exists
            return true;
        }else {
//            user doesn't exist
            Toast.makeText(getActivity(), "User doesn't exists", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private Boolean emailValidation(){
        String email = etEmail.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (email.matches(emailPattern)){
            return true;
        }else {
            Toast.makeText(getActivity(), "Email is not valid", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private Boolean validate() {
        boolean validate = true;
        if (TextUtils.isEmpty(etEmail.getText().toString())) {
            etEmail.requestFocus();
            Toast.makeText(getActivity(), "Please Enter Email", Toast.LENGTH_SHORT).show();
            validate = false;
        }
        if (TextUtils.isEmpty(etPassword.getText().toString())) {
            etPassword.requestFocus();
            Toast.makeText(getActivity(), "Please Enter Password", Toast.LENGTH_SHORT).show();
            validate = false;
        }
        return validate;
    }


}
