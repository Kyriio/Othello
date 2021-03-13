public class Case {
	private int joueur; //déclaration de l'attribut joueur

	public Case(){ //initialisation de la case en valeur par défaut
		joueur=0;
	}

	public Case(int joueur){ //constructeur de la case
		if((joueur == 0) || (joueur == 1) || (joueur == 2)){
            this.joueur = joueur ;
        }
        else{
            System.out.println("La case ne peut contenir que 0, 1 ou 2");
            this.joueur =0;
        }
	}

	public int getJoueur(){ //getteur du joueur de la case
		return joueur;
	}

	public void setJoueur(int joueur){ //setteur du joueur de la case
		if((joueur == 0) || (joueur == 1) || (joueur == 2)){
            this.joueur = joueur ;
        }
        else{
            System.out.println("La case ne peut contenir que 0, 1 ou 2");
            this.joueur =0;
        }
	}

	public String toString(){ //méthode toString
		return ("La valeur de la case " + joueur);
	}

	public boolean equals(Object obj){ //méthode equals
		if (this==obj){
			return true;
		}
		if (obj == null){
			return false;
		}
		if (getClass() != obj.getClass()){
			return false;
		}
		Case other = (Case) obj;
		return((joueur==other.joueur));

	}




}