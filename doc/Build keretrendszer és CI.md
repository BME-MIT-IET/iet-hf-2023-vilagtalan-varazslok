Pósa Tamás Márton: <br />
Hozzáadtam a projekthez a Maven keretrendszert, és a pom fájlt.<br />
pom.xml:
![](pom_alap.png)

Motyovszki András: <br />
A github felületén létrehoztam egy github actiont a Continuous Integration-höz (Java with Maven). A projekthez hozzáadtam a maven.yml fájlt, mellyel felkonfiguráltam az ellenőzrést. Így minden github push után ellenőrzésre kerül, hogy le tudja-e buildelni a projektet.