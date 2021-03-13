public class Plateau{
	private Case plateau[][]; //création de l'attribut plateau du type Case

	public Plateau(){ //remplissage de ce plateau
		plateau=new Case[8][8];
		for(int i=0; i<8; i++){
			for(int u=0; u<8; u++){
				plateau[i][u]=new Case();
			}
		}
	}

	public Case getXY(int i, int j){ //getteur de la case[i][j]
		return(plateau[i][j]);
	}

	public boolean plein(){ //méthode permettant de vérifier si le plateau est plein ou non
		boolean plein=true;
		for (int i=0; i<8; i++){
			for (int u=0; u<8; u++){
				int joueurCase = plateau[i][u].getJoueur();
				if (joueurCase==0){
					plein=false;
				}
			}
		}
		return(plein);
	}

	public boolean testPosePion(int x, int y, int joueur){ //méthode qui vérifie si on peut poser le pion 
		int pionAdverse;
		boolean trouve=false;
		boolean stop;
		int compteur=0;
		if(joueur==1){
			pionAdverse=2;
		}
		else{
			pionAdverse=1;
		}


		if(y>0 && x>0 && plateau[y-1][x-1].getJoueur()==pionAdverse){ //test de la case en haut a gauche
			stop=false;
			y=y-2;
			x=x-2;
			compteur=compteur+2;
			while(!stop && y>=0 && x>=0){
				if(plateau[y][x].getJoueur()==joueur){
					trouve=true;
					stop=true;
					for(int i=0; i<compteur;i++){
						plateau[y+i][x+i].setJoueur(joueur);
					}
					
				}
				else if(plateau[y][x].getJoueur()==0){
					stop=true;
				}
				else{
					y=y-1;
					x=x-1;
					compteur=compteur+1;
				}
			}
			y=y+compteur;
			x=x+compteur;
		}


		if(y>0 && plateau[y-1][x].getJoueur()==pionAdverse){ //test de la case en haut
			compteur=0;
			y=y-2;
			compteur=compteur+2;
			stop=false;
			while(!stop && y>=0){
				if(plateau[y][x].getJoueur()==joueur){
					trouve=true;
					stop=true;
					for(int i=0; i<compteur;i++){
						plateau[y+i][x].setJoueur(joueur);
					}
					
				}
				else if(plateau[y][x].getJoueur()==0){
					stop=true;
				}
				else{
					y=y-1;
					compteur=compteur+1;
				}
			}
			y=y+compteur;
		}


		if(y>0 && x<7 && plateau[y-1][x+1].getJoueur()==pionAdverse){ //test de la case en haut a droite
			compteur=0;
			y=y-2;
			x=x+2;
			compteur=compteur+2;
			stop=false;
			while(!stop && y>=0 && x<=7){
				if(plateau[y][x].getJoueur()==joueur){
					trouve=true;
					stop=true;
					for(int i=0; i<compteur;i++){
						plateau[y+i][x-i].setJoueur(joueur);
					}
				}
				else if(plateau[y][x].getJoueur()==0){
					stop=true;
				}
				else{
					y=y-1;
					x=x+1;
					compteur=compteur+1;
				}

			}
			x=x-compteur;
			y=y+compteur;
		}


		if(x<7 && plateau[y][x+1].getJoueur()==pionAdverse){ //test de la case a droite
			compteur=0;
			x=x+2;
			compteur=compteur+2;
			stop=false;
			while(!stop && x<=7){
				if(plateau[y][x].getJoueur()==joueur){
					trouve=true;
					stop=true;
					for(int i=0; i<compteur;i++){
						plateau[y][x-i].setJoueur(joueur);
					}
					
				}
				else if(plateau[y][x].getJoueur()==0){
					stop=true;
				}
				else{
					x=x+1;
					compteur=compteur+1;
				}
			}
			x=x-compteur;
		}

		if(x<7 && y<7 && plateau[y+1][x+1].getJoueur()==pionAdverse){ //test de la case en bas a droite
			compteur=0;
			x=x+2;
			y=y+2;
			compteur=compteur+2;
			stop=false;
			while(!stop && x<=7 && y<=7){
				if(plateau[y][x].getJoueur()==joueur){
					trouve=true;
					stop=true;
					for(int i=0; i<compteur;i++){
						plateau[y-i][x-i].setJoueur(joueur);
					}
				
				}
				else if(plateau[y][x].getJoueur()==0){
					stop=true;
				}
				else{
					x=x+1;
					y=y+1;
					compteur=compteur+1;
				}
			}
			x=x-compteur;
			y=y-compteur;
		}


		if(y<7 && plateau[y+1][x].getJoueur()==pionAdverse){ //test de la case en bas
			compteur=0;
			y=y+2;
			compteur=compteur+2;
			stop=false;
			while(!stop && y<=7){
				if(plateau[y][x].getJoueur()==joueur){
					trouve=true;
					stop=true;
					for(int i=0; i<compteur;i++){
						plateau[y-i][x].setJoueur(joueur);
					}
				
				}
				else if(plateau[y][x].getJoueur()==0){
					stop=true;
				}
				else{
					y=y+1;
					compteur=compteur+1;
				}
			}
			y=y-compteur;
		}


		if(y<7 && x>0 && plateau[y+1][x-1].getJoueur()==pionAdverse){ //test de la case en bas a gauche
			compteur=0;
			y=y+2;
			x=x-2;
			compteur=compteur+2;
			stop=false;
			while(!stop && y<=7 && x>=0){
				if(plateau[y][x].getJoueur()==joueur){
					trouve=true;
					stop=true;
					for(int i=0; i<compteur;i++){
						plateau[y-i][x+i].setJoueur(joueur);
					}
				
				}
				else if(plateau[y][x].getJoueur()==0){
					stop=true;
				}
				else{
					y=y+1;
					x=x-1;
					compteur=compteur+1;
				}
			}
			y=y-compteur;
			x=x+compteur;
		}


		if(x>0 && plateau[y][x-1].getJoueur()==pionAdverse){ //test de la case a gauche
			compteur=0;
			x=x-2;
			compteur=compteur+2;
			stop=false;
			while(!stop && x>=0){
				if(plateau[y][x].getJoueur()==joueur){
					trouve=true;
					stop=true;
					for(int i=0; i<compteur;i++){
						plateau[y][x+i].setJoueur(joueur);
					}
				
				}
				else if(plateau[y][x].getJoueur()==0){
					stop=true;
				}
				else{
					x=x-1;
					compteur=compteur+1;
				}
			}
			x=x+compteur;
		}


		return(trouve);
	}



	public boolean Rejoue(int x, int y, int joueur){ //méthode qui permet de savoir si le joueur x doit rejouer ou pas
		int pionAdverse;
		boolean trouve=false;
		boolean stop;
		int compteur=0;
		if(joueur==1){
			pionAdverse=2;
		}
		else{
			pionAdverse=1;
		}


		if(y>0 && x>0 && plateau[y-1][x-1].getJoueur()==pionAdverse){ //test de la case en haut a gauche
			stop=false;
			y=y-2;
			x=x-2;
			compteur=compteur+2;
			while(!stop && y>=0 && x>=0){
				if(plateau[y][x].getJoueur()==joueur){
					trouve=true;
					stop=true;

				}
				else if(plateau[y][x].getJoueur()==0){
					stop=true;
				}
				else{
					y=y-1;
					x=x-1;
					compteur=compteur+1;
				}
			}
			y=y+compteur;
			x=x+compteur;
		}


		if(y>0 && plateau[y-1][x].getJoueur()==pionAdverse){ //test de la case en haut
			compteur=0;
			y=y-2;
			compteur=compteur+2;
			stop=false;
			while(!stop && y>=0){
				if(plateau[y][x].getJoueur()==joueur){
					trouve=true;
					stop=true;

					
				}
				else if(plateau[y][x].getJoueur()==0){
					stop=true;
				}
				else{
					y=y-1;
					compteur=compteur+1;
				}
			}
			y=y+compteur;
		}


		if(y>0 && x<7 && plateau[y-1][x+1].getJoueur()==pionAdverse){ //test de la case en haut a droite
			compteur=0;
			y=y-2;
			x=x+2;
			compteur=compteur+2;
			stop=false;
			while(!stop && y>=0 && x<=7){
				if(plateau[y][x].getJoueur()==joueur){
					trouve=true;
					stop=true;

				}
				else if(plateau[y][x].getJoueur()==0){
					stop=true;
				}
				else{
					y=y-1;
					x=x+1;
					compteur=compteur+1;
				}

			}
			x=x-compteur;
			y=y+compteur;
		}


		if(x<7 && plateau[y][x+1].getJoueur()==pionAdverse){ //test de la case a droite
			compteur=0;
			x=x+2;
			compteur=compteur+2;
			stop=false;
			while(!stop && x<=7){
				if(plateau[y][x].getJoueur()==joueur){
					trouve=true;
					stop=true;

					
				}
				else if(plateau[y][x].getJoueur()==0){
					stop=true;
				}
				else{
					x=x+1;
					compteur=compteur+1;
				}
			}
			x=x-compteur;
		}

		if(x<7 && y<7 && plateau[y+1][x+1].getJoueur()==pionAdverse){ //test de la case en bas a droite
			compteur=0;
			x=x+2;
			y=y+2;
			compteur=compteur+2;
			stop=false;
			while(!stop && x<=7 && y<=7){
				if(plateau[y][x].getJoueur()==joueur){
					trouve=true;
					stop=true;

				
				}
				else if(plateau[y][x].getJoueur()==0){
					stop=true;
				}
				else{
					x=x+1;
					y=y+1;
					compteur=compteur+1;
				}
			}
			x=x-compteur;
			y=y-compteur;
		}


		if(y<7 && plateau[y+1][x].getJoueur()==pionAdverse){ //test de la case en bas
			compteur=0;
			y=y+2;
			compteur=compteur+2;
			stop=false;
			while(!stop && y<=7){
				if(plateau[y][x].getJoueur()==joueur){
					trouve=true;
					stop=true;

				
				}
				else if(plateau[y][x].getJoueur()==0){
					stop=true;
				}
				else{
					y=y+1;
					compteur=compteur+1;
				}
			}
			y=y-compteur;
		}


		if(y<7 && x>0 && plateau[y+1][x-1].getJoueur()==pionAdverse){ //test de la case en bas a gauche
			compteur=0;
			y=y+2;
			x=x-2;
			compteur=compteur+2;
			stop=false;
			while(!stop && y<=7 && x>=0){
				if(plateau[y][x].getJoueur()==joueur){
					trouve=true;
					stop=true;

				}
				else if(plateau[y][x].getJoueur()==0){
					stop=true;
				}
				else{
					y=y+1;
					x=x-1;
					compteur=compteur+1;
				}
			}
			y=y-compteur;
			x=x+compteur;
		}


		if(x>0 && plateau[y][x-1].getJoueur()==pionAdverse){ //test de la case a gauche
			compteur=0;
			x=x-2;
			compteur=compteur+2;
			stop=false;
			while(!stop && x>=0){
				if(plateau[y][x].getJoueur()==joueur){
					trouve=true;
					stop=true;

				}
				else if(plateau[y][x].getJoueur()==0){
					stop=true;
				}
				else{
					x=x-1;
					compteur=compteur+1;
				}
			}
			x=x+compteur;
		}


		return(trouve);
	}


	public String toString(){ // méthode toString pour mettre en forme le plateau
		String grille="___ ___ ___ ___ ___ ___ ___ ___"+"\n";
		String symbole=" ";	
		for(int i=0; i<8; i++){
			for(int u=0; u<8; u++){
				if (plateau[i][u].getJoueur()==0){ //si il n'y a pas de joueur qui occupe la case alors on ne met rien dedans
					symbole=" ";	
				}
				else if(plateau[i][u].getJoueur()==1){ //si la case est occupée par le joueur n°1 alors on met une croix
					symbole="X";
				}
				else{  // sinon si la case est occupée par le joueur n°2 alors on met une rond
					symbole="O";
				}
				grille=grille +" " +symbole + " |";
			}
			grille = grille + "\n" +"___ ___ ___ ___ ___ ___ ___ ___"+"\n";
		}
		return(grille);
	}


}