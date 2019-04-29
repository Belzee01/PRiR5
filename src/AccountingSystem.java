import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AccountingSystem implements AccountingSystemInterface {

    private Map<String, Subscription> registeredPhone;

    public AccountingSystem() {
        this.registeredPhone = Collections.synchronizedMap(new HashMap<>());
    }

    @Override
    public void phoneRegistration(String number, PhoneInterface phone) {
        this.registeredPhone.put(number, new Subscription(phone, 0L));
    }

    @Override
    public long subscriptionPurchase(String number, long time) {
        return this.registeredPhone.get(number).addRemainingTime(time);
    }

    @Override
    public Optional<Long> getRemainingTime(String number) {
        return Optional.of(this.registeredPhone.get(number).getRemainingTime());
    }

    @Override
    public boolean connection(String numberFrom, String numberTo) {
        return false;
    }

    @Override
    public void disconnection(String number) {

    }

    @Override
    public Optional<Long> getBilling(String numberFrom, String numberTo) {
        return Optional.empty();
    }

    @Override
    public Optional<Boolean> isConnected(String number) {
        return Optional.empty();
    }

    public static class Subscription {
        private PhoneInterface phoneInterface;
        private Long remainingTime;

        public Subscription(PhoneInterface phoneInterface, Long remainingTime) {
            this.phoneInterface = phoneInterface;
            this.remainingTime = remainingTime;
        }

        public PhoneInterface getPhoneInterface() {
            return phoneInterface;
        }

        public void setPhoneInterface(PhoneInterface phoneInterface) {
            this.phoneInterface = phoneInterface;
        }

        public Long getRemainingTime() {
            return remainingTime;
        }

        public long addRemainingTime(Long remainingTime) {
            this.remainingTime += remainingTime;
            return this.remainingTime;
        }
    }
}
