Baczó Domonkos: </br>
A manuális teszteléshez a dokumentáció use case-ein mentünnk végig. </br>
A játék kezelőfelülete:
![](map.png)

1. Mozgás </br>
![](m1.png) </br>

Teszt előkészítése: új játék indítása 1 játékossal</br>
Tesztelési terv: A Move gombnál kiválasztottuk, hogy melyik mezőre akarunk lépni, és a gombra kattintunk. </br>
Teszt eredménye: sikeres, valóban a megadott mezőre lépett.</br>

2. Mozgásképtelenség </br>
![](m2.png) </br>

Teszt előkészítése: új játék két játékossal, az elsőt egy laboratóriumba navigáltuk (kék mező). Ezt addig csináltuk, amíg vagy Chorea, vagy Stun vírust találtunk (ez esetben Chorea), és nem kapunk medvevírust. Elkészítjük a vírust a Create agent gombbal a vírus kiválasztása után. Ezután a másik játékost a szomszédos mezőre navigáltuk.</br>
Tesztelési terv: A vírussal felszerelt játékost a másik játékos mezőjére léptetjük, és a Use virus gomb mellett kiválasztjuk a vírust és a céljátákost, majd megnyomjuk a gombot. Ezután a másik játékossal lépni próbálunk.</br>
Teszt eredménye: sikeres, a másik játékos valóban nem tudott lépni (csak Chorea esetén a köre végén, random, ahogy a játékban kell). Az akciópontjai viszont fogytak, de ez nem baj, mert cselekvésképtelen a virológus. </br>

3. Tárgyfelvétel </br>
![](m3.png) </br>
Teszt előkészítése: új játék indítása 1 játékossal</br>
Tesztelési terv: A Move gombbal elnavigálunk egy narancssárga mezőre (óvóhely), ahol van egy tárgy (kis kör). A pick up gombbal felvesszük.</br>
Teszt eredménye: sikeres, a tárgy a tárgylistában megjelent, és az eldobható dolgok között is ott van, a térképről eltűnt a kis kör.</br>

4. Sikertelen tárgyfelvétel </br>
![](m4.png) </br>

Teszt előkészítése: 1. eset: Hasonló a második teszthez, csak az "áldozatot" egy óvóhelyre navigáljuk előtte. 2. eset: Egy játékossal összeszedünk háromféle tárgyat.</br>
Tesztelési terv: 1. eset: A vírussal felszerelt játékost a másik játékos mezőjére léptetjük, és a Use virus gomb mellett kiválasztjuk a vírust és a céljátákost, majd megnyomjuk a gombot. Ezután a másik játékossal megpróbálunk egy tárgyat felvenni. 2. eset: A játékossal elmegyünk egy negyedi óvóhelyre, és megpróbálunk tárgyat felvenni. Mindkét esetben az akció sikertelenségét várjuk.</br>
Teszt eredménye: sikeres, a játékosok nem tudtak tárgyat felvenni. Teli eszköztár esetén is fogytak az akciópontok, ez viszont nem felhasználóbarát.</br>

5. Aminosav felvétel </br>
![](m5.png) </br>
Teszt előkészítése: új játék indítása 1 játékossal</br>
Tesztelési terv: A Move gombbal elnavigálunk egy piros mezőre (aminosav-raktár),a pick up gombbal felvesszük.</br>
Teszt eredménye: sikeres, a nyersanyagok között megjelent még 50 aminosav.</br>



