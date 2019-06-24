package Universe;

import android.content.Context;
import android.util.Log;

public class LoadImageResource {
    public static int getMipmapResIdByName(Context context, String resName)  {
        String pkgName = context.getPackageName();
        // Return 0 if not found
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("CustomGridView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }
}
