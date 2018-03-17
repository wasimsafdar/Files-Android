package dk.humma.www.files_datastorage_async;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import io.karim.MaterialTabs;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends SetPermissionActivity{

    @Override
    protected String[] getDesiredPermissions() {
        return(new String[]{WRITE_EXTERNAL_STORAGE});
    }

    @Override
    protected void onPermissionDenied() {
        Toast
                .makeText(this, R.string.msg_sorry, Toast.LENGTH_LONG)
                .show();
        finish();
    }

    @Override
    protected void onReady(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        ViewPager pager=(ViewPager)findViewById(R.id.pager);

        pager.setAdapter(new SamplePageAdapter(this, getSupportFragmentManager()));

        MaterialTabs tabs=(MaterialTabs)findViewById(R.id.tabs);
        tabs.setViewPager(pager);
    }


}


