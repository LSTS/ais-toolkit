package de.baderjene.aistoolkit.aisparser.message;

/**
 * An AIS message consists of one or more AIS fragments. This class represents such a fragment.
 * 
 * @author Patrick Gotthard <mailto:patrick.gotthard@bader-jene.de>
 * 
 */
public class Fragment {

    private int totalFragments;
    private int currentFragment;
    private String payload;

    public int getTotalFragments() {
        return totalFragments;
    }

    public void setTotalFragments(final int totalFragments) {
        this.totalFragments = totalFragments;
    }

    public int getCurrentFragment() {
        return currentFragment;
    }

    public void setCurrentFragment(final int currentFragment) {
        this.currentFragment = currentFragment;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(final String payload) {
        this.payload = payload;
    }

}
