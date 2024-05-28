package light.breeze.lunarsmp.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

public class Requests {
    public static String get( String strurl ) {
        String stuff = "";
        try {
            URL url = new URL( strurl );
            BufferedReader in = new BufferedReader( new InputStreamReader( url.openStream() ) );
            String str = "";
            List<String> broken = in.lines().toList();
            for ( int i = 0; i < broken.size(); i++ ) {
                str = str + "\n" + broken.get( i );
            }
            in.close();
            stuff = str;
        } catch ( java.io.IOException e1 ) {
            stuff = e1.getMessage();
        }
        return stuff;
    }
}

