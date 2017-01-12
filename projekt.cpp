#include <ncurses.h>
#include <iostream>

using namespace std;

int wczytanie(string a, int max){
	int k;
	cout << a << "\n";
	cin >> k;
	while(cin.fail() || k < 0 || k >= max){
		cin.clear();
		cin.ignore(1000, '\n');
		cout << "Cos Ci nie poszlo, sprobuj jeszcze raz\n";
		cout << a << "\n";
		cin >> k;
	}
	return k;
}

void granica(int kolumny_max, int rzedy_max){
	mvprintw(rzedy_max/2, kolumny_max/2-22,"Wychodzisz poza ekran!");
}

void tworzenie_obiektu(int dol, int gora, int lewa, char znak[]){
	int t,r,p;
	t = lewa;
	r = gora;
	clear();
	for(p = dol; p>=gora; p--){
		mvprintw(p, t, znak);
		mvprintw(r, t, znak);
		t++;
		r++;
	}
}

char znak[1];
main(){
	int kolumny_min = 0;
	int rzedy_min = 0;
	int kolumny_max, rzedy, rzedy_max, wysokosc;
	initscr();
	keypad(stdscr, FALSE);
	getmaxyx(stdscr, rzedy_max, kolumny_max);
	mvprintw(rzedy_max-2, kolumny_min, "Podaj znak jakim chcesz rysowac obiekt:\n");
	znak[0] = getch();
	clear();
	endwin();
	rzedy = wczytanie("Podaj wielkosc obiektu", rzedy_max);
	initscr();
	keypad(stdscr, TRUE);
	wysokosc = rzedy_max - rzedy;
	int dol_obiektu = rzedy_max-1;
	int gora_obiektu = rzedy_max - 1 - rzedy;
	int prawa_strona = kolumny_min + rzedy;
	int lewa_strona = kolumny_min;
	tworzenie_obiektu(dol_obiektu, wysokosc, lewa_strona, znak);
	getch();
	char co_robic;
	mvprintw(0,0,"Co chcesz teraz zrobic z obiektem? Poprzesowac go po ekranie? - uzyj a,w,s,d, zmienic jego wymiary? - uzyj +/-, a moze po prostu wyjsc wtedy wcisnij q\n");
	do{
		co_robic = getch();
		if(co_robic == '+' || co_robic == '-'){
			if(co_robic == '+' && (gora_obiektu == rzedy_min || prawa_strona == kolumny_max-1)){
				granica(kolumny_max, rzedy_max);
			}
			else if(co_robic == '-' && (prawa_strona == lewa_strona)){
				mvprintw(rzedy_max/2, kolumny_max/2-30, "To minimalna wielkosc obiektu!");
			}
			else{
				switch(co_robic){
					case '-':
						gora_obiektu++;
						prawa_strona--;
						break;
					case '+':
						gora_obiektu--;
						prawa_strona++;
						break;
				}
				tworzenie_obiektu(dol_obiektu, gora_obiektu, lewa_strona, znak);
			}		
		}
		if(co_robic == 'w' || co_robic == 's'){
			if(co_robic == 'w' && gora_obiektu == rzedy_min){
				granica(kolumny_max, rzedy_max);
			}
			else if(co_robic == 's' && dol_obiektu == rzedy_max -1){
				granica(kolumny_max, rzedy_max);
			}
			else{
				switch(co_robic){
					case 'w':
						gora_obiektu--;
						dol_obiektu--;
						break;
					case 's':
						gora_obiektu++;
						dol_obiektu++;
						break;
				}
				tworzenie_obiektu(dol_obiektu, gora_obiektu, lewa_strona, znak);
			}
		}
		if(co_robic == 'a' || co_robic == 'd'){
			if(co_robic == 'a' && lewa_strona == kolumny_min){
				granica(kolumny_max, rzedy_max);
			}
			else if(co_robic == 'd' && prawa_strona == kolumny_max - 1){
				granica(kolumny_max, rzedy_max);
			}
			else{
				switch(co_robic){
					case 'a':
						lewa_strona--;
						prawa_strona--;
						break;
					case 'd':
						lewa_strona++;
						prawa_strona++;
						break;
				}
				tworzenie_obiektu(dol_obiektu, gora_obiektu, lewa_strona, znak);
			}
		}
	} while(co_robic != 113);
	endwin();
	return 0;
}
