package phase1.SourceCode;

public class Sponsor {
    private String sponsorID;
    private double contribution;

    public Sponsor(String sponsorID, double contribution) {
        this.sponsorID = sponsorID;
        this.contribution = contribution;
    }

    public String getSponsorID() {
        return sponsorID;
    }

    public double getContribution() {
        return contribution;
    }
}