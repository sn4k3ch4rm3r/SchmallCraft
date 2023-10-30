# Áttekintés
[Minicraft](https://en.wikipedia.org/wiki/Minicraft)-hoz hasonló 2D-s felülnézetű játék, amit eredetileg Notch, a Minecraft alkotója készített a 2011-es Ludum Dare game jam-re 48 óra alatt. Egy random generált világban kell nyersanyagokat gyűjtenünk, eszközöket készítenünk, különböző szörnyekkel megküzdenünk és végül legyőznünk a Tűzvarázslót.

# Játékmenet
A játék célja, hogy legyőzzük a Tűzvarázslót. Ehhez nyersanyagokat kell gyűjtenünk, először a felszínen, később pedig a barlangokba is lemerészkedhetünk. A felszínen juthatunk többek között fához, homokhoz, és elelemhez. A barlangokban találunk értékesebb nyersanyagokat, mint a különböző ércek és kristályok, azonban itt sokkal több szörnnyel kell megküzdenünk és a Tűzvarázslóval is itt találkozhatunk. A barlangokkal ellentétben a felszínen kevés szörnnyel találkozunk, és azt is csak éjszaka. 

A megszerzett nyersanyagokból különböző eszközöket készíthetünk, amelyek segítenek az értékesebb nyersanyagok megszerzésében. Ezen kívül készíthetünk még fegyvereket és páncélt, ami a szörnyekkel való küzdelemben segít.

A világ random generált, tavakkal, rétekkel és erdőkkel. Mivel a játék 2 dimenziós így a felszín és a barlangok között a világban random elhelyezett lépcsőkön tudunk közlekedni.

A játékos láthatja az életerejét és a fáradtságát. Életerőt veszít, amikor szörnyekkel harcol, ha eléri a 0-t akkor meghal. Fáradsága akkor növekszik amikor harcol vagy nyersanyagokat gyűjt. Ha túlságosan elfárad gyengébb lesz és lassabban tud mozogni. Életerőt étellel, energiáját pihenéssel tudja vissza tölteni.

# Grafika és környezet
A játék grafikája pixel art stílusú. Elsősorban a Java Swing primitív grafikai utasításait használja.

# Fájlkezelés
A játékosnak lehetősége van, hogy elmentse és később vissza töltse az aktuális világot, amiben épp játszik.

# A program felépítése
- **Főmenü**: Itt válaszhatjuk ki, hogy új játékot szeretnénk kezdeni, vagy egy korábbi játékot folytatni.
- **Játék**: Maga a játék a menüben kiválasztott világban.