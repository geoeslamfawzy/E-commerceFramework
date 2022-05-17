package trial;

public class Trial {
    public static void main(String[] args) throws Exception{
        String reviews = "2 review(s)";
        int numberOfReviews = Integer.parseInt(reviews.replaceAll("[\\D]", ""));
        System.out.println(numberOfReviews);
    }
}
