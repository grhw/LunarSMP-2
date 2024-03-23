package light.breeze;

import java.util.HashMap;
import java.util.Map;

public class CustomModelDatas {
    public static int getCustomModelData(String name)
    {
        return name.hashCode();
    }
}
