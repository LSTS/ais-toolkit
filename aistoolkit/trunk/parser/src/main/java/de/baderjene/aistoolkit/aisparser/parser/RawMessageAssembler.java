package de.baderjene.aistoolkit.aisparser.parser;

import java.util.ArrayList;
import java.util.List;

import de.baderjene.aistoolkit.aisparser.message.RawMessage;
import de.baderjene.aistoolkit.aisparser.message.Fragment;

/**
 * Assembles one or more message fragments to a base AIS message object.
 * 
 * @author Patrick Gotthard <patrick.gotthard@bader-jene.de>
 * 
 */
public class RawMessageAssembler {

    private final List<Fragment> fragments = new ArrayList<Fragment>();

    /**
     * Adds a fragment to the assembler.
     * 
     * @param fragment An AIS message fragment
     * @return an assembled message or null if the message is not complete
     */
    public RawMessage addFragment(final Fragment fragment) {

        final int actualFragment = fragment.getCurrentFragment();
        final int totalFragments = fragment.getTotalFragments();
        final int expectedFragment = fragments.size() + 1;

        if (actualFragment == expectedFragment) {
            fragments.add(fragment);
        } else {
            fragments.clear();
            if (actualFragment == 1) {
                fragments.add(fragment);
            }
        }

        RawMessage message = null;
        if (totalFragments == fragments.size()) {
            message = new RawMessage();
            message.setFragments(new ArrayList<Fragment>(fragments));
            message.setBinary(getBinaryPayload());
            fragments.clear();
        }
        return message;
    }

    /**
     * Converts the payload to the binary form.
     * 
     * @return Binary representation of the payload
     */
    private String getBinaryPayload() {
        final StringBuilder builder = new StringBuilder();
        for (final Fragment fragment : fragments) {
            for (int i : fragment.getPayload().toCharArray()) {
                if (i < 89) {
                    i -= 48;
                } else {
                    i -= 56;
                }
                final StringBuilder binary = new StringBuilder(Integer.toBinaryString(i));
                while (binary.length() < 6) {
                    binary.insert(0, "0");
                }
                builder.append(binary);
            }
        }
        return builder.toString();
    }

}
