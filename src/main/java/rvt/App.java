package rvt;

public class App {
    public static void main(String[] args) {
        IOU mattsIOU = new IOU();
        mattsIOU.setSum("Martin", 51.5);
        mattsIOU.setSum("Martin", 10.5);

        System.out.println(mattsIOU.howMuchDoIOweTo("Martin"));
    }
}
