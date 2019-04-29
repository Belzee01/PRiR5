import java.util.Optional;

public interface AccountingSystemInterface {

    /**
     * Metoda rejestruje telefon w systemie. KaĹźdy telefon musi byÄ zarejestrowany
     * przed uĹźyciem.
     *
     * @param number unikalny numer rejestrowanego telefonu
     * @param phone  interfejs pozwalajÄcy na komunikacjÄ z telefonem
     */
    public void phoneRegistration(String number, PhoneInterface phone);

    /**
     * Wykupienie abonamentu dla podanego numeru telefonu na okreĹlony czas
     * poĹÄczenia. Czas poĹÄczenia podawany jest milisekundach.
     *
     * @param number numer telefonu, ktĂłrego transakcja dotyczy
     * @param time   wykupiony czas poĹÄczenia w msec
     * @return sumaryczny, pozostaĹy do dyspozycji czas poĹÄczenia dostÄpny dla
     *         uĹźytkownika telefonu o numerze number. Wynik podawany jest w msec i
     *         uwzglÄdnia poprzednie zakupy i poĹÄczenia (w tym aktualnego o ile
     *         takie istnieje).
     */
    public long subscriptionPurchase(String number, long time);

    /**
     * Metoda zwraca pozostaĹy do uĹźycia czas poĹÄczeĹ z numeru number. Wynik
     * podawany jest w msec.
     *
     * @param number numer telefonu, dla ktĂłrego zwracany jest czas pozostajÄcy
     *          do dyspozycji uĹźytkownika.
     * @return jeĹli nie zarejestrowano podanego numeru telefonu metoda zwraca pusty
     *         obiekt Optional. JeĹli numer jest zarejestrowany to wykonane metoda
     *         zwraca pozostaĹy do uĹźycia czas. Wynik musi uwzglÄdniaÄ zarĂłwno
     *         wszystkie zakupy jak i zakoĹczone poĹÄczenia, w tym poĹÄczenie
     *         aktualne (jeĹli istnieje).
     */
    public Optional<Long> getRemainingTime(String number);

    /**
     * Metoda pozwala zgĹosiÄ rozpoczÄcie poĹÄczenie pomiÄdzy numerem numberFrom
     * oraz numberTo. PoĹÄczenie jest rozpoczynane jeĹli oba numery sÄ
     * zarejestrowane, uĹźytkownik numeru numberFrom posiada wiÄkszÄ od 0 liczbÄ
     * msec, ktĂłrych moĹźna uĹźyÄ do prowadzenia rozmĂłw oraz numer numberTo nie jest
     * uĹźyty w innym poĹÄczeniu.
     *
     * @param numberFrom numer, ktĂłry nawiÄzuje poĹÄczenie
     * @param numberTo   numer, do ktĂłrego nawiÄzywane jest poĹÄczenie.
     * @return true jeĹli poĹÄczenie udaĹo siÄ nawiÄzaÄ, false - w przeciwnym
     *         przypadku
     */
    public boolean connection(String numberFrom, String numberTo);

    /**
     * RozĹÄczenie poĹÄczenia. PoĹÄczenie moĹźe rozĹÄczyÄ dowolny z telefonĂłw z
     * poĹÄczonej pary. RozĹÄczenie poĹÄczenia koĹczy pomiar czasu - z chwilÄ
     * zakoĹczenia metody disconnection, metoda getBilling musi uwzglÄdniaÄ rĂłwnieĹź
     * czas wĹaĹnie zakoĹczonego poĹÄczenia.
     * @param number numer telefonu, ktĂłry zleca rozĹÄczenie poĹÄczenia
     */
    public void disconnection(String number);

    /**
     * Metoda zwraca (o ile istnieje) sumaryczny czas zakoĹczonych poĹÄczeĹ z
     * telefonu numberFrom do telefonu o numerze numberTo.
     *
     * @param numberFrom numer, dla ktĂłrego czas poĹÄczeĹ jest zwracany
     * @param numberTo   numer, do ktĂłrego byĹy wykonywane poĹÄczenie telefoniczne
     * @return W przypadku gdy numer numberFrom i/lub numberTo jest nieznany
     *         zwracany jest obiekt Optional nie zawierajÄcy danych (pusty). W innym
     *         przypadku zwracany jest sumaryczny czas poĹÄczeĹ.
     */
    public Optional<Long> getBilling(String numberFrom, String numberTo);

    /**
     * Metoda zwraca informacjÄ o stanie poĹÄczenia do/z danego numeru telefonu.
     * JeĹli numer nie jest znany systemowi zwracany jest pusty obiekt Optional.
     * JeĹli numer jest zajÄty zwracany jest obiekt Optional zawierajÄcy true. JeĹli
     * numer nie jest zajÄty poĹÄczeniem zwracamy jest obiekt Optional zawierajÄcy
     * false.
     *
     * @param number numer telefonu
     * @return jeĹli numer jest znany to stan poĹÄczenia z/do tego numeru. JeĹli
     *         number nie jest systemowi znany, to zwracany jest pusty obiekt
     *         Optional.
     */
    public Optional<Boolean> isConnected(String number);
}