package juliana.com.tmp.Control;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Juliana on 29/10/2016.
 */

public class Listener implements ValueEventListener{

    private String data;
    private boolean ready;

    public Listener(){
        ready=false;
    }

    public String getData(){
        return data;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        data= dataSnapshot.getValue(String.class);
        ready=true;
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

    public boolean isReady(){
        return ready;
    }
}
