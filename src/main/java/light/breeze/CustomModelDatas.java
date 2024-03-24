package light.breeze;

public class CustomModelDatas {
    public static int getCustomModelData(String name)
    {
        return Math.abs(name.hashCode());
    }
}
