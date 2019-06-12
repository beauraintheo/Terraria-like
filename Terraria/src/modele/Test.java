package modele;

public class Test {

	public static void main(String[] args)  {
		Inventaire i = new Inventaire();
		BlocTerre b = new BlocTerre(16);
		
		i.ajouterItem(b);
		i.afficherItems();
		
		Item a = i.item(1);
		
		System.out.println(a);
	}
}
