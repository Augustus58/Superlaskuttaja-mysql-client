## Superlaskuttaja - mysql client
Ensimmäinen testiversio valmis. Tällä hetkellä käyttöliittymän kuuntelijoissa on logiikkaan kuuluvaa koodia, injektiosuojausta ei ole ja mysql:n mahdollistamaa transaktioiden hallintaa ei ole otettu käyttöön. Edellisiin kohtiin tulee päivitystä jos/kun projektiin liikenee aikaa. Client on kuitenkin tällä hetkellä omassa käytössä ja parantelen sitä pikkuhiljaa. Tietokanta ja client on suunniteltu siten, että laskujen arkistointi tietokantatasolla on mahdollista. Eli jos muokkaa asiakkaan tietoja, niin muokkaus ei vaikuta jo muodostettuihin laskuihin. Ominaisuus ei olisi välttämätön, mutta tarjoaa lisäturvaa tositteiden palauttamiseen jos pdf-versiot tai tulostetut versiot jostain syystä katoaa. Ominaisuus on toteutettu versioimalla laskuttaja ja asiakkaat.
## Jos haluat kokeilla clienttiä
- Jos koneellasi ei ole javan ajoympäristöä eli JRE:tä asennettuna, niin asenna se osoitteesta https://java.com/en/download/
- Lataa juuressa oleva "Superlaskuttaja-1.0-SNAPSHOT-jar-with-dependencies" - niminen tiedosto
- Hanki Mysql-tietokanta jossa on jokin käyttäjä ja käyttäjällä on tietokanta jonka nimi on sama kuin käyttäjän. Huolehdi, että käyttäjällä on kaikki oikeudet omiin tietokantoihinsa.
- Aja lataamasi tiedosto komentoriviltä komennolla: `java -jar Superlaskuttaja-1.0-SNAPSHOT-jar-with-dependencies.jar` tai kaksoisklikkaa lataamaasi tiedostoa
- Kun client käynnistyy, niin klikkaa "Aseta tietokanta" alhaalta ja syötä palvelimen osoite, käyttäjätunnus ja salasana. Jos valitset "muista", niin ota huomioon, että tunnus ja salasana tallentuu selväkielisenä samaan kansioon, missä itse client on
- Kokeile clienttiä lisäämällä ensin laskuttajan tiedot, sitten vähintään yksi asiakas jne.

## Aihemäärittely
### Aihe
Laskutusohjelma. Toteutetaan ohjelma, jolla voidaan laskuttaa asiakkaita. Ohjelmaan tulee syöttää laskuttajan ja asiakkaiden tiedot. Kun edelliset tiedot on syötetty voidaan ohjelmaan lisätä asiakaskohtaisia suoritteita. Ohjelmalla voidaan laskuttaja-, asiakas -ja suoritetietojen pohjalta tehdä laskuja. Laskut voidaan viedä pdf-muotoon lähetettäväksi sähköpostilla tai muuten. Ohjelma huolehtii mm. laskujen laskujen numeroinnista kirjanpitoa varten ja viitteiden automaattisesta luomisesta asiakasnumeron ja asiakaskohtaisen laskunumeron perusteella.

### Käyttäjät
- Laskuttaja

### Laskuttajan toiminnot
#### Yhteenveto-osio
- Lisää laskuttajan tiedot
  - Onnistuu jos laskuttajan tietoja ei ole vielä lisätty
- Muokkaa laskuttajan tietoja
  - Onnistuu jos laskuttajan tiedot on lisätty
- Uusi laskutustiedosto
  - Uusi laskutustiedosto ilman mitään syötettyjä tietoja
  - Kysyy tarvittaessa tallennetaanko avoinna ollut laskutustiedosto
- Avaa laskutustiedosto
  - Avaa halutun laskutustiedoston
  - Kysyy tarvittaessa tallennetaanko avoinna ollut laskutustiedosto
- Tallenna laskutustiedosto

#### Asiakkaat-osio
- Näytä kriteeritekstin sisältävät
  - Voidaan valita valikosta kriteeri, syöttää kriteeriteksti ja etsiä kriteeritekstin sisältävät asiakkaat
- Näytä kaikki asiakkaat
  - Jos on etsitty asiakkaita tietyllä kriteerillä ja osa on rajautunut pois voidaan tällä näyttää kaikki asiakkaat
- Lisää asiakas
  - Onnistuu jos laskuttajan tiedot on syötetty
- Muokkaa valittua asiakasta
- Poista valittu asiakas
  - Kysyy varmistuksen

#### Suoritteet-osio
- Näytä kriteeritekstin sisältävät
  - Voidaan valita valikosta kriteeri, syöttää kriteeriteksti ja etsiä kriteeritekstin sisältävät suoritteet
- Näytä kaikki suoritteet
  - Jos	on etsitty suoritteita tietyllä kriteerillä ja osa on rajautunut pois voidaan tällä näyttää kaikki suoritteet
- Lisää suorite
  - Onnistuu jos vähintään yhden asiakkaan tiedot on syötetty
- Lisää suorite valitusta
  - Voidaan lisätä uusi suorite käyttäen valittua suoritetta pohjana
- Muokkaa valittua suoritetta
- Poista valittu suorite
 - Kysyy varmistuksen

#### Laskut-osio
- Näytä kriteeritekstin sisältävät
  - Voidaan valita valikosta kriteeri, syöttää kriteeriteksti ja etsiä kriteeritekstin sisältävät suoritteet
- Näytä kaikki suoritteet
 - Jos on etsitty laskuja tietyllä kriteerillä ja osa on rajautunut pois voidaan tällä näyttää kaikki laskut
- Lisää lasku
  - Onnistuu jos on olemassa laskuttamattomia suoritteita
- Muokkaa valittua laskua
- Vie valittu lasku pdf
- Poista valittu lasku
  - Kysyy varmistuksen