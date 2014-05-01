## Rakennekuvaus
### Logiikka
Logiikan keskeisin luokka on Lasku
#### Luokkaan Lasku liittyvät luokat
- Laskuttaja
  - Lasku saa tätä kautta laskuttajaan liittyvät tiedot
  - Tätä kautta myös tieto lähetettyjen laskujen yhteismäärästä ja sitä kautta laskulle tuleva laskunnumero
- Asiakas
  - Lasku saa tätä kautta asiakkaaseen liittyvät tiedot
  - Tätä kautta myös tieto kyseiselle asiakkaalle lähetettyjen laskujen määrästä ja sitä kautta tarvittava tieto laskulle tulevaa viitenumeroa varten
- Suorite
  - Vain suoritteita, jotka liittyvät laskun Asiakas-olioon
  - Lasku saa tätä kautta tiedot suoritteiden kuvauksista ja yhteissummista laskun summan laskemista varten
- LaskunSumma
  - Tänne talletetaan laskun summa
- Viite
  - Tänne talletetaan laskun viite
  - Viitteen muodostamiseen käytetään asiakasnumeroa ja tietoa asiakkaalle lähetettyjen laskujen määrästä
- GregorianCalendar
  - Tänne talletetaan päiväys -ja eräpäivätiedot
- Pankkiviivakoodi
  - Tämän muodostamiseen käytetään tietoja tilinumero, laskun summa, viite ja eräpäivä
  - Helpottaa asiakkaan laskun maksamista
- Tietovarasto
  - Lasku talletetaan tänne
- MerkkiJaMerkkijonoTarkistin
  - Laskun tietojen tarkistuksia varten

### Käyttöliittymä
Keskeisin luokka on Kayttoliittyma
#### Luokkaan Käyttoliittyma liittyvät tärkeimmät luokat
- NappulaLukko
  - Tätä luokkaa tarvitaan siihen, että esim. asiakasta ei voi poistaa kesken laskun luomisen
- JTabbedPane tappedPane
  - Käyttöliittymän päänavigointia varten
- JPanel yhteenvetoPanel
  - Ensimmäinen tappedPane:n paneeli
- JPanel asiakkaatPanel
  - Toinen tappedPane:n paneeli
- JPanel suoritteetPanel
  - Kolmas tappedPane:n paneeli
- JPanel laskutPanel
  - Neljäs tappedPane:n paneeli