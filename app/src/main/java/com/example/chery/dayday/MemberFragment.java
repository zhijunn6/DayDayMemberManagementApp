package com.example.chery.dayday;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MemberFragment extends Fragment{
    private static final String TAG = "BAKA";
    private EditText memberName, memberBirthDay, memberBirthMonth, memberBirthYear, memberIC, memberEmail, memberAddress, memberPhoneNumber;
    private Button submitDetailsButton, openDialogButton;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private DatabaseReference MembersEndPoint;

    private String mName, mEmail, mAddress, mBirthDay, mBirthMonth, mBirthYear, mIC, mPhoneNo;


    public MemberFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        mDatabase =  FirebaseDatabase.getInstance().getReference();
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        MembersEndPoint = mDatabase.child("Members");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.member_fragment, container, false);

        // Initialize edit texts and button
        memberName = (EditText) view.findViewById(R.id.member_name);
        memberBirthDay = (EditText) view.findViewById(R.id.member_birthday_day);
        memberBirthMonth = (EditText) view.findViewById(R.id.member_birthday_month);
        memberBirthYear = (EditText) view.findViewById(R.id.member_birthday_year);
        memberIC = (EditText) view.findViewById(R.id.member_IC);
        memberEmail = (EditText) view.findViewById(R.id.member_Email);
        memberAddress = (EditText) view.findViewById(R.id.member_address);
        memberPhoneNumber = (EditText) view.findViewById(R.id.member_PhoneNumber);
        openDialogButton = (Button) view.findViewById(R.id.open_dialog_button);
        submitDetailsButton = (Button) view.findViewById(R.id.submit_member_details);

        openDialogButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Create Dialog on button click
                    AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                    alert.setTitle("As a Member,");
                    alert.setMessage(getString(R.string.member_benefit_1)
                            + "\n" + getString(R.string.member_benefit_2)
                            + "\n" + getString(R.string.member_benefit_3));
                    alert.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(getContext(), "Thanks for reading!", Toast.LENGTH_SHORT).show();
                        }
                    });
                    alert.show();
            }
        });

        submitDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Getting text from Edittext in Member Form
                mName = memberName.getText().toString();
                mBirthDay = memberBirthDay.getText().toString().trim();
                mBirthMonth = memberBirthMonth.getText().toString().trim();
                mBirthYear = memberBirthYear.getText().toString().trim();
                mIC = memberIC.getText().toString().trim();
                mEmail = memberEmail.getText().toString().trim();
                mAddress = memberAddress.getText().toString().trim();
                mPhoneNo = memberPhoneNumber.getText().toString().trim();

                    // Validation
                if (validationSuccess()) {

                    // Set data
                    Member newMember = new Member();
                    newMember.setMemberName(mName);
                    newMember.setMemberBirthDay(Integer.valueOf(mBirthDay));
                    newMember.setMemberBirthMonth(Integer.valueOf(mBirthMonth));
                    newMember.setMemberBirthYear(Integer.valueOf(mBirthYear));
                    newMember.setMemberIC(Long.valueOf(mIC));
                    newMember.setMemberEmail(mEmail);
                    newMember.setMemberAddress(mAddress);
                    newMember.setMemberPhoneNumber(Long.valueOf(mPhoneNo));


                    // To FireBase
                    String unique_key = MembersEndPoint.push().getKey();
                    MembersEndPoint.child(unique_key).setValue(newMember);
                    Log.d(TAG, "MembersEndPoint: " + unique_key);

                    // Empty the fields after submission
                    memberName.setText("");
                    memberBirthDay.setText("");
                    memberBirthMonth.setText("");
                    memberBirthYear.setText("");
                    memberIC.setText("");
                    memberEmail.setText("");
                    memberAddress.setText("");
                    memberPhoneNumber.setText("");
                }else{
                    
                }
            }
        });

        return view;
    }

    private boolean validationSuccess() {
        if(memberName.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(getActivity(), "Please enter your name", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(memberBirthDay.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(getActivity(), "Please enter your Birth Day", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(memberBirthMonth.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Please enter your Birth Month", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(memberBirthYear.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Please enter your Birth Year", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(memberIC.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Please enter your IC", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(memberPhoneNumber.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Please enter your Phone Number", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @Override
    public void onResume() {
        //Create Dialog
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setTitle("As a Member,");
        alert.setMessage(getString(R.string.member_benefit_1)
                + "\n" + getString(R.string.member_benefit_2)
                + "\n" + getString(R.string.member_benefit_3));
        alert.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getContext(), "Thanks for reading!", Toast.LENGTH_SHORT).show();
            }
        });
        alert.show();
        super.onResume();
    }
}
