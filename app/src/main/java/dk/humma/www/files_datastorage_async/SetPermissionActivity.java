package dk.humma.www.files_datastorage_async;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

/**
 * Created by Waseem on 17/03/2018.
 */

abstract public class SetPermissionActivity extends FragmentActivity {
    abstract protected String[] getDesiredPermissions();
    abstract protected void onPermissionDenied();
    abstract protected void onReady(Bundle state);

    private static final int REQUEST_PERMISSION=61125;
    private static final String STATE_IN_PERMISSION="inPermission";
    private boolean isInPermission=false;
    private Bundle state;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.state=savedInstanceState;

        if (state!=null) {
            isInPermission=state.getBoolean(STATE_IN_PERMISSION, false);
        }

        if (hasAllPermissions(getDesiredPermissions())) {
            onReady(state);
        }
        else if (!isInPermission) {
            isInPermission=true;

            ActivityCompat
                    .requestPermissions(this,
                            netPermissions(getDesiredPermissions()),
                            REQUEST_PERMISSION);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        isInPermission=false;
        if (requestCode==REQUEST_PERMISSION) {
            if (hasAllPermissions(getDesiredPermissions())) {
                onReady(state);
            }
            else {
                onPermissionDenied();
            }
        }
    }

    private boolean hasAllPermissions(String[] perms) {
        for (String perm : perms) {
            if (!hasPermission(perm)) {
                return(false);
            }
        }

        return(true);
    }

    protected boolean hasPermission(String perm) {
        return(ContextCompat.checkSelfPermission(this, perm)==
                PackageManager.PERMISSION_GRANTED);
    }

    private String[] netPermissions(String[] wanted) {
        ArrayList<String> result=new ArrayList<String>();

        for (String perm : wanted) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }

        return(result.toArray(new String[result.size()]));
    }
}























