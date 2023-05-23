Baczó Domonkos: <br />
Az elején átnéztem a kód egy részét, de szabad szemmel komoly ahibát nem találtam (kisebb hibák voltak, például a kommentezés kódolása megváltozott, és így nem tudta megjeleníteni az ékezetes karaktereket, egy-két függvény túl hosszú vagy nem teljesen optimális). <br />
Ezután létrehoztam egy SonarCloud-analízist, amelyet összekötöttem a Github repositoryval. Ez nem volt egyszerű, mert csak artifactId megadásával sikerült. Az analízis pedig az első pár alakommal csak a pom.xml fájlra futott le, de aztán a mappaszerkezet megváltoztatása után már átvizsgálta az összes kódot, és talált 9 bugot, 7 security hotspot-ot és 190 code smell-t. <br />
Ezekből én javítottam ki a Random osztály használatából származó bugokat (hogy ne jöjjön létre mindig új), a code smellek közül pedig néhány blocker súlyosságút.

<br />
<br />
Le Ngoc Thai:
Én kijavitottam a Control osztály hibáit. Nem volt sok, csak a bufferedreadnél kellett a try with resource megoldásra átírni, amit ajánlott.

