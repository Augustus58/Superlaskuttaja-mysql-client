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