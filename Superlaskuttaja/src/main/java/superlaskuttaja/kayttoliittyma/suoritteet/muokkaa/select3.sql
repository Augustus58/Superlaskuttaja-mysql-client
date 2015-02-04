select nimi, vastaanottaja, kuvaus, alkuaika, maara, maaranYksikot,
((1+0.01*alvProsentti)*aHintaVeroton) as yht, !isnull(lasku) as onkoLaskutettu,
suoritteenNumero
from Suorite, Asiakas
where vastaanottaja = asiakasnumero
and vastaanottajanVersio = versio
and suoritteenNumero = suoritteenNumero
