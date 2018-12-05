package com.example.chery.dayday;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class SearchFragment extends Fragment implements SearchRecyclerViewAdapter.ItemClickListener{
    private static final String TAG = "BAKA";
    private RecyclerView recyclerView;
    public EditText searchEt;
    private SearchRecyclerViewAdapter adapter;
    ArrayList<Member> zenxinMember;

    // FireBase
    DatabaseReference mDatabase;
    FirebaseAuth mAuth;
    FirebaseDatabase mFirebaseInstance;
    FirebaseAuth.AuthStateListener mAuthListener;
    DatabaseReference MembersEndPoint;

    // OnCreateView of fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);

        recyclerView = view.findViewById(R.id.search_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // FireBase
        // AddValueEventListener to retrieve stored data in FireBase DataBase
        mAuth = FirebaseAuth.getInstance();
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mDatabase =  FirebaseDatabase.getInstance().getReference();
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        MembersEndPoint = mDatabase.child("Members");

        // Retrieve stored data according to memberName
        MembersEndPoint.orderByChild("memberName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                zenxinMember = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Member existingMember = ds.getValue(Member.class);
                    zenxinMember.add(existingMember);

                    //SearchRecyclerViewAdapter
                    if (getActivity() != null) {
                        adapter = new SearchRecyclerViewAdapter(getActivity(), zenxinMember);
                        recyclerView.setAdapter(adapter);
                    }

                    adapter.setClickListener(SearchFragment.this);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Failed to read value
                Log.d("Hello", "Failed to read value.", databaseError.toException());
            }
        });

        // Search using EditText
        searchEt = (EditText) view.findViewById(R.id.search_et);
        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });

        return view;
    }

    // Filter for searching a particular member using the member's name
    private void filter(String text){
        ArrayList<Member> filteredList = new ArrayList<>();

        for (Member item : zenxinMember){
            if (item.getMemberName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }

    // onClick on each item in RecyclerView
    @Override
    public void onItemClick(View view, int position) {

        Member searchedMember = (Member) adapter.getItem(position);

        // User detail to pass to UserDetailsFragment
        String pass_memberName = searchedMember.getMemberName();
        int pass_memberBirthDay = searchedMember.getMemberBirthDay();
        int pass_memberBirthMonth = searchedMember.getMemberBirthMonth();
        int pass_memberBirthYear = searchedMember.getMemberBirthYear();
        Long pass_memberIC = searchedMember.getMemberIC();
        String pass_memberEmail = searchedMember.getMemberEmail();
        String pass_memberAddress = searchedMember.getMemberAddress();
        Long pass_memberPhoneNo = searchedMember.getMemberPhoneNumber();

        // Use Bundle to send positional data to UserDetailsFragment
        Bundle bundle = new Bundle();
        bundle.putString("memberName", pass_memberName);
        bundle.putInt("memberBirthDay", pass_memberBirthDay);
        bundle.putInt("memberBirthMonth", pass_memberBirthMonth);
        bundle.putInt("memberBirthYear", pass_memberBirthYear);
        bundle.putLong("memberIC", pass_memberIC);
        bundle.putString("memberEmail", pass_memberEmail);
        bundle.putString("memberAddress", pass_memberAddress);
        bundle.putLong("memberPhoneNo", pass_memberPhoneNo);

        // Create and open new fragment
        UserDetailsFragment userDetailsFragment = new UserDetailsFragment();
        userDetailsFragment.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, userDetailsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
