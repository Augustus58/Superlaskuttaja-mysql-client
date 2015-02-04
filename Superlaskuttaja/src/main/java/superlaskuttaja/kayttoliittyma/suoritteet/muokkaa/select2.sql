select distinct nimi, asiakasnumero
from Asiakas A
where versio = (select max(versio)
from Asiakas where asiakasnumero = A.asiakasnumero)
