package de.baderjene.ais.parser;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * This class is used for keys of partial messages in a HashSet.
 * 
 * @author Patrick Gotthard <mailto:patrick.gotthard@bader-jene.de>
 * 
 */
class ID {

    private String channel;
    private int seqNum;

    public String getChannel() {
        return channel;
    }

    public void setChannel(final String channel) {
        this.channel = channel;
    }

    public int getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(final int seqNum) {
        this.seqNum = seqNum;
    }

    @Override
    public boolean equals(final Object obj) {
        boolean equals = false;
        if (obj != null && obj instanceof ID) {
            final ID id = (ID) obj;
            final EqualsBuilder builder = new EqualsBuilder();
            builder.append(channel, id.channel);
            builder.append(seqNum, id.seqNum);
            equals = builder.build();
        }
        return equals;
    }

    @Override
    public int hashCode() {
        final HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(channel);
        builder.append(seqNum);
        return builder.build();
    }
}
