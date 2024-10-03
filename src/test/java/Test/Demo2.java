package Test;

public class Demo2 {
    public static void main(String[] args) {
        String str1 = "12/01/2011";
        String str2 = "01/12/2024";
        int years = (Integer.parseInt(str2.split("/")[2]) - Integer.parseInt(str1.split("/")[2])) * 12;
        int months = Integer.parseInt(str2.split("/")[1]) - Integer.parseInt(str1.split("/")[1]);
        System.out.println(months + years);
    }
}
