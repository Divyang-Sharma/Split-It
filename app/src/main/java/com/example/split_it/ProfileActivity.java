package com.example.split_it;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.split_it.database.AppDatabase;
import com.example.split_it.database.model.Group;
import com.example.split_it.database.repository.GroupRepository;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView name, email;

    Button signOutBtn;
    ImageView ProfilePhoto;

    Button addGroupButton;

    GroupRepository groupRepository;
    AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // xml component declaration
        name = findViewById(R.id.name);
        signOutBtn = findViewById(R.id.signout);
        ProfilePhoto = findViewById(R.id.ProfilePhoto);
        Resources res = getResources();
        Drawable defaultimage = ResourcesCompat.getDrawable(res, R.drawable.image, null);

        appDatabase = AppDatabase.Companion.getDatabase(this);
        groupRepository = new GroupRepository(appDatabase);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        //Fetching User Data and Show In Profile
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();

            Uri Photo = acct.getPhotoUrl();

            name.setText(personName);

            Glide.with(this).load(String.valueOf(Photo)).placeholder(defaultimage).into(ProfilePhoto);

        }

        addGroupButton = findViewById(R.id.button);
        addGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        //Sign-Out Button On Click Listener
        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });


        //Recycler-View
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        groupRepository.getGroups().observe(this, groups -> {
            recyclerView.setAdapter(new CustomAdapter(groups));
        });
    }

    //Data send in Recycler-View
    private List<String> generateData() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add(String.valueOf(i) + "--Row");
        }
        return data;

    }

    //Dialog Functions
    public void openDialog() {

        View dialogView = getLayoutInflater().inflate(R.layout.layout_dialog, null);
        EditText groupName = dialogView.findViewById(R.id.edit_username);

        new AlertDialog.Builder(this)
                .setView(dialogView)
                .setTitle("Group name")
                .setNegativeButton("Cancel", (dialogInterface, i) -> {
                })
                .setPositiveButton("Ok", (dialogInterface, i) -> {
                    String groupNameText = groupName.getText().toString();
                    if (!groupNameText.trim().isEmpty()) {

                        //close the dialog
                        dialogInterface.dismiss();

                        //adding the current user to the member list
                        List<Integer> userList = Collections.singletonList(1);

                        //inserts the group into the db
                        Group group = new Group(null,groupNameText,userList);
                        groupRepository.insertGroup(group);
                    }
                })
                .show();

    }


    //Sign-Out
    void signOut() {
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                finish();
                startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
            }
        });

    }
}
