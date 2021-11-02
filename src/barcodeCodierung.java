
public class barcodeCodierung {
	private int[] startzeichen = {1,0,0,1,0,1,1,0,1,1,0,1};
	private int[] zahl_0	   = {1,0,1,0,0,1,1,0,1,1,0,1};
	private int[] zahl_1	   = {1,1,0,1,0,0,1,0,1,0,1,1};
	private int[] zahl_2	   = {1,0,1,1,0,0,1,0,1,0,1,1};
	private int[] zahl_3	   = {1,1,0,1,1,0,0,1,0,1,0,1};
	private int[] zahl_4	   = {1,0,1,0,0,1,1,0,1,0,1,1};		
	private int[] zahl_5	   = {1,1,0,1,0,0,1,1,0,1,0,1};
	private int[] zahl_6	   = {1,0,1,1,0,0,1,1,0,1,0,1};
	private int[] zahl_7	   = {1,0,1,0,0,1,0,1,1,0,1,1};
	private int[] zahl_8	   = {1,1,0,1,0,0,1,0,1,1,0,1};
	private int[] zahl_9	   = {1,0,1,1,0,0,1,0,1,1,0,1};
	public barcodeCodierung() {}
	//Die Obigen Arrays, werden durch das Objekt Leonardoaus der Klasse Gui aufgerufen und der Inhalt des Arrays wird übergeben.
	//Die Buttons setzten was zuletzt gedrückt wurde, damit die Methode getCodierung weiß welches Array er anfragen muss.
	public int test(String s,int arrayPlatz) {
		if(s == "Startzeichen") {
			return startzeichen[arrayPlatz];

			}
		if(s == "0") {
			return zahl_0[arrayPlatz];
		}
		
		if(s == "1") {
			return zahl_1[arrayPlatz];
		}
		
		if(s == "2") {
			return zahl_2[arrayPlatz];
		}
		
		if(s == "3") {
			return zahl_3[arrayPlatz];
		}
		
		if(s == "4") {
			return zahl_4[arrayPlatz];
		}
		
		if(s == "5") {
			return zahl_5[arrayPlatz];
		}
		
		if(s == "6") {
			return zahl_6[arrayPlatz];
		}
		
		if(s == "7") {
			return zahl_7[arrayPlatz];
		}
		
		if(s == "8") {
			return zahl_8[arrayPlatz];
		}
		
		if(s == "9") {
			return zahl_9[arrayPlatz];
		}
		
		if(s == "x") {
			return startzeichen[arrayPlatz];
		}
		
		return 69;
	}
	
}
