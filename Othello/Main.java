import java.util.Scanner;
class Main{
	public static void main(String[] args){
		Plateau grille = new Plateau(); //création de plusieurs attributs servant au jeu
		boolean testGrillePleine = grille.plein();
		Scanner sc = new Scanner(System.in);
		int tourJoueur=1;
		int blocage=0;
		
		grille.getXY(3,3).setJoueur(2); //pose des pions de départ
		grille.getXY(4,4).setJoueur(2);
		grille.getXY(3,4).setJoueur(1);
		grille.getXY(4,3).setJoueur(1);

		while(!testGrillePleine && blocage!=2){ //tant que la grille n'est pas remplie et que c'est toujours possible de jouer...
			boolean tour;
			testGrillePleine = grille.plein();
			if (testGrillePleine){ //si la grille est pleine, on interdit le prochain joueur de jouer
				tour=true;
			}
			else{ //sinon on laisse jouer le prochain joueur
				tour=false;
				
			}

			boolean testPossibleJouer=false; // partie qui va servir a déterminer si le joueur actuel peut jouer au moins 1 pion ou non
			
			for(int i=0; i<8; i++){
				for(int j=0; j<8; j++){
					boolean testCasePossible=grille.Rejoue(i,j,tourJoueur); //on teste toutes les cases du plateau pour savoir si le joueur x peut jouer ou non
					if(testCasePossible==true && grille.getXY(j,i).getJoueur()==0){  //si le joueur x peut jouer alors on le laisse jouer
						testPossibleJouer=true;
					}
				}
			}

			if(testPossibleJouer==false){ // si le joueur ne peut pas jouer alors on change de joueur et on vient vérifier si l'autre jouer peut jouer lui aussi
				System.out.println("Desole, aucun pion ne peut etre place. Changement de joueur.");

				if (tourJoueur==1){ //on alterne entre les deux joueurs
				tourJoueur=2;
				}
				else{
				tourJoueur=1;
				}
				
				blocage=1;

				for(int i=0; i<8; i++){
					for(int j=0; j<8; j++){
						boolean testCasePossible=grille.Rejoue(i,j,tourJoueur); //on teste toutes les cases du plateau pour savoir si l'autre joueur peut jouer ou non
						if(testCasePossible==true && grille.getXY(j,i).getJoueur()==0){ //si il peut jouer sur une case alors on le laisse joueur et on ne bloque pas le jeu
							testPossibleJouer=true;
							blocage=0;
						}
					}
				}

				if(testPossibleJouer==false){ // si l'autre joueur ne peut pas jouer alors, la partie est terminée et on bloque le jeu
					System.out.println("Desole, aucun pion ne peut etre place. Fin de partie.");
					blocage=2;
				}	

			}

			while(!tour && blocage!=2){ //tant que le jeu n'est pas bloqué et que le tour n'est pas terminé, on laisse le joueur x jouer
				System.out.println(grille);
				System.out.println("C'est au tour du joueur num "+tourJoueur);
				System.out.println("Entrez un chiffre entre 0 et 7 pour la valeur de X :");
				String nombreX = sc.next();
				while(!(nombreX.equals("0")) && !(nombreX.equals("1")) && !(nombreX.equals("2")) && !(nombreX.equals("3"))
				&& !(nombreX.equals("4")) && !(nombreX.equals("5")) && !(nombreX.equals("6")) && !(nombreX.equals("7"))){ // tant que la saisie est incorrecte, on redemande de saisir la valeur de X
					System.out.println("Erreur : Saisie incorrecte, veuillez reessayer.");
					System.out.println("Entrez un chiffre entre 0 et 7 pour la valeur de X :");
					nombreX = sc.next();
				}
				Integer nbX = new Integer(nombreX);
				int x=nbX.intValue();

				System.out.println("Entrez un chiffre entre 0 et 7 pour la valeur de Y :");
				String nombreY = sc.next();
				while(!(nombreY.equals("0")) && !(nombreY.equals("1")) && !(nombreY.equals("2")) && !(nombreY.equals("3")) 
				&& !(nombreY.equals("4")) && !(nombreY.equals("5")) && !(nombreY.equals("6")) && !(nombreY.equals("7"))){ // tant que la saisie est incorrecte, on redemande de saisir la valeur de Y
					System.out.println("Erreur : Saisie incorrecte, veuillez reessayer.");
					System.out.println("Entrez un chiffre entre 0 et 7 pour la valeur de Y :");
					nombreY = sc.next();
				}
				Integer nbY = new Integer(nombreY);
				int y=nbY.intValue();

				if(grille.getXY(y,x).getJoueur()==0 && grille.testPosePion(x,y,tourJoueur)){ //on insère le symbole si la case est vide et que c'est possible de placer le pion correspondant
					tour=true;
					grille.getXY(y,x).setJoueur(tourJoueur);
					
					if (tourJoueur==1){ //on alterne entre les deux joueurs
						tourJoueur=2;
					}
					else{
						tourJoueur=1;
					}
				}
				else if(grille.getXY(y,x).getJoueur()!=0){ // sinon on montre qu'il y a une erreur car la case est prise
					System.out.println("Erreur : la case souhaitee est deja prise, resaisissez vos coordonnees.");
				}
				else{ //ou encore, sinon on montre que le joueur ne respecte pas les règles et qu'il faut changer de coordonnées
					System.out.println("Erreur : placement impossible, veuillez reessayer !");
				}
			}

		}

		System.out.println(grille); //on affiche la grille finale
		int compteur1=0; //compteurs qui comptent le nombre de pions de chaque joueur
		int compteur2=0;
		for(int i=0; i<8; i++){ //on parcoure chaque case pour compter le nombre de jetons de chaque joueurs
			for(int j=0; j<8; j++){
				
				if(grille.getXY(j,i).getJoueur()==1){
					compteur1=compteur1+1;
				}
				else if(grille.getXY(j,i).getJoueur()==2){
					compteur2=compteur2+1;
				}
			}
		}

		

		System.out.println("Pions du joueur 1 (croix) : "+compteur1);  //on affiche qui est le gagnant en fonction de qui a le plus de jetons
		System.out.println("Pions du joueur 2 (ronds) : "+compteur2);
		if(compteur1>compteur2){
			System.out.println("Le joueur 1 (croix) a gagne !!");
		}
		else if(compteur2>compteur1){
			System.out.println("Le joueur 2 (ronds) a gagne !!");
		}
		else{
			System.out.println("Personne ne gagne, Egalite !!");
		}
	}
}	