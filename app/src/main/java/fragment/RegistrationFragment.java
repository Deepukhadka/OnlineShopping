package fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.onlineshopping.R;

import static android.content.Context.MODE_PRIVATE;

public class RegistrationFragment extends Fragment {

    private EditText etEmail, etPassword,etConfirmPassword;
    private Button btnRegister;

    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registration,container,false);

        etEmail = view.findViewById(R.id.etEmailRegister);
        etPassword = view.findViewById(R.id.etPasswordRegister);
        etConfirmPassword = view.findViewById(R.id.etPasswordConfirm);
        btnRegister = view.findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

        return view;
    }


    public void signUp() {

        if (emailValidation()) {
            //      for valid email check user
            if (checkUser()) {
                //            when user is not registered check password and then register
                if (checkPassword()) {
                    //                when both password is matched check length of password
                    if (passwordValidation()){

                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("User", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("email", etEmail.getText().toString());
                        editor.putString("password", etPassword.getText().toString());
                        editor.commit();
                        Toast.makeText(getActivity(), "Registered Successful", Toast.LENGTH_SHORT).show();
                        etEmail.setText("");
                        etPassword.setText("");
                        etConfirmPassword.setText("");

                    }
                }
            }
        }

    }

    public Boolean checkPassword(){
        if (etPassword.getText().toString().equals(etConfirmPassword.getText().toString())){
            return true;
        }else {
            Toast.makeText(getActivity(), "Password Not Matched", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public Boolean checkUser(){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("User",MODE_PRIVATE);
        String email = sharedPreferences.getString("email","");

        if (email.equals(etEmail.getText().toString())){
//            user already exists
            Toast.makeText(getActivity(), "User already exists", Toast.LENGTH_SHORT).show();
            return false;
        }else {
//            user doesn't exist
            return true;
        }
    }



    public Boolean emailValidation(){
        String email = etEmail.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (email.matches(emailPattern)){
            return true;
        }else {
            Toast.makeText(getActivity(), "Email is not valid12", Toast.LENGTH_SHORT).show();
            return false;
        }
    }



    public Boolean passwordValidation(){
        if (etPassword.getText().toString().length() >= 6 && etPassword.getText().toString().length() <=10){
            return true;
        }else {
            Toast.makeText(getActivity(), "Password should be more than 6 and less than 10 characters", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


}
