package com.example.split_it;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity implements ExampleDialog.ExampleDialogListener {
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView name, email;
    Button signOutBtn;
    ImageView ProfilePhoto;
    Button groupBtn;
    private TextView textViewUsername;

    private Button button;

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



        //Dummy -> Will remove the code below after testing
        groupBtn = findViewById(R.id.dummy_button);
        //------------------------------------------------------------------------------------------

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
//Dialog
        textViewUsername = (TextView) findViewById(R.id.textview_username);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
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


        // This will be removed after testing
        //------------------------------------------------------------------------------------------
        groupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(ProfileActivity.this, GroupActivity.class);
                startActivity(n);
            }
        });
        //------------------------------------------------------------------------------------------
        //Recycler-View
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CustomAdapter(generateData()));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
//Data send in Recycler-View
    private List<String> generateData() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add(String.valueOf(i) + "--Row");
        }
        return data;

    }
    //Dialog Funtions
    public void openDialog() {
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    public void applyTexts(String username) {
        textViewUsername.setText(username);

    }



//Sign-Out
    void signOut(){
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                finish();
                startActivity(new Intent(ProfileActivity.this,LoginActivity.class));
            }
        });

    }
}
