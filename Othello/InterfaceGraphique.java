import MG2D.*;
import MG2D.geometrie.*;
import java.awt.Font;
class InterfaceGraphique{
	public static void main(String[] args){
		Plateau grille = new Plateau(); //création de plusieurs attributs servant au jeu de base
		boolean testGrillePleine = grille.plein();
		int tourJoueur=1;
		int blocage=0;

		Souris souris; //création de plusieurs attributs servant au jeu avec version graphique
		Fenetre f = new Fenetre ( "Mon Othello :)", 800,900);
		Point point1;
		Point point2;
		Rectangle traitGrille;

		souris = f.getSouris(); //permet l'utilisation de la souris


		grille.getXY(3,3).setJoueur(1); //pose des pions de départ
		grille.getXY(4,4).setJoueur(1);
		grille.getXY(3,4).setJoueur(2);
		grille.getXY(4,3).setJoueur(2);




		point1=new Point(0, 0); //remplissage de l'arrière plan (la couleur vert_fonce est une couleur que j'ai crée moi même sur MG2D qui correspond a rgb(46,139,87))
		point2=new Point(800, 900);
        traitGrille=new Rectangle(Couleur.VERT_FONCE, point1, point2, true);
        f.ajouter(traitGrille);

        /*création de la grille*/
        point1=new Point(0, 795); 
		point2=new Point(800, 805);
        traitGrille=new Rectangle(Couleur.NOIR, point1, point2, true);
        f.ajouter(traitGrille);

        /*lignes verticales*/
		for(int i=0;i<700;i=i+100){
        	point1=new Point(95+i, 0); 
			point2=new Point(105+i, 800);
        	traitGrille=new Rectangle(Couleur.NOIR, point1, point2, true);
        	f.ajouter(traitGrille);
        }

        /*lignes horizontales*/
       for(int i=0;i<700;i=i+100){
        	point1=new Point(0, 95+i); 
			point2=new Point(800, 105+i);
        	traitGrille=new Rectangle(Couleur.NOIR, point1, point2, true);
        	f.ajouter(traitGrille);
        }

        /*double boucle qui parcourt la grille pour voir ou il doit poser les pions de bases dans la grille*/
        for(int i=0; i<8; i++){ 
			for(int j=0; j<8; j++){
				
				if(grille.getXY(j,i).getJoueur()==1){
					point1=new Point(i*100+50, j*100+50); 
					Cercle rond = new Cercle(Couleur.NOIR, point1, 40, true);
					f.ajouter(rond);
					f.rafraichir();
				}
				else if(grille.getXY(j,i).getJoueur()==2){
					point1=new Point(i*100+50, j*100+50); 
					Cercle rond = new Cercle(Couleur.BLANC, point1, 40, true);
					f.ajouter(rond);
					f.rafraichir();
				}
			}
		}

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
					
					blocage=2;
				}	

			}

			if (tourJoueur==1){ // si c'est au tour du joueur 1, on écrit que c'est à lui de jouer
				point1=new Point(0, 800); 
				point2=new Point(800, 900);
        		traitGrille=new Rectangle(Couleur.VERT_FONCE, point1, point2, true); //on remplit la zone de texte de vert pour effacer le texte précédent
        		f.ajouter(traitGrille);

        		point1=new Point(400,850); 
				Texte t=new Texte(Couleur.NOIR, "C'est au joueur 1 de jouer", new Font("Calibri", Font.TYPE1_FONT, 40), point1); //création du texte pour tour joueur1
				f.ajouter(t);
				f.rafraichir();
			}
			else if(tourJoueur==2){// si c'est au tour du joueur 2, on écrit que c'est à lui de jouer
				point1=new Point(0, 800); 
				point2=new Point(800, 900);
        		traitGrille=new Rectangle(Couleur.VERT_FONCE, point1, point2, true); //on remplit la zone de texte de vert pour effacer le texte précédent
        		f.ajouter(traitGrille);

        		point1=new Point(400,850); 
				Texte t=new Texte(Couleur.BLANC, "C'est au joueur 2 de jouer", new Font("Calibri", Font.TYPE1_FONT, 40), point1); //création du texte pour tour joueur2
				f.ajouter(t);
				f.rafraichir();
			}
			
			while(!tour && blocage!=2){ //tant que le jeu n'est pas bloqué et que le tour n'est pas terminé, on laisse le joueur x jouer
				try{Thread.sleep(1);}catch(Exception e){} //permet de ralentir la boucle afin que le clic soit bien détecté

				int posx = souris.getPosition().getX(); // récupération des coordonnées x et y de la souris sur la fenêtre
				int posy = souris.getPosition().getY();

				if(posx>=0 && posx<=100){ // succession de if pour bien recentrer le cercle dans les cases x
					posx=50;
				}
				else if(posx>100 && posx <=200){
					posx=150;
				}
				else if(posx>200 && posx <= 300){
					posx=250;
				}
				else if(posx>300 && posx <= 400){
					posx=350;
				}
				else if(posx>400 && posx <= 500){
					posx=450;
				}
				else if(posx>500 && posx <= 600){
					posx=550;
				}
				else if(posx>600 && posx <= 700){
					posx=650;
				}
				else if(posx>700 && posx <= 800){
					posx=750;
				}

				if(posy>=0 && posy<=100){ // succession de if pour bien recentrer le cercle dans les cases y
					posy=50;
				}
				else if(posy>100 && posy <=200){
					posy=150;
				}
				else if(posy>200 && posy <= 300){
					posy=250;
				}
				else if(posy>300 && posy <= 400){
					posy=350;
				}
				else if(posy>400 && posy <= 500){
					posy=450;
				}
				else if(posy>500 && posy <= 600){
					posy=550;
				}
				else if(posy>600 && posy <= 700){
					posy=650;
				}
				else if(posy>700 && posy <= 800){
					posy=750;
				}

				
				if(souris.getClicGauche()){ // si le clic est détecté, on fait plusieurs actions
					int x=(int)posx/100; //x et y servent a remplir le tableau de l'attribut grille pour détecter quelle case est remplie ou non
					int y=(int)posy/100;

					if(grille.getXY(y,x).getJoueur()==0 && grille.testPosePion(x,y,tourJoueur)){ //on insère le symbole si la case est vide et que c'est possible de placer le pion correspondant
						tour=true;
						grille.getXY(y,x).setJoueur(tourJoueur);
						
						for(int i=0; i<8; i++){  //parcours de la grille pour savoir quels pions il faut placer sur le graphique
							for(int j=0; j<8; j++){
								if(grille.getXY(j,i).getJoueur()==1){
									point1=new Point(i*100+50, j*100+50); 
									Cercle rond = new Cercle(Couleur.NOIR, point1, 40, true);
									f.ajouter(rond);
									f.rafraichir();
								}
								else if(grille.getXY(j,i).getJoueur()==2){
									point1=new Point(i*100+50, j*100+50); 
									Cercle rond = new Cercle(Couleur.BLANC, point1, 40, true);
									f.ajouter(rond);
									f.rafraichir();
								}
							}
						}

						if (tourJoueur==1){ //on alterne entre les deux joueurs
							point1=new Point(posx, posy); // création des cercles noirs pour le joueur 1
							Cercle rond = new Cercle(Couleur.NOIR, point1, 40, true);
							f.ajouter(rond);
							f.rafraichir();
							tourJoueur=2;
						}
						else{
							point1=new Point(posx, posy); // création des cercles blancs pour le joueur 2
							Cercle rond = new Cercle(Couleur.BLANC, point1, 40, true);
							f.ajouter(rond);
							f.rafraichir();
							tourJoueur=1;
						}
					}

					
				}
				
			}	
		}

		point1=new Point(0, 800); 
		point2=new Point(800, 900);
        traitGrille=new Rectangle(Couleur.VERT_FONCE, point1, point2, true); //permet d'effacer le texte précédent
        f.ajouter(traitGrille);

		point1=new Point(400,880); 
		Texte t=new Texte(Couleur.NOIR, "Fin de partie !", new Font("Calibri", Font.TYPE1_FONT, 30), point1); //création du texte pour signifier que c'est la fin de partie
		f.ajouter(t);
		f.rafraichir();

		int compteur1=0; //compteurs qui comptent le nombre de pions de chaque joueur
		int compteur2=0;

		for(int i=0; i<8; i++){ //on parcourt chaque case pour compter le nombre de jetons de chaque joueurs
			for(int j=0; j<8; j++){
				
				if(grille.getXY(j,i).getJoueur()==1){
					compteur1=compteur1+1;
				}
				else if(grille.getXY(j,i).getJoueur()==2){
					compteur2=compteur2+1;
				}
			}
		}
		point1=new Point(200,860); 
		t=new Texte(Couleur.NOIR, "Noir : "+compteur1, new Font("Calibri", Font.TYPE1_FONT, 30), point1);  //texte qui affiche le compteur final de pions noirs
		f.ajouter(t);
		f.rafraichir();

		point1=new Point(600,860); 
		t=new Texte(Couleur.NOIR, "Blanc : "+compteur2, new Font("Calibri", Font.TYPE1_FONT, 30), point1); //texte qui affiche le compteur final de pions blancs
		f.ajouter(t);
		f.rafraichir();

		if(compteur1>compteur2){ //on affiche qui est le gagnant en fonction de qui a le plus de jetons
			point1=new Point(400,830); 
			t=new Texte(Couleur.NOIR, "Le joueur 1 (Noir) a gagne !! ", new Font("Calibri", Font.TYPE1_FONT, 30), point1); 
			f.ajouter(t);
			f.rafraichir();
		}
		else if(compteur2>compteur1){
			point1=new Point(400,830); 
			t=new Texte(Couleur.BLANC, "Le joueur 2 (Blanc) a gagne !! ", new Font("Calibri", Font.TYPE1_FONT, 30), point1); 
			f.ajouter(t);
			f.rafraichir();
		}
		else{
			point1=new Point(400,830); 
			t=new Texte(Couleur.BLEU, "Personne n'a gagne, Egalite !! ", new Font("Calibri", Font.TYPE1_FONT, 30), point1); 
			f.ajouter(t);
			f.rafraichir();
		}

	}
	
}