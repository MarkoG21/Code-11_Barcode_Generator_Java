import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.SystemColor;

@SuppressWarnings("serial")
public class Gui extends JFrame implements ActionListener {
	JButton btnNewButton;
	//Einstellungen für die Strichbreite oder Länge, wie auch Koordinaten, und Buttonflags.
	private boolean disableStartzeichen = false;
	private int buttonFlagcounter = 1;
	private	int	buttonPresses = 0;
	private	String lastButtonPressed;
	private int dicke = 10;
	private int laenge = 200;
	private int yBarcode = 260;
	private	boolean drawBarcode = false;
	//Hier werden die Übergebenen Array werte aus barcodeCodierung gespeichert.
	private	int[]  temporaryDrawBits =   {0,0,0,0,0,0,0,0,0,0,0,0};
	private	int[]  temporaryDrawBits1 =  {0,0,0,0,0,0,0,0,0,0,0,0};
	private	int[]  temporaryDrawBits2 =  {0,0,0,0,0,0,0,0,0,0,0,0};
	private	int[]  temporaryDrawBits3 =  {0,0,0,0,0,0,0,0,0,0,0,0};
	private	int[]  temporaryDrawBits4 =  {0,0,0,0,0,0,0,0,0,0,0,0};
	private	int[]  temporaryDrawBits5 =  {0,0,0,0,0,0,0,0,0,0,0,0};
	private	int[]  temporaryDrawBits6 =  {0,0,0,0,0,0,0,0,0,0,0,0};
	private	int[]  temporaryDrawBits7 =  {0,0,0,0,0,0,0,0,0,0,0,0};
	//Counter für loops.
	private int 	zaehler=0;
	private	int		counter=0;
	private	int		delay = 250; //Damit kann man die Y Koordinaten der Zahlen Buttons einfach verschieben.
	private	boolean	buttonFlag = true;
	private JPanel zeichenflaeche;
	//Daran orientieren sich die Barcode "schnipsel", hier wird später die eigene Dicke der Barcodes drauf gerechnet um die X koordinate zu bestimmen.
	private	int	lastX = 100;
	private	barcodeCodierung leonardo = new barcodeCodierung();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		

		
	}  

	public Gui() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		zeichenflaeche = new JPanel();
		zeichenflaeche.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(zeichenflaeche);
		zeichenflaeche.setLayout(null);
		this.setResizable(false);
		zeichenflaeche = new JPanel(){
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g){
				super.paintComponent(g);
		}
			@Override
			public void paint(Graphics g){
				//Bei dem 7.buttonpress werden alle anderen Buttons gelöscht, da ein Endzeichen immer benötig wird, macht das Programm idiotensicher.
				if(buttonPresses == 7 ) {
					zeichenflaeche.removeAll();
					buttonPresses++;
					JButton btnEndzeichen = new JButton("Endzeichen");
					btnEndzeichen.setBackground(SystemColor.info);
					btnEndzeichen.setFont(new Font("Arial", Font.PLAIN, 12));
					btnEndzeichen.setBounds(1149, 626, 105, 44);
					zeichenflaeche.add(btnEndzeichen);
					btnEndzeichen.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							buttonPresses++;
							lastButtonPressed = "x";
							getCodierung();
						}
					});
					
				}
				
				//Schaltet die anderen buttons frei
				if(buttonFlagcounter ==2) {
					buttonFlagcounter++;
					createBtns2();
				}
				
					super.paint(g);
					//Stamdardmäsig ist das Panel grau.
					zeichenflaeche.setBackground(Color.WHITE);
					//Macht, das nur einmal die buttons erstellt werden.
					if(buttonFlag) {
						buttonFlag = false;
						createBtns();
					}
					//Wenn drawBarcode true ist, sorgt das dafür, das wie der Name schon sagt, die Barcodes gezeichnet werden.
					if(drawBarcode) {
						
						//Hier wird im ersten Schritt geschaut, ist in dem Array eine 0 oder 1, wenn 0
						//bleibt das Feld Weiß, wenn 1 dann schwarzes Rechteck. 
						
						//Vielleicht hätte man das ganze mit einem anderem kleinerem und cooleren Algorythmus lösen können
						//aber mir ist da nichts anderes eingefallen, naja es funktioniert und ich bin zufrieden :D
						//Ich schreib das jetzt nicht über jedes aber das gilt für alle Barcode Teile.
						if(temporaryDrawBits[0] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX, yBarcode, dicke, laenge);
						}

						if(temporaryDrawBits[1] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits[2] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+2*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+2*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits[3] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+3*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+3*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits[4] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+4*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+4*dicke, yBarcode, dicke, laenge);
						}
					
						if(temporaryDrawBits[5] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+5*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+5*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits[6] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+6*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+6*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits[7] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+7*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+7*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits[8] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+8*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+8*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits[9] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+9*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+9*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits[10] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+10*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+10*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits[11] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+11*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+11*dicke, yBarcode, dicke, laenge);
						}
						
						//NächsterCodeAbschnitt
						
						
						
						
						//Die Nötige Lücke um Zeichen voneinander unterscheiden zu können
					
						if(temporaryDrawBits1[0] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+13*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+13*dicke, yBarcode, dicke, laenge);
						}

						if(temporaryDrawBits1[1] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+14*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+14*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits1[2] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+15*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+15*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits1[3] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+16*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+16*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits1[4] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+17*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+17*dicke, yBarcode, dicke, laenge);
						}
					
						if(temporaryDrawBits1[5] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+18*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+18*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits1[6] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+19*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+19*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits1[7] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+20*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+20*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits1[8] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+21*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+21*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits1[9] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+22*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+22*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits1[10] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+23*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+23*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits1[11] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+24*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+24*dicke, yBarcode, dicke, laenge);
						}
						

						//3.Barcode Element
						
						
						if(temporaryDrawBits2[0] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+26*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+26*dicke, yBarcode, dicke, laenge);
						}

						if(temporaryDrawBits2[1] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+27*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+27*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits2[2] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+28*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+28*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits2[3] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+29*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+29*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits2[4] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+30*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+30*dicke, yBarcode, dicke, laenge);
						}
					
						if(temporaryDrawBits2[5] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+31*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+31*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits2[6] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+32*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+32*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits2[7] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+33*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+33*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits2[8] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+34*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+34*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits2[9] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+35*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+35*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits2[10] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+36*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+36*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits2[11] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+37*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+37*dicke, yBarcode, dicke, laenge);
						}
						
						
						
						
						
						
						
						//4. Barcode Element
						if(temporaryDrawBits3[0] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+39*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+39*dicke, yBarcode, dicke, laenge);
						}

						if(temporaryDrawBits3[1] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+40*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+40*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits3[2] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+41*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+41*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits3[3] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+42*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+42*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits3[4] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+43*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+43*dicke, yBarcode, dicke, laenge);
						}
					
						if(temporaryDrawBits3[5] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+44*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+44*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits3[6] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+45*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+45*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits3[7] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+46*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+46*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits3[8] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+47*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+47*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits3[9] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+48*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+48*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits3[10] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+49*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+49*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits3[11] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+50*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+50*dicke, yBarcode, dicke, laenge);
						}
						
						
						
						
						//5.Barcode
						
						
						
						
						
						if(temporaryDrawBits4[0] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+52*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+52*dicke, yBarcode, dicke, laenge);
						}

						if(temporaryDrawBits4[1] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+53*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+53*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits4[2] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+54*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+54*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits4[3] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+55*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+55*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits4[4] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+56*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+56*dicke, yBarcode, dicke, laenge);
						}
					
						if(temporaryDrawBits4[5] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+57*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+57*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits4[6] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+58*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+58*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits4[7] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+59*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+59*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits4[8] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+60*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+60*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits4[9] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+61*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+61*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits4[10] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+62*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+62*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits4[11] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+63*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+63*dicke, yBarcode, dicke, laenge);
						}
						
						
						
						
						//6.Barcode Element
						
						if(temporaryDrawBits5[0] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+65*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+65*dicke, yBarcode, dicke, laenge);
						}

						if(temporaryDrawBits5[1] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+66*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+66*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits5[2] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+67*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+67*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits5[3] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+68*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+68*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits5[4] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+69*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+69*dicke, yBarcode, dicke, laenge);
						}
					
						if(temporaryDrawBits5[5] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+70*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+70*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits5[6] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+71*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+71*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits5[7] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+72*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+72*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits5[8] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+73*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+73*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits5[9] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+74*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+74*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits5[10] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+75*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+75*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits5[11] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+76*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+76*dicke, yBarcode, dicke, laenge);
						}
						
						
						
						
						
						//7. Barcode
						
						
						
						
						
						if(temporaryDrawBits6[0] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+78*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+78*dicke, yBarcode, dicke, laenge);
						}

						if(temporaryDrawBits6[1] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+79*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+79*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits6[2] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+80*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+80*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits6[3] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+81*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+81*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits6[4] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+82*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+82*dicke, yBarcode, dicke, laenge);
						}
					
						if(temporaryDrawBits6[5] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+83*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+83*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits6[6] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+84*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+84*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits6[7] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+85*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+85*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits6[8] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+86*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+86*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits6[9] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+87*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+87*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits6[10] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+88*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+88*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits6[11] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+89*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+89*dicke, yBarcode, dicke, laenge);
						}
						
						
						
						
						//8. Barcode
						
						
						
						
						
						if(temporaryDrawBits7[0] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+91*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+91*dicke, yBarcode, dicke, laenge);
						}

						if(temporaryDrawBits7[1] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+92*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+92*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits7[2] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+93*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+93*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits7[3] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+94*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+94*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits7[4] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+95*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+95*dicke, yBarcode, dicke, laenge);
						}
					
						if(temporaryDrawBits7[5] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+96*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+96*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits7[6] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+97*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+97*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits7[7] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+98*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+98*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits7[8] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+99*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+99*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits7[9] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+100*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+100*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits7[10] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+101*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+101*dicke, yBarcode, dicke, laenge);
						}
						
						if(temporaryDrawBits7[11] == 1) {
							g.setColor(Color.BLACK);
							g.fillRect(lastX+102*dicke, yBarcode, dicke, laenge);
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(lastX+102*dicke, yBarcode, dicke, laenge);
						}
						
						
						
						
					}
					
					
					repaint();

				}

	};
		
			setContentPane(zeichenflaeche);
			//Null Layout, dadurch kann ich die Buttons dahin machen wo ich will.
			zeichenflaeche.setLayout(null);
		
}
	public void createBtns(){
		//Startbutton
		//Hier wird der Erste Button erstellt, auch nur der, damit man nicht anfäng zahlen zu drücken ohne das Startzeichen zu haben.
		JButton btnNewButton = new JButton("Startzeichen");
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewButton.setBounds(10, 626, 105, 44);
		zeichenflaeche.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!disableStartzeichen) {
					disableStartzeichen = true;
				buttonFlagcounter++;
				buttonPresses++;
			lastButtonPressed="Startzeichen";
				getCodierung();
				}
			}
		});
	}
	public void createBtns2() {
		
		//Hier folgen die Zahlen und anderen Buttons.
		//Endbutton
		JButton btnEndzeichen = new JButton("Endzeichen");
		btnEndzeichen.setBackground(SystemColor.info);
		btnEndzeichen.setFont(new Font("Arial", Font.PLAIN, 12));
		btnEndzeichen.setBounds(1149, 626, 105, 44);
		zeichenflaeche.add(btnEndzeichen);
		btnEndzeichen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buttonPresses++;
				lastButtonPressed = "x";
				getCodierung();
				zeichenflaeche.removeAll();
			}
		});
		JButton zahlNull = new JButton("0");
		zahlNull.setBackground(new Color(158, 158, 158));
		zahlNull.setFont(new Font("Arial", Font.PLAIN, 12));
		zahlNull.setBounds(120+delay, 626, 45, 44);
		zeichenflaeche.add(zahlNull);
		zahlNull.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buttonPresses++;
				lastButtonPressed ="0";
				getCodierung();
			}
		});
		
		
		JButton zahlEins = new JButton("1");
		zahlEins.setBackground(new Color(158, 158, 158));
		zahlEins.setFont(new Font("Arial", Font.PLAIN, 12));
		zahlEins.setBounds(170+delay, 626, 45, 44);
		zeichenflaeche.add(zahlEins);
		zahlEins.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Erhöht die Knopfdrücke, setzt den Button als zuletzt gedrück, und ruft eine Methode auf.
				buttonPresses++;
				lastButtonPressed = "1";
				getCodierung();
			}
		});
		
			JButton zahlZwei  = new JButton("2");
			zahlZwei.setBackground(new Color(158, 158, 158));
			zahlZwei.setFont(new Font("Arial", Font.PLAIN, 12));
			zahlZwei.setBounds(220+delay, 626, 45, 44);
			zeichenflaeche.add(zahlZwei);
			zahlZwei.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					buttonPresses++;
					lastButtonPressed = "2";
					getCodierung();
				}
			});
			
			JButton zahlDrei = new JButton("3");
			zahlDrei.setBackground(new Color(158, 158, 158));
			zahlDrei.setFont(new Font("Arial", Font.PLAIN, 12));
			zahlDrei.setBounds(270+delay, 626, 45, 44);
			zeichenflaeche.add(zahlDrei);
			zahlDrei.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					buttonPresses++;
					lastButtonPressed = "3";
					getCodierung();
				}
			});
			
			JButton zahlVier = new JButton("4");
			zahlVier.setBackground(new Color(158, 158, 158));
			zahlVier.setFont(new Font("Arial", Font.PLAIN, 12));
			zahlVier.setBounds(320+delay, 626, 45, 44);
			zeichenflaeche.add(zahlVier);
			zahlVier.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					buttonPresses++;
					lastButtonPressed = "4";
					getCodierung();
				}
			});
			
			JButton zahlFuenf = new JButton("5");
			zahlFuenf.setBackground(new Color(158, 158, 158));
			zahlFuenf.setFont(new Font("Arial", Font.PLAIN, 12));
			zahlFuenf.setBounds(370+delay, 626, 45, 44);
			zeichenflaeche.add(zahlFuenf);
			zahlFuenf.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					buttonPresses++;
					lastButtonPressed = "5";
					getCodierung();
				}
			});
		
			JButton zahlSechs = new JButton("6");
			zahlSechs.setBackground(new Color(158, 158, 158));
			zahlSechs.setFont(new Font("Arial", Font.PLAIN, 12));
			zahlSechs.setBounds(420+delay, 626, 45, 44);
			zeichenflaeche.add(zahlSechs);
			zahlSechs.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					buttonPresses++;
					lastButtonPressed = "6";
					getCodierung();
				}
			});

			JButton zahlSieben = new JButton("7");
			zahlSieben.setBackground(new Color(158, 158, 158));
			zahlSieben.setFont(new Font("Arial", Font.PLAIN, 12));
			zahlSieben.setBounds(470+delay, 626, 45, 44);
			zeichenflaeche.add(zahlSieben);
			zahlSieben.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					buttonPresses++;
					lastButtonPressed = "7";
					getCodierung();
				}
			});
			
			JButton zahlAcht = new JButton("8");
			zahlAcht.setBackground(new Color(158, 158, 158));
			zahlAcht.setFont(new Font("Arial", Font.PLAIN, 12));
			zahlAcht.setBounds(520+delay, 626, 45, 44);
			zeichenflaeche.add(zahlAcht);
			zahlAcht.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					buttonPresses++;
					lastButtonPressed = "8";
					getCodierung();
				}
			});
			
			JButton zahlNeun = new JButton("9");
			zahlNeun.setBackground(new Color(158, 158, 158));
			zahlNeun.setFont(new Font("Arial", Font.PLAIN, 12));
			zahlNeun.setBounds(570+delay, 626, 45, 44);
			zeichenflaeche.add(zahlNeun);
			zahlNeun.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					buttonPresses++;
					lastButtonPressed = "9";
					getCodierung();
				}
			});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	}
	
	private void getCodierung() {
		//Hier schaut die Methode erstmal, von wem sie aufgerufen wurde, hier wird sie in "lastbuttonpressed" fündig.
		if(lastButtonPressed == "Startzeichen") {

			while(zaehler<12) {
//Hier wird Stelle für Stelle des Arrays der anderen Klasse and diese hier übergeben, welche dann im Endeffekt oben gedrawt wird.
			temporaryDrawBits[counter] = leonardo.test("Startzeichen", counter);
			counter++;
			zaehler++;
			}
		}
		if(buttonPresses == 2) {
			while(zaehler<12) {
			temporaryDrawBits1[counter] = leonardo.test(lastButtonPressed, counter);
			counter++;
			zaehler++;
		}
	}
		
		if(buttonPresses == 3) {
			while(zaehler<12) {
			temporaryDrawBits2[counter] = leonardo.test(lastButtonPressed, counter);
			counter++;
			zaehler++;
		}
	}
		
		if(buttonPresses == 4) {
			while(zaehler<12) {
			temporaryDrawBits3[counter] = leonardo.test(lastButtonPressed, counter);
			counter++;
			zaehler++;
		}
	}
		
		if(buttonPresses == 5) {
			while(zaehler<12) {
			temporaryDrawBits4[counter] = leonardo.test(lastButtonPressed, counter);
			counter++;
			zaehler++;
		}
	}
		
		if(buttonPresses == 6) {
			while(zaehler<12) {
			temporaryDrawBits5[counter] = leonardo.test(lastButtonPressed, counter);
			counter++;
			zaehler++;
		}
	}
		
		if(buttonPresses == 7) {
			while(zaehler<12) {
			temporaryDrawBits6[counter] = leonardo.test(lastButtonPressed, counter);
			counter++;
			zaehler++;
		}
	}
		
		if(buttonPresses == 9) {
			while(zaehler<12) {
			temporaryDrawBits7[counter] = leonardo.test(lastButtonPressed, counter);
			counter++;
			zaehler++;
		}
	}
		
		
			if(counter > 11) {
				drawBarcode = true;
				lastButtonPressed = "none";
				counter = 0;
				zaehler = 0;
			}
	}
}

