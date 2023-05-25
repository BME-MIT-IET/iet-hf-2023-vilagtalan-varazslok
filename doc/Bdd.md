Motyovszki András: </br>
A bdd teszteket a cucumber segítségével készítettük el. Először felmentem a cucumber.io oldalra, az ott leírtak alapján módosítottam a pom.xml-t, hogy a projekt tudja futtatni a cucumber teszteket. A beüzelemelés nem sikerült első próbálkozásra, mert hiányzott néhány junit-os dependency, surefire plugin. A megfelelő dependency-k beállítása után is volt még probléma, a mappaszerkezetből eredően. Ennek megoldásához törölni kellet a .classpath és a .project fileokat, majd kijavítani a packageneveket a java fileokban (ProjLabVilagtalan-ra).

A pom.xml:
![](bdd_pompom.png)

Ezek után sikeresen felismerte a projekt a szükséges dependencyket, alkalmas lett cucumber tesztek futtatására.

Majd megírtam a következő teszteket:
1. use_virus_on_virologist_with_gloves </br>
    Virológus vírust ken, a másiknak kesztyűje van </br>
    Leírás: Két virológus van ugyanazon mezőn, az egyikük Chorea vírust használ a másikon, aki a kesztyűjével visszaveri. </br>
    Ellenőrzött funkcionalitás: Annak ellenőrzése, hogy a kesztyű vissza tud kenni.
2. use_virus_both_have_gloves </br>
    Virológus vírust ken, a mindkettőnek kesztyűje van </br>
    Leírás: Két virológus van ugyanazon mezőn, az egyikük Chorea vírust használ a másikon, aki a kesztyűjével visszaveri, de az elsőnek is van, így a hatás elveszik. </br>
    Ellenőrzött funkcionalitás: Annak ellenőrzése, hogy a két kesztyűnél a hatás elveszik.
3. glove_breaks </br>
    Kesztyű eltörik </br>
    Leírás: Két virológus van ugyanazon mezőn, az egyikük Chorea vírust használ a másikon, aki a kesztyűjével visszaveri (egy „élete” volt), a kesztyű eltörik. </br>
    Ellenőrzött funkcionalitás: Annak ellenőrzése, hogy a kesztyű el tud törni.
4. use_vaccine_on_virologist </br>
    Virológus vakcinát ken </br>
    Leírás: Két virológus van ugyanazon mezőn, az egyikük AntiChoreát használ a másikon. </br>
    Ellenőrzött funkcionalitás: Vakcinakenés alapműködését ellenőrzi.
5. virologist_moves_by_chorea </br>
    Chorea hatás </br>
    Leírás: Egy virológuson hat a Chorea, véletlenszerűen lép (itt most az elsőre sorrendben). </br>
    Ellenőrzött funkcionalitás: Chorea működését ellenőrzi.
6. created_agent_expires </br>
    Létrehozott ágens lejár </br>
    Leírás: Egy virológusnál van egy legyártott AntiStun, az lejár és eltűnik. </br>
    Ellenőrzött funkcionalitás: Annak ellenőrzése, hogy a lejáró ágensek eltűnnek.

Pósa Tamás Márton: </br>
A következő teszteket készítettem el:
1. virologist_can't_learn_something_twice </br>
    Virológus megpróbál olyan ágenst tanulni, amit már ismer, így nem tanulja meg mégegyszer </br>
    Leírás: A virológus egy laboratóriumban van, Choreát és AntiChoreát tanulna, de már ismeri őket, így nem tanulja meg őket még egyszer. </br>
    Ellenőrzött funkcionalitás: Annak ellenőrzése, hogy a virológus egyszerre csak egyszer ismerje az ágenseit.
2. virologist_steals </br>
    Virológus lop egy lebénult virológustól </br>
    Leírás: Két virológus van ugyanazon mezőn, az egyikük Stun hatása alatt, van nála egy köpeny és egy zsák, a másiknál egy zsák. Aki nem bénult, lop a másiktól: az anyagait és a köpenyt, a zsákot nem, mert már volt nála egy. </br>
    Ellenőrzött funkcionalitás: Annak ellenőrzése, hogy lehet lopni egy bénult virológustól, és hogy a lopás megfelelően működik.
3. virologist_cannot_steal </br>
    Bénultan nem működik a lopás </br>
    Leírás: Két virológus van ugyanazon mezőn, mindkettő Stun hatása alatt, az egyiknél van egy köpeny, a másik lopni próbál, de nem tud. </br>
    Ellenőrzött funkcionalitás: Annak ellenőrzése, hogy cselekvésképtelenül nem lehet lopni.


Le Ngoc Thai: </br>
A következő teszteket készítettem el:
1. virologist_cannot_move </br>
    Virológus megpróbál megpróbál egy másik mezőre lépni de bénítás hatás alatt áll. </br>
    Leírás:A virológus egy mezőre próbál lépni, de nem tud mert egy vírus hatása alatt áll. </br>
    Ellenőrzött funkcionalitás: Annak ellenőrzése, hogy a virológus nem tud továbblépni egy másik mezőre
2. virologist_pickup_backpack </br>
    Virológus felvesz egy hátizsákot itemnek. </br>
    Leírás: A virológus a shelter mezőre lép és felveszi a hátizsákot. </br>
    Ellenőrzött funkcionalitás: Annak ellenőrzése, hogy sikeresen felveszi a hátizsákot, ha az adott mezőre lép.
3. virologist_cannot_pickup_another_backpack </br>
    Nem lehet egy virológusnak két ugyanolyan itemje </br>
    Leírás:A virológus már rendelkezik egy hátizsákkal, de megpróbál mégegy hátizsákot felvenni. </br>
    Ellenőrzött funkcionalitás: Annak ellenőrzése, hogy csak egy hátizsákkal rendelkezhet a virológus.

Baczó Domonkos </br>
A következő teszteket készítettem el:
1. bear_infection </br>
    Virológus rálép eg ylaboratóriumra, és medvefertőzést kap</br>
    Leírás: A virológus egy laboratóriumra lép, van nála egy Forget vírus. A medvevírust elkapja, a Forget vírust elveszti.</br>
    Ellenőrzött funkcionalitás: Annak ellenőrzése, hogy megfertőződött, és csak az az egy vírus van rajta.
2. bear_infects_virologist </br>
    Egy medve megfertőz egy virológust. </br>
    Leírás: A medve egy szomszédos mezőről rálép egy másik virulógus mezejére (a Timer miatt), aki ettől elkapja a medvevírust. </br>
    Ellenőrzött funkcionalitás: Annak ellenőrzése, hogy a virológus valóban elkapja a medvevírust.
3. bear_dies </br>
    A medvét megölik baltaval </br>
    Leírás: Egy virológus a szomszédos mezőről egy medve mezőjére lép. Van nála egy balta, azzal automatikusan megöli a medvét. </br>
    Ellenőrzött funkcionalitás: Annak ellenőrzése, hogy a végén a mezőn csak egy virológus van, és ő a baltás.

A tesztelés közben belefutottunk, hogy bizonyos feltételek, Cucumber-es state definition-ök többször is újra felhasználhatóak lennének. Azonban egyszerre dologztunk a feladaton, és így a bonyolultabb merge conflictok elkerülése érdekében inkább mindenki külön hozta létre ezeket a definíciókat. Ez azt eredményezte, hogy bizonyos definíciókat más néven újra kellett írni, és csak egyénenként tudtuk újra felhasználni őket. Talán jobb lett volna mindent egy fájlban, vagy csak lokális változókkal kezelni az újrahasználhatóság érdekében. </br>
Illetve javítottam egy kisebb hibát, amikor Timer tick függvényében a for ciklus túlindexelte magát, adtam bele egy plusz peremfeltételt.





![](testsSuccessful.png)