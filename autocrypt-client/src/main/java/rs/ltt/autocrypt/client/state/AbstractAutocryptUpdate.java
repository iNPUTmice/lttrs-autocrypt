package rs.ltt.autocrypt.client.state;

import java.time.Instant;
import org.bouncycastle.openpgp.PGPPublicKeyRing;
import rs.ltt.autocrypt.client.PGPKeyRings;

public abstract class AbstractAutocryptUpdate {

    private final String from;
    private final Instant effectiveDate;
    private final byte[] keyData;

    public AbstractAutocryptUpdate(
            final String from, final Instant effectiveDate, final byte[] keyData) {
        this.from = from;
        this.effectiveDate = effectiveDate;
        this.keyData = keyData;
    }

    public String getFrom() {
        return from;
    }

    public Instant getEffectiveDate() {
        return effectiveDate;
    }

    public byte[] getKeyData() {
        return keyData;
    }

    public PGPPublicKeyRing getPublicKeyRing() {
        return PGPKeyRings.readPublicKeyRing(keyData);
    }
}
