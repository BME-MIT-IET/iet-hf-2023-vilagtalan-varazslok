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








![](testsSuccessful.png)