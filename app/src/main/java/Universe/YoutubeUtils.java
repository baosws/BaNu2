package Universe;

import android.util.Log;

public class YoutubeUtils {

    public static String GetCueFromLink(String url)
    {
        try
        {
            String[] parts = url.split("=|\\/");
            return parts[parts.length - 1];
        }
        catch (Exception e)
        {
            Log.e("Youtube loading", "Link error!");
        }
        return "";
    }
}
