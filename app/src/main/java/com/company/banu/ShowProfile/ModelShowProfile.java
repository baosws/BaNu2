package com.company.banu.ShowProfile;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.Log;

import com.company.banu.Backend;
import com.company.banu.CallBack;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class ModelShowProfile {
    PresenterShowProfile presenter;

    public ModelShowProfile(PresenterShowProfile presenter) {
        this.presenter = presenter;
    }
    public void getAvatar(CallBack cb) {
        Backend.downloadAvatar(cb);
    }

    public String getUserName() {
        return FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
    }

    public String getUID()
    {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public String getEmail() {
        return FirebaseAuth.getInstance().getCurrentUser().getEmail();
    }

    public void uploadAvatar(final String filename, Bitmap chosenAvatar) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        if (chosenAvatar != null) {
            Backend.putCache(filename, chosenAvatar);
            StorageReference ref = storage.getReference();
            final StorageReference avatarRef = ref.child(filename);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            chosenAvatar.compress(Bitmap.CompressFormat.JPEG, 60, baos);

            byte[] data = baos.toByteArray();
            UploadTask uploadTask = avatarRef.putBytes(data);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Log.d("btag", "avatar uploaded succeed: " + avatarRef.getName());
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("btag", "avatar upload failed " + avatarRef.getName());
                }
            });
        }
    }
}
