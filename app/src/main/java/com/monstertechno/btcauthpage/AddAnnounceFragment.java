package com.monstertechno.btcauthpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.monstertechno.btcauthpage.R;

import java.util.HashMap;

    public class AddAnnounceFragment extends Fragment {

        private EditText title,description,author;
        private Button save;

        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_addanounce, container, false);

            title = v.findViewById(R.id.title);
            description = v.findViewById(R.id.description);
            author = v.findViewById(R.id.author);
            save = v.findViewById(R.id.save);

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    HashMap<String,Object> map = new HashMap<>();
                    map.put("title",title.getText().toString());
                    map.put("description",description.getText().toString());
                    map.put("author",author.getText().toString());

                    FirebaseDatabase.getInstance().getReference().child("Post").push()
                            .setValue(map)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Log.i("jfbvkj", "onComplete: ");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.i("jfbvkj", "onFailure: "+e.toString());
                                }
                            }).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.i("jfbvkj", "onSuccess: ");

                        }
                    });


                }
            });
            return v;
        }
    }