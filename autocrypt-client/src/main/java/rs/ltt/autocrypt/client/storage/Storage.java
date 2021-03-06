package rs.ltt.autocrypt.client.storage;

import java.time.Instant;
import rs.ltt.autocrypt.client.header.EncryptionPreference;

public interface Storage {

    /**
     * Steps 1-2 of the update process 1) If the message’s effective date is older than the
     * peers[from-addr].autocrypt_timestamp value, then no changes are required, and the update
     * process terminates (returns false). 2) If the message’s effective date is more recent than
     * peers[from-addr].last_seen then set peers[from-addr].last_seen to the message’s effective
     * date.
     *
     * @param address The peer’s from address
     * @param effectiveDate The effective date of the message (sending time or the time of receipt
     *     if that date is in the future)
     * @return true if the effective data was more recent than the current autocrypt_timestamp
     */
    boolean updateLastSeen(final String address, final Instant effectiveDate);

    /**
     * Steps 4-6 of the update process. 4) Set peers[from-addr].autocrypt_timestamp to the message’s
     * effective date. 5) Set peers[from-addr].public_key to the corresponding keydata value of the
     * Autocrypt header. 6) Set peers[from-addr].prefer_encrypt to the corresponding prefer-encrypt
     * value of the Autocrypt header.
     *
     * @param address The peer’s from address
     * @param effectiveDate The effective date of the message (sending time or the time of receipt
     *     if that date is in the future)
     * @param publicKey The key-data from the Autocrypt header
     * @param preference The prefer-encrypt value of the Autocrypt header
     */
    void updateAutocrypt(
            final String address,
            final Instant effectiveDate,
            final byte[] publicKey,
            final EncryptionPreference preference);

    /**
     * Steps 2-4 from the gossip update process. 2) If peers[gossip-addr].gossip_timestamp is more
     * recent than the message’s effective date, then the update process terminates. 3) Set
     * peers[gossip-addr].gossip_timestamp to the message’s effective date. 4) Set
     * peers[gossip-addr].gossip_key to the value of the keydata attribute.
     *
     * @param address The peer’s address from the addr attribute
     * @param effectiveData The effective date of the message (sending time or the time of receipt *
     *     if that date is in the future)
     * @param publicKey The key-data from the Autocrypt-Gossip header
     * @return returns true if the gossip key has been updated
     */
    boolean updateGossip(final String address, final Instant effectiveData, final byte[] publicKey);

    PeerState getPeerState(final String address);

    AccountState getAccountState(final String userId);

    void setAccountState(final String userId, final AccountState accountState);
}
