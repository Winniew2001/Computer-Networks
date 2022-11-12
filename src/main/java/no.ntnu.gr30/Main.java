package no.ntnu.gr30;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting the application...");
        App app = new App();
        try{
            app.run();
        } catch (Exception e){
            System.out.println("Henloe" + e.getMessage());
        }
        System.out.println("Done B)");
    }
}