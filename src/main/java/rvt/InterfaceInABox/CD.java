package rvt.InterfaceInABox;

public class CD implements Packable {
    private String artist;
    private String cdName;
    private int publicationYear;

    public CD(String artist, String cdName, int publicationyear) {
        this.artist = artist;
        this.cdName = cdName;
        this.publicationYear = publicationyear; 
    }

    public double weight() {
        return 0.1;
    }

    @Override
    public String toString() {
        return this.artist + ": " + this.cdName + " (" + this.publicationYear + ")";
    }
}
