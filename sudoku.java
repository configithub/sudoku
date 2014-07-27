/****************************************************************/
/*                      sudoku	                                */
/*                                                              */
/****************************************************************/
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;





public class sudoku extends JFrame
{
	// Variables declaration
	
	private JTextArea JTexta[][] = new JTextArea[9][9];
	private JScrollPane JScrollp[][] = new JScrollPane[9][9];
	private JScrollPane jScrollPanebouton;
	private JButton jButtonbouton;
	private JPanel contentPane;
	ActionListener activerbouton;
	private JButton jButtonbouton1;
	ActionListener activerbouton1;
	private JScrollPane jScrollPanebouton1;
	private JButton jButtonbouton2;
	ActionListener activerbouton2;
	private JScrollPane jScrollPanebouton2;
	JScrollPane jScrollPaneboutonabout;
	JButton jButtonboutonabout;
	JPanel contentPaneabout;
	JLabel abouttext;
	JLabel abouttext1;
	JLabel abouttext2;
	JLabel aboutimage;
	ActionListener activerboutonabout;
	JFrame about;
	private int a[][] = new int[9][9];
	private int c[][][] = new int[9][9][99999];
	private int nfi[] = new int[81];
	private int nfj[] = new int[81];
	boolean possibilite[][][] = new boolean[10][9][9];
	boolean echec[][][] = new boolean[10][9][9];
	int t;
	int p;
	int i;
	int j;
	int h;
	

	
	// End of variables declaration


	public sudoku()
	{
		super();
		initializeComponent();
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setVisible(true);
	}


	private void initializeComponent()
	{
		for (j = 0 ; j < 9; j++ ) {
			for (i = 0 ; i < 9; i++ ) { 
				JTexta[i][j] = new JTextArea();
			};
		};
		
		for (j = 0 ; j < 9; j++ ) {
			for (i = 0 ; i < 9; i++ ) { 
				JScrollp[i][j] = new JScrollPane();
			};
		};
		ImageIcon icone = new ImageIcon("moi1.gif");
		abouttext = new JLabel("Gilles Masselot");
		abouttext2 = new JLabel("gilles.masselot@student.ecp.fr");
		abouttext1 = new JLabel(":p");
		aboutimage = new JLabel(icone);
		about = new JFrame();
		jScrollPanebouton = new JScrollPane();
		jButtonbouton = new JButton();
		jScrollPanebouton1 = new JScrollPane();
		jButtonbouton1 = new JButton();
		jScrollPanebouton2 = new JScrollPane();
		jButtonbouton2 = new JButton();
		jScrollPaneboutonabout = new JScrollPane();
		jButtonboutonabout = new JButton();
		contentPaneabout = (JPanel)about.getContentPane();
		contentPane = (JPanel)this.getContentPane();
		
		activerboutonabout = new ActionListener(){
       		public void actionPerformed(ActionEvent g) {
       			about.setVisible(false);
       		}
       	};
       	activerbouton2 = new ActionListener(){
       		public void actionPerformed(ActionEvent h) {
       			for(i=0;i<9;i++){
					for(j=0;j<9;j++){
						
						
						JTexta[i][j].setText("");
					}
							
				}
			}
		};
		activerbouton1 = new ActionListener(){
       		public void actionPerformed(ActionEvent f) {
       			about.setVisible(true);
       		}
       	};
		activerbouton = new ActionListener(){
       		public void actionPerformed(ActionEvent e) {
       			
       			
       			// ALLOCATION TABLEAU
       			
       			
       			for (j = 0 ; j < 9; j++ ) {
					for (i = 0 ; i < 9; i++ ) { 
						if(JTexta[i][j].getText().length() == 0) { 
       						a[i][j] = 10*i+j+100;
       					} else {
       						Integer b = new Integer(JTexta[i][j].getText());
       						a[i][j] = b.intValue();
       					};
					};
				};
		
				
       			
       			
       	
       			
         		
         		//contrôle validité de la grille
         		
         		
         		for (i = 0 ; i < 9; i++ ) {
					for (j = 0 ; j < 9; j++ ) {
         				System.out.println("a"+i+""+j+": " + a[i][j]);
         			};
         		};
         		
         		
         		
         		
         			
         			
         			
         		if (testGrille(a)  == false) {
         			System.out.println("La grille entree n'est pas valide");
         		}else{
         		//si la grille est ok, résolution
         		
         		// 1ere phase : élagage, on supprime les possibilités invalides cf fonction elagagetot()
         		
         			
         			
         			p=0;
         			elagagetot();
         			
         			
         			h=0;
         			for(i=0;i<9;i++){
						for(j=0;j<9;j++){
							if (a[i][j]<10) {
								h=h+1;	
							}
						}
					}
					if (h==81){
						for(i=0;i<9;i++){
							for(j=0;j<9;j++){
								String d = new String();
								d = d.valueOf(a[i][j]);
								JTexta[i][j].setText(d);
							}
						}
					
					}else{
					
					
					//2eme phase : résolution par test réussite/échec cf fonction resout(a)
					
         				imprime(a);
         			
         				initechec(echec);
         			
         				sauvegarde(a,c);
         				repnf(a);
         				p=0;
         				t=0;
         				resout(a);
         				
         				for(i=0;i<9;i++){
							for(j=0;j<9;j++){
								String d = new String();
								d = d.valueOf(a[i][j]);
								JTexta[i][j].setText(d);
							}
							
						}
					}
         		}
         		
       		};
       		
       		
       		
       	};	
		

		//
		// couleur
		//
		JTexta[0][0].setBackground(new Color(187, 207, 223));
		JTexta[0][1].setBackground(new Color(187, 207, 223));
		JTexta[0][2].setBackground(new Color(187, 207, 223));
		JTexta[0][6].setBackground(new Color(187, 207, 223));
		JTexta[0][7].setBackground(new Color(187, 207, 223));
		JTexta[0][8].setBackground(new Color(187, 207, 223));
		JTexta[1][0].setBackground(new Color(187, 207, 223));
		JTexta[1][1].setBackground(new Color(187, 207, 223));
		JTexta[1][2].setBackground(new Color(187, 207, 223));
		JTexta[1][6].setBackground(new Color(187, 207, 223));
		JTexta[1][7].setBackground(new Color(187, 207, 223));
		JTexta[1][8].setBackground(new Color(187, 207, 223));
		JTexta[2][0].setBackground(new Color(187, 207, 223));
		JTexta[2][1].setBackground(new Color(187, 207, 223));
		JTexta[2][2].setBackground(new Color(187, 207, 223));
		JTexta[2][6].setBackground(new Color(187, 207, 223));
		JTexta[2][7].setBackground(new Color(187, 207, 223));
		JTexta[2][8].setBackground(new Color(187, 207, 223));
		JTexta[3][3].setBackground(new Color(187, 207, 223));
		JTexta[3][4].setBackground(new Color(187, 207, 223));
		JTexta[3][5].setBackground(new Color(187, 207, 223));
		JTexta[4][3].setBackground(new Color(187, 207, 223));
		JTexta[4][4].setBackground(new Color(187, 207, 223));
		JTexta[4][5].setBackground(new Color(187, 207, 223));
		JTexta[5][3].setBackground(new Color(187, 207, 223));
		JTexta[5][4].setBackground(new Color(187, 207, 223));
		JTexta[5][5].setBackground(new Color(187, 207, 223));
		JTexta[6][0].setBackground(new Color(187, 207, 223));
		JTexta[6][1].setBackground(new Color(187, 207, 223));
		JTexta[6][2].setBackground(new Color(187, 207, 223));
		JTexta[6][6].setBackground(new Color(187, 207, 223));
		JTexta[6][7].setBackground(new Color(187, 207, 223));
		JTexta[6][8].setBackground(new Color(187, 207, 223));
		JTexta[7][0].setBackground(new Color(187, 207, 223));
		JTexta[7][1].setBackground(new Color(187, 207, 223));
		JTexta[7][2].setBackground(new Color(187, 207, 223));
		JTexta[7][6].setBackground(new Color(187, 207, 223));
		JTexta[7][7].setBackground(new Color(187, 207, 223));
		JTexta[7][8].setBackground(new Color(187, 207, 223));
		JTexta[8][0].setBackground(new Color(187, 207, 223));
		JTexta[8][1].setBackground(new Color(187, 207, 223));
		JTexta[8][2].setBackground(new Color(187, 207, 223));
		JTexta[8][6].setBackground(new Color(187, 207, 223));
		JTexta[8][7].setBackground(new Color(187, 207, 223));
		JTexta[8][8].setBackground(new Color(187, 207, 223));

		
		
		// association ScrollPane-TextArea
		
       	for (j = 0 ; j < 9; j++ ) {	
			for (i = 0 ; i < 9; i++ ) { 
				JScrollp[i][j].setViewportView(JTexta[i][j]);
			};
		};
		
		
		jScrollPanebouton.setViewportView(jButtonbouton);
		jButtonbouton.setText("Résoudre");
		jButtonbouton.addActionListener(activerbouton);
		
		jScrollPanebouton1.setViewportView(jButtonbouton1);
		jButtonbouton1.setText("About");
		jButtonbouton1.addActionListener(activerbouton1);
		
		jScrollPanebouton2.setViewportView(jButtonbouton2);
		jButtonbouton2.setText("Reset");
		jButtonbouton2.addActionListener(activerbouton2);
		
		jScrollPaneboutonabout.setViewportView(jButtonboutonabout);
		jButtonboutonabout.setText("Close");
		jButtonboutonabout.addActionListener(activerboutonabout);
	
	
		//
		// contentPane
		//
		
		
		contentPane.setLayout(null);
		contentPaneabout.setLayout(null);
		
		for (j = 0 ; j < 9; j++ ) {	
			for (i = 0 ; i < 9; i++ ) {
				addComponent(contentPane, JScrollp[i][j], 50+25*j,75+25*i,23,23);
			};
		};
			
		addComponent(contentPane, jScrollPanebouton, 87,327,147,40);
		addComponent(contentPane, jScrollPanebouton1, 87,467,147,40);
		addComponent(contentPane, jScrollPanebouton2, 87,397,147,40);
		addComponent(contentPaneabout, jScrollPaneboutonabout, 70,360,140,40);
		addComponent(contentPaneabout, abouttext, 65,300,280,40);
		addComponent(contentPaneabout, abouttext2, 50,320,280,40);
		addComponent(contentPaneabout, abouttext1, 245,385,25,25);
		addComponent(contentPaneabout, aboutimage, 15,20,240,262);
		
		this.setTitle("Sudoku");
		this.setLocation(new Point(20, 61));
		this.setSize(new Dimension(330, 570));
		this.setResizable(false);
		
		about.setTitle("About me");
		about.setLocation(new Point(40, 81));
		about.setSize(new Dimension(280, 450));
		about.setResizable(false);
		
		
	} 


	private void addComponent(Container container,Component c,int x,int y,int width,int height)
	{
		c.setBounds(x,y,width,height);
		container.add(c);
		
	}
		public static void main (String [] args) {
		new sudoku();
	}
	
	//testColonne renvoie false si il existe 2 cases dont les valeurs sont égales dans la colonne j
	// et true si toutes les valeurs de la colonne j sont différentes.
	static boolean testColonne(int j, int a[][])
	{	
		int p;
		int q;
		int n;
		n=0;
		boolean test;
		test = true;
		boolean nestpasegal[]= new boolean[36];
		for (q=0;q<8;q++){
			for (p=q+1;p<9;p++) {
				if(a[p][j] == a[q][j]) {
					nestpasegal[n] = false;		
				}else{
					nestpasegal[n] = true;
				};
				n= n + 1;
			};
		};
		n=0;
		while (n<nestpasegal.length) {
			test = test && nestpasegal[n];
			n= n + 1;
				
		};
		return test;
	}
	
	//testLigne renvoie false si il existe 2 cases dont les valeurs sont égales dans la ligne i
	// et true si toutes les valeurs de la ligne i sont différentes.
	static boolean testLigne(int i, int a[][])
	{	
		int p;
		int q;
		int n;
		n=0;
		boolean test;
		test = true;
		boolean nestpasegal[]= new boolean[36];
		for (q=0;q<8;q++){
			for (p=q+1;p<9;p++) {
				if(a[i][p] == a[i][q]) {
					nestpasegal[n] = false;		
				}else{
					nestpasegal[n] = true;
				};
				n=n+1;
			};
		};
		n=0;
		while (n<nestpasegal.length) {
			test = test && nestpasegal[n];
			n=n+1;	
		};
		return test;
		
	}
	//testCarre renvoie false si il existe 2 cases dont les valeurs sont égales dans le sous carré 3x3 qui contient
	//la case de coordonnées (i,j) et true si toutes les valeurs du sous carré 3x3 sont différentes.
	static boolean testCarre(int i, int j, int a[][])
	{
		int x;
		int y;
		int k;
		int l;
		int v;
		v=0;
		int n;
		n=0;
		int p;
		int q;
		boolean test;
		test = true;
		boolean nestpasegal[]= new boolean[36];
		int g[] = new int[9];
		
		if(i<3) {
			x=0;
		}else if(2<i & i<6){
			x=1;
		}else{
			x=2;
		}
		
		if(j<3) {
			y=0;
		}else if(2<j & j<6){
			y=1;
		}else{
			y=2;
		}
		
		for (k=3*x;k<3*(x+1);k++){
			for(l=3*y;l<3*(y+1);l++){
				g[v]=a[k][l];
				v = v + 1;
			}	
		}
		
		for (q=0;q<8;q++){
			for (p=q+1;p<9;p++) {
				if(g[p] == g[q]) {
					nestpasegal[n] = false;		
				}else{
					nestpasegal[n] = true;
				};
				n=n+1;
			};
		};
		n=0;
		while (n<nestpasegal.length) {
			test = test && nestpasegal[n];
			n=n+1;	
		};
		return test;
		
	}
	
	//testsudoku est le test cumulé, ligne colonne et sous carré, renvoie true quand les regles du sudoku sont
	//respectées pour la case de coordonnées (i,j) et false si les regles du sudoku ne sont pas respectées.
	static boolean testSudoku(int i,int j,int a[][])
	{
		return (testLigne(i,a) && testColonne(j,a) && testCarre(i,j,a));
	}

	//testGrille vérifie la validité de la grille entrée par l'utilisateur.
	static boolean testGrille(int a[][])
	{
		int x;
		int y;
		int i;
		int j;
		
		boolean test;
		test = true;
		for (i=0;i<9;i++){
			test = test && testLigne(i,a);
		}
		for (j=0;j<9;j++){
			test = test && testColonne(j,a);
		}
		for (x=0;x<3;x++){
			for (y=0;y<3;y++){
				test = test && testCarre(3*x,3*y,a);
			}
		}
		return test;
	}
	
	
	
	//elague détermine les cases pour lesquelles les possibilités sont restreintes 
	//fige celles pour qui il n'y a qu'une seule possibilité.
	//et restreint les possibilites de valeur pour chaque case en fonction des 
	//regles du sudoku.
	
	public void elague(int a[][])
	{
		int i;
		int j;
		int k;
		int count;
		int c[][] = new int[9][9];
		for(i=0;i<9;i++){
			for(j=0;j<9;j++){
				c[i][j] = a[i][j]; 
			}
		}
		
		
		for (i=0;i<9;i++){
			for (j=0;j<9;j++){
				if (a[i][j]<10){
					for (k=1;k<10;k++){
						possibilite[k][i][j]=false;
					}
					possibilite[a[i][j]][i][j]=true;
				}else{
					for (k=1;k<10;k++){
						a[i][j]=k;
						if (testGrille(a) == true) {
							possibilite[k][i][j]=true;
						}else{
							possibilite[k][i][j]=false;
						}
						
						
					}
				}
				a[i][j]=c[i][j];
			}
		}
		
	
		for (i=0;i<9;i++){
			for (j=0;j<9;j++){
				count = 0;
				for (k=1;k<10;k++){
					if (possibilite[k][i][j]==true) {
						count = count + 1;
					}
				}
				if (count == 1) {
					for (k=1;k<10;k++){
						if (possibilite[k][i][j]==true) {
							a[i][j]=k;
						}
					}
				}
			}
		}					
	}
	
	//elagagetot applique la fonction elague à la grille autant de fois que nécessaire (c'est à dire jusqu'à
	//ce que la grille soit inchangée par elague par rapport à la sauvegard au temps p-1.
	
	public void elagagetot()
	{
		int u;
		u=0;
		sauvegarde(a,c);
		elague(a);
		p=p+1;
		for (i=0;i<9;i++){
			for (j=0;j<9;j++){
				if (c[i][j][p-1] == a[i][j]) {
					u=u+1;
				}
			}
		}
		if (u==81) {
			return;
		}else{
			elagagetot();
		}
		
		
	}
	
	//relog possibilite, cette fonction réenregistre les possibilités possibles pour chaque case
	//comme c'est le cas dans la fonction elagage mais sans effectuer d'elagage.
	public void relogposs(int a[][])
	{
		
		int i;
		int j;
		int k;
		int c[][] = new int[9][9];
		for(i=0;i<9;i++){
			for(j=0;j<9;j++){
				c[i][j] = a[i][j]; 
			}
		}
		
		
		for (i=0;i<9;i++){
			for (j=0;j<9;j++){
				
					for (k=1;k<10;k++){
						a[i][j]=k;
						if (testSudoku(i,j,a) == true) {
							possibilite[k][i][j]=true;
						}else{
							possibilite[k][i][j]=false;
						}
						
						
					}
				
				a[i][j]=c[i][j];
			}
		}
	}
	
	//sauvegarde sauvegarde la grille a[i][j] dans le tableau c[i][j][t] ou t désigne le 
	//numero (temps) du tableau de sauvegarde c[i][j][t].
	public void sauvegarde(int a[][],int c[][][])
	{
			
		int i;
		int j;
	
		for(i=0;i<9;i++){
			for(j=0;j<9;j++){
				c[i][j][p] = a[i][j]; 
			}
		}
			
		
	};
	
	
	//initialise échec
	public void initechec(boolean echec[][][])
	{
		int i;
		int j;
		int k;
		for (i=0;i<9;i++){
			for (j=0;j<9;j++){
				for (k=1;k<10;k++) {
					echec[k][i][j] = true;
				}
			}
		}
	}
	
	//répertorie les cases non figées et listes leurs valeurs dans nf[].
	//répertorie les cases figées par la valeur -1.
	public void repnf(int a[][])
	{
		int i;
		int j;
		int k;
		int count;
		int p;
		int w;
		p=0;
		for (i=0;i<9;i++){
			for (j=0;j<9;j++){
				count = 0;
				for (k=1;k<10;k++){
					if (possibilite[k][i][j]==true) {
						count = count + 1;
					}
				}
				if (count>1) {
					
					nfi[p]=i;
					nfj[p]=j;
					p=p+1;
				}
			}
		}
		for (w=p;w<81;w++){
			nfi[w]=-1;
			nfj[w]=-1;
		}
		p=0;
				
	}
	
	//résoudre la grille
	
	public void resout(int a[][])
	{	
		int i;
		int j;
		int k;
		int countpossechec;
		int w;
		int z;
		
		
	
	
		sauvegarde(a,c);
		
		relogposs(a);
		t=t+1;
		
		i=nfi[p];
		j=nfj[p];
	
		
		countpossechec=0;
		for (k=1;k<10;k++){
			if (possibilite[k][i][j]==true && echec[k][i][j]==true) {
				countpossechec = countpossechec + 1;			
			}
		}
		if (countpossechec==0) {
			if (p==0) { 
				System.out.println("grille non resolue");
				
				return;
			}else{
				echec[a[nfi[p-1]][nfj[p-1]]][nfi[p-1]][nfj[p-1]] = false;
				for (k=1;k<10;k++) {
					echec[k][nfi[p]][nfj[p]] = true;
				}
				for (i=0;i<9;i++){
					for (j=0;j<9;j++) {
						a[i][j]=c[i][j][p-1];
					}
				}
				
				p = p - 1;
				
				resout(a); 
			}
		}else{
			
			a[i][j]=1;
			while (possibilite[a[i][j]][i][j]==false || echec[a[i][j]][i][j]==false){
				a[i][j]=a[i][j]+1;	
			}
			
			z=0;
			for(w=0;w<nfi.length;w++){
				if (nfi[w] != -1){
					z=z+1;
				}
			}
			
			if (p==z-1){
				
				return;
			}else{
				
				p = p + 1;
				
				resout(a);
			}
			
		}
		
		
	
		
		
	}
	
	
	//imprime les résultats.
	public void imprime(int a[][])
	{
		for (i = 0 ; i < 9; i++ ) {
			for (j = 0 ; j < 9; j++ ) {
         		System.out.println("a"+i+""+j+": " + a[i][j]);
         	};
         };
	};
	
	
	
	
	
	
	
		
	

	
		
       	
		//réalisé par Gilles Masselot.
	
	

}
