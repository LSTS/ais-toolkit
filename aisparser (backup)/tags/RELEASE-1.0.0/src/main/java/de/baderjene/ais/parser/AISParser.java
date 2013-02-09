package de.baderjene.ais.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The processor is the central place to send your ais messages. It's implemented as an observer which sends notifications when complete
 * messages were parsed. Use one processor per data source, otherwise the messages could get corrupted.
 * 
 * @author Patrick Gotthard <mailto:patrick.gotthard@bader-jene.de>
 * 
 */
public class AISParser {

    private final Map<ID, MessageAssembler> incompleteMessages = new HashMap<ID, MessageAssembler>();
    private final List<AISObserver> observers = new ArrayList<AISObserver>();

    /**
     * Parses a raw ais packet.
     * 
     * @param raw Raw AIS packet
     * @throws InvalidPacketException when the packet is not valid ais packet.
     * @throws InvalidChecksumException when the checksum of the packet is invalid.
     */
    public void process(final String raw) throws InvalidPacketException, InvalidChecksumException {

        final Packet packet = new PacketParser().parse(raw);

        if (packet.getTotalPackets() == 1) { // single part message
            final MessageAssembler assembler = new MessageAssembler();
            assembler.addPacket(packet);
            notifyObservers(assembler.assemble());

        } else { // multi part message

            final ID id = new ID();
            id.setChannel(packet.getChannel());
            id.setSeqNum(packet.getSeqNum());

            MessageAssembler assembler = incompleteMessages.get(id);

            if (assembler == null || packet.getCurrentPacket() == 1) {

                assembler = new MessageAssembler();
                assembler.addPacket(packet);
                incompleteMessages.put(id, assembler);

            } else {

                assembler.addPacket(packet);

                if (assembler.isMessageComplete()) {

                    incompleteMessages.remove(id);
                    notifyObservers(assembler.assemble());

                }

            }
        }
    }

    /**
     * Register an observer.
     * 
     * @param observer The observer
     */
    public void register(final AISObserver observer) {
        observers.add(observer);
    }

    /**
     * Unregister an observer.
     * 
     * @param observer The observer
     */
    public void unregister(final AISObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notify observers when complete messages were parsed.
     * 
     * @param message the parsed ais message
     */
    private void notifyObservers(final Message message) {
        final int type = message.getAisMessageType();
        if (type == 1 || type == 2 || type == 3) {
            final PositionReportDTO report = new PositionReportParser().parse(message);
            for (final AISObserver observer : observers) {
                observer.update(report);
            }
        } else if (type == 5) {
            final VesselDataDTO data = new VesselDataParser().parse(message);
            for (final AISObserver observer : observers) {
                observer.update(data);
            }
        }
    }
}
