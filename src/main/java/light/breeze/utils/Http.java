package light.breeze.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

public class Http {
    public static String get(String strurl) {
        String stuff = "";
        try {
            URL url = new URL(strurl);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String str = "";
            List broken = in.lines().toList();
            for (int i = 0; i < broken.size(); i++) {
                str = str + "\n" + broken.get(i);
            }
            in.close();
            if (str != null) {
                stuff = str;
            }
        }
        catch (java.io.IOException e1) {
            stuff = e1.getMessage();
        }
        return stuff;
    }
}
