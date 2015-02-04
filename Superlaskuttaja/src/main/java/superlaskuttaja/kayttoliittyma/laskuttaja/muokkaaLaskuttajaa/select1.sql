select distinct nimi, katuosoite, postinumero, kaupunki, yrityksenNimi, alvTunniste, tilinumero, tilinumeronPankki, tilinumeronSwiftBic, puhelinnumero, sahkopostiOsoite, laskujaLahetetty
from Laskuttaja
where versio = (select max(versio) from Laskuttaja)