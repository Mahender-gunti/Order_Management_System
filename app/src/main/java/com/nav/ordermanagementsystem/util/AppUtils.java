
package com.nav.ordermanagementsystem.util;

import android.content.Context;
import android.widget.Toast;


public final class AppUtils {

    private AppUtils() {
        // This class is not publicly instantiable
    }


    /**
     * <strong>public void showAToast (String st)</strong></br>
     * this little method displays a toast on the screen.</br>
     * it checks if a toast is currently visible</br>
     * if so </br>
     * ... it "sets" the new text</br>
     * else</br>
     * ... it "makes" the new text</br>
     * and "shows" either or  
     * @param message the string to be toasted
     */

    public static void showAToast (Context context,String message){
        {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

        }
    }



}
