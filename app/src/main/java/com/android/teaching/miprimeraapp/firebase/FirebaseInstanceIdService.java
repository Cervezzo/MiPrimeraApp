package com.android.teaching.miprimeraapp.firebase;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

public class FirebaseInstanceIdService extends com.google.firebase.iid.FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        Log.d("InstanceIdService", "EP! Token refreshed!");

        // Get updated InstanceID token.
        String token = FirebaseInstanceId.getInstance().getToken();
        FirebaseDatabase myDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = myDatabase
                .getReference("device_push_token");
        databaseReference.setValue(token);
    }
}
