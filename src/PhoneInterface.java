/**
 * Bardzo prosty interfejs pozwalajÄcy na przekazanie informacji z systemu do
 * telefonu
 */
public interface PhoneInterface {
    /**
     * Informacja o prĂłbie nawiÄzania poĹÄczenia z numeru number. Metoda koĹczy
     * pracÄ, gdy uĹźytkownik telefonu zdecyduje co zrobiÄ - odebraÄ czy odrzuciÄ
     * poĹÄczenie. Nie ma gwarancji, Ĺźe stanie siÄ to natychmiast.
     *
     * @param number numer, z ktĂłrego nawiÄzywane jest poĹÄczenie
     * @return true - poĹÄczenie zostaĹo zaakceptowane, od tego momentu poĹÄczenie
     *         jest uznawane za zestawione i rozliczny jest czas poĹÄczenia. false -
     *         odrzucono poĹÄczenie.
     */
    public boolean newConnection(String number);

    /**
     * Metoda przekazuje informacjÄ o zakoĹczeniu poĹÄczenia z numerem telefonu
     * number.
     *
     * @param number numer telefonu, z ktĂłrym prowadzona byĹa wĹaĹnie zakoĹczona rozmowa.
     */
    public void connectionClosed(String number);
}