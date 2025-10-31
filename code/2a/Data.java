public class Data {
    public String[] saveData(String data) {
        if (data == null || data.isEmpty()) return new String[0];
        return data.trim().split("\\s+");
    }
}