A játék kezelőfelülete:
![](map.png)

Mozgás </br>
![](m1.png) </br>

Teszt előkészítése: új játák indítása 1 játékossal</br>
Tesztelési terv: A Move gombnál kiválasztottuk, hogy melyik mezőre akarunk lépni, és a gombra kattintunk. </br>
Teszt eredménye: sikeres, valóban a megadott mezőre lépett.</br>

Mozgásképtelenség </br>
![](m2.png) </br>

Teszt előkészítése: új játék két játékossal, az elsőt egy laboratóriumba navigáltuk (kék mező). Ezt addig csináltuk, amíg vagy Chorea, vagy Stun vírust találtunk (ez esetben Chorea), és nem kapunk medvevírust. Elkészítjük a vírust a Create agent gombbal a vírus kiválasztása után. Ezután a másik játékost a szomszédos mezőre navigáltuk.</br>
Tesztelési terv: A vírussal felszerelt játékost a másik játékos mezőjére léptetjük, és a Use virus gomb mellett kiválasztjuk a vírust és a céljátákost, majd megnyomjuk a gombot. Ezután a másik játékossal lépni próbálunk.</br>
Teszt eredménye: sikeres, a másik játékos valóban nem tudott lépni (csak Chorea esetén a köre végén, random, ahogy a játékban kell). Az akciópontjai viszont fogytak, de ez nem baj, mert cselekvésképtelen a virológus. </br>

