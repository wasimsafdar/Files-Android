package dk.humma.www.files_datastorage_async;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Environment;
import android.support.v4.app.FragmentPagerAdapter;

import java.io.File;

/**
 * Created by Waseem on 17/03/2018.
 */

public class SamplePageAdapter extends FragmentPagerAdapter {

    private static final int[] TITLES={R.string.internal,
            R.string.external, R.string.pub};
    private static final int TAB_INTERNAL=0;
    private static final int TAB_EXTERNAL=1;
    private static final String FILENAME="test.txt";
    private final Context ctxt;

    public SamplePageAdapter(Context ctxt, FragmentManager mgr) {
        super(mgr);

        this.ctxt=ctxt;
    }

    @Override
    public int getCount() {
        return(3);

    }

    @Override
    public Fragment getItem(int position) {
        File fileToEdit;

        switch(position) {
            case TAB_INTERNAL:
                fileToEdit=new File(ctxt.getFilesDir(), FILENAME);
                break;

            case TAB_EXTERNAL:
                fileToEdit=new File(ctxt.getExternalFilesDir(null), FILENAME);
                break;

            default:
                fileToEdit=
                        new File(Environment.
                                getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
                                FILENAME);
                break;
        }

        return(EditorFragment.newInstance(fileToEdit));
    }

    @Override
    public String getPageTitle(int position) {
        return(ctxt.getString(TITLES[position]));
    }

}
