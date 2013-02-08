package de.baderjene.aistoolkit.aisparser.message;

import java.util.List;

/**
 * Represents an unparsed AIS message.
 * 
 * @author Patrick Gotthard <patrick.gotthard@bader-jene.de>
 * 
 */
public class RawMessage extends Message {

    private List<Fragment> fragments;
    private String binary;

    public List<Fragment> getFragments() {
        return fragments;
    }

    public void setFragments(final List<Fragment> fragments) {
        this.fragments = fragments;
    }

    public String getBinary() {
        return binary;
    }

    public void setBinary(final String binary) {
        this.binary = binary;
    }

}
