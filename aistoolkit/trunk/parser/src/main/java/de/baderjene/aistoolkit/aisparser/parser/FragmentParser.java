package de.baderjene.aistoolkit.aisparser.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.baderjene.aistoolkit.aisparser.message.Fragment;

/**
 * Parser for AIS fragments.
 * 
 * @author Patrick Gotthard <mailto:patrick.gotthard@bader-jene.de>
 * @see Fragment
 */
public class FragmentParser {

    /**
     * Parse an AIS fragment.
     * 
     * @param aisInput An AIS fragment
     * @return The parsed fragment
     */
    public Fragment parse(final String aisInput) {

        Fragment fragment = null;

        // RegEx validation
        final Matcher matcher = Pattern.compile("\\!([A-Z]{5},(\\d),(\\d),\\d?,[AB12]?,(.*?),\\d)\\*([0-9A-F]{2})").matcher(aisInput);
        if (matcher.find()) {

            // Checksum validation
            int currentChecksum = 0;
            for (final char c : matcher.group(1).toCharArray()) {
                currentChecksum ^= c;
            }
            final int expectedChecksum = Integer.parseInt(matcher.group(5), 16);
            if (currentChecksum == expectedChecksum) {

                // Parse fragment
                final int totalFragments = Integer.parseInt(matcher.group(2));
                final int currentFragment = Integer.parseInt(matcher.group(3));
                final String payload = matcher.group(4);

                fragment = new Fragment();
                fragment.setTotalFragments(totalFragments);
                fragment.setCurrentFragment(currentFragment);
                fragment.setPayload(payload);
            }

        }

        return fragment;

    }

}
