package com.example.chery.dayday;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class UserDetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_details, container, false);

        // Get data from Bundle
        Bundle bundle = getArguments();

        String ud_memberName = bundle.getString("memberName");
        int ud_memberBirthDay = bundle.getInt("memberBirthDay");
        int ud_memberBirthMonth = bundle.getInt("memberBirthMonth");
        int ud_memberBirthYear = bundle.getInt("memberBirthYear");
        Long ud_memberIC = bundle.getLong("memberIC");
        String ud_memberEmail = bundle.getString("memberEmail");
        String ud_memberAddress = bundle.getString("memberAddress");
        Long ud_memberPhoneNo = bundle.getLong("memberPhoneNo");


        // Initialize TextViews
        TextView display_ud_memberName = (TextView) view.findViewById(R.id.ud_member_name);
        TextView display_ud_memberBirthDay = (TextView) view.findViewById(R.id.ud_member_birthday_day);
        TextView display_ud_memberBirthMonth= (TextView) view.findViewById(R.id.ud_member_birthday_month);
        TextView display_ud_memberBirthYear = (TextView) view.findViewById(R.id.ud_member_birthday_year);
        TextView display_ud_memberIC = (TextView) view.findViewById(R.id.ud_member_IC);
        TextView display_ud_memberEmail = (TextView) view.findViewById(R.id.ud_member_Email);
        TextView display_ud_memberAddress = (TextView) view.findViewById(R.id.ud_member_address);
        TextView display_ud_memberPhoneNo = (TextView) view.findViewById(R.id.ud_member_PhoneNumber);

        // Set TextViews to display data
        display_ud_memberName.setText(ud_memberName);
        display_ud_memberBirthDay.setText(String.valueOf(ud_memberBirthDay));
        display_ud_memberBirthMonth.setText(String.valueOf(ud_memberBirthMonth));
        display_ud_memberBirthYear.setText(String.valueOf(ud_memberBirthYear));
        display_ud_memberIC.setText(String.valueOf(ud_memberIC));
        display_ud_memberEmail.setText(ud_memberEmail);
        display_ud_memberAddress.setText(ud_memberAddress);
        display_ud_memberPhoneNo.setText(String.valueOf(ud_memberPhoneNo));

        return view;
    }

}
