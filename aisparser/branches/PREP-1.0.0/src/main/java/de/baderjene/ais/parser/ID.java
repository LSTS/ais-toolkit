package de.baderjene.ais.parser;

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
        return obj != null && obj instanceof ID && getChannel().equals(((ID) obj).getChannel()) && getSeqNum() == ((ID) obj).getSeqNum();
    }

    @Override
    public int hashCode() {
        return getSeqNum();
    }
}
