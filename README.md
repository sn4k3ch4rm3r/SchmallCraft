# SchmallCraft
## Menü
### New Game
Új játék indítása. Ki kell választanunk, hogy mi az a fájl, ahová majd menteni szeretnénk a játék állapotát.
### Load Game
Játék betöltése kiválasztott fájlból.

## Játék
### Irányítás
Mozgás
- `W` - felfelé
- `A` - balra
- `S` - lefelé
- `D` - jobbra

Inventory-ban görgővel választhatünk a slot-ok közül.

Bal egérgombbal tudunk blokkokat kiütni vagy entitásokat sebezni.

Jobb egérgombbal tudunk item-et vagy munkaállomást használni.

`C` lenyomásával tudjuk megnyitni a munkaállomás nélkül elérhető barkácsolás menüt.

`ESC` lenyomásával léphetünk ki a barkácsolás menüből vagy nyithatjuk meg a mentés/kilépés menüt.

### Barkácsolás
Különböző munkaállomásokban különböző típusú itemeket tudunk elkészíteni. Baloldalon az adott munkaállomásban elkészíthető itemek jelennek meg, jobb oldalon a kiválasztott item elkészítéséhez szükséges nyersanyagok. Kattintással készíthetjük el a kiválaszott itemet, ha van nálunk elég nyersanyag hozzá.

## Projekt fordítása
A projekt gradle-t használt.

Fordítás
```sh
./gradlew build
```

Futtatás
```sh
./gradlew run
```

## Fények
A játék a barlangokban alapból dinamikus fényeket jelenít meg, viszont ez a swing limitációi miatt nagyon lecsökkenti a frame rate-t, ezért lehetőség van kikapcsolni parancssori argumentummal.

```sh
./gradlew run --args="--no-lights"
```