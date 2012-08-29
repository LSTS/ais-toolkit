package de.baderjene.aistoolkit.aisparser;

import de.baderjene.aistoolkit.aisparser.message.Fragment;
import de.baderjene.aistoolkit.aisparser.message.Message;
import de.baderjene.aistoolkit.aisparser.message.RawMessage;
import de.baderjene.aistoolkit.aisparser.parser.FragmentParser;
import de.baderjene.aistoolkit.aisparser.parser.MessageParser;
import de.baderjene.aistoolkit.aisparser.parser.RawMessageAssembler;

/**
 * Parser for AIS messages. Register your observers to get notified about parsed messages. Use one parser per data source, otherwise the
 * messages get corrupted.
 * 
 * @author Patrick Gotthard <mailto:patrick.gotthard@bader-jene.de>
 * 
 */
public class AISParser extends AbstractAISPublisher {

    private final FragmentParser fragmentParser = new FragmentParser();
    private final RawMessageAssembler rawMessageAssembler = new RawMessageAssembler();

    /**
     * Parses AIS messages.
     * 
     * @param aisInput AIS input
     */
    public void process(final String aisInput) {
        final Fragment fragment = fragmentParser.parse(aisInput);
        if (fragment != null) {
            final RawMessage rawMessage = rawMessageAssembler.addFragment(fragment);
            if (rawMessage != null) {
                final Message message = new MessageParser(rawMessage).parse();
                if (message != null) {
                    notifyObservers(message);
                }
            }
        }
    }

}
