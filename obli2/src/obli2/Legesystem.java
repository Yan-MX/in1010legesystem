package obli2;
/*
 * NOTES:
 * Scanner try hasnext(), and then only use .next() do not use nextInt()
 * 
 * */

/*scanner next() vs nextLine() space can be counted as nextLine*/
import java.util.*;
import java.io.*;

public class Legesystem {
	// Opprett lister som lagrer objektene i legesystemet
	static Liste<Lege> legeliste = new SortertLenkeliste<Lege>();
	static Liste<Pasient> pasientliste = new Lenkeliste<Pasient>();
	static Liste<Legemiddel> legemiddelliste = new Lenkeliste<Legemiddel>();
	static Liste<Resept> reseptliste = new Lenkeliste<Resept>();
	static Liste<Lege> spesialisterliste = new SortertLenkeliste<Lege>();

	public static void main(String[] args) throws NumberFormatException, UlovligUtskrift {
		File a = new File("happy.txt");
		lesFraFil(a);
		// print sum
		/*
		 * System.out.println(legeliste.stoerrelse());
		 * System.out.println(pasientliste.stoerrelse());
		 * System.out.println(legemiddelliste.stoerrelse());
		 * System.out.println(reseptliste.stoerrelse());
		 */
		Scanner input = new Scanner(System.in);
		mainmenu(input);
		input.close();

	}

	/*
	 * debugging System.out.println(legemiddelliste.hent(0)); for (int i = 0
	 * ;i<7;i++) { System.out.println(legemiddelliste.hent(i));
	 * System.out.println(i); System.out.println(); }
	 * 
	 * for(Lege i:legeliste) { System.out.println(i); }
	 */
	// this is a main menu method
	public static void mainmenu(Scanner input) {
		int num = 0;
		/*
		 * Scanner only open and close once and used in program becasue once scanner is
		 * closed, the System in will be closed and can not receive more input
		 */
		

		System.out.println("[Choose from the Main Menu, Choose from 1,2,3,4,5 or 100]");
		System.out.println("1: Print full list");
		System.out.println("2: Add new element to system \n3: Apply prescription to a patient");
		System.out.println("4: Show statistics \n5: Write to file \n100: end the system");
		try{
			String nm = input.next();
			num = Integer.parseInt(nm);
		
	
			// if the input is out of range then it is invalid, will get warning
			if (num > 5 && num != 100) {
				System.out.println("[invalid input, choose again]");
				mainmenu(input);
			}
			switch (num) {
			case 1:
				// print once the overview
				System.out.println("     Overview:" + "\n   doctors:  " + legeliste.stoerrelse() + "\n   patient:  "
						+ pasientliste.stoerrelse() + "\n   drugs:    " + legemiddelliste.stoerrelse() + "\n   prescriptions: "
						+ reseptliste.stoerrelse());
				System.out.println();
				// pass in Scanner input in other methods
				print(input);
				break;
			case 3:
				
				applypToPatient(input);
				break;
			case 4:
				System.out.println("[Choose from the Menu, Choose from a, b, c]");
				System.out.println("a. Total number of prescriptions for addictive drugs");
				System.out.println("b. Total number of prescriptions for narcotic drugs");
				System.out.println("c. Statistics on possible drug abuse");
				statistic(input);
				break;
			case 5:
				writeToFile(input);
				break;
			case 100:
				System.out.println("System Ends");
				break;
	
			}
		}
		catch(Exception e) {
			System.out.println("[Invalid Input, Please Enter a Number]");
			mainmenu(input);
		}
		
	}

	public static void applypToPatient(Scanner input) {
		System.out.println("Task:  Apply prescription to a patient\n Please choose a patient to work with");
		for (Pasient i : pasientliste) {
			System.out.println(i.PasientID + ": " + i.henteNavn() + ", (" + i.hentefodselnummer() + ")");
		}
		try {
			if (input.hasNext()) {
				String s = input.next();
				int m = Integer.parseInt(s);
				Pasient thePatient = null;
				for (Pasient l : pasientliste) {
					if (m == l.PasientID) {
						thePatient = l;
					}
				}
				System.out.println("Chosen: " + thePatient.henteNavn() + " (" + thePatient.hentefodselnummer() + ")");
				if (thePatient.sinReseptListe.stoerrelse() != 0) {
					System.out.println("Please choose one prescription, enter the number");
					for (Resept u : thePatient.sinReseptListe) {
						System.out
								.println(u.Id + ": " + u.hentLegemiddel().hentNavn() + " (Reit: " + u.hentReit() + ")");
					}
					if (input.hasNext()) {
						String y = input.next();
						Resept thePrescription = null;
						int id = Integer.parseInt(y);
						for (Resept s1 : thePatient.sinReseptListe) {
							if (id == s1.Id) {
								thePrescription = s1;
							}
						}
						if (thePrescription.bruk()) {
							System.out.println("Prescription " + thePrescription.hentLegemiddel().hentNavn()
									+ " is used, " + thePrescription.hentReit() + " reit left.");
						} else {
							System.out.println(":( There is no reit left to use for this prescription");
						}
					}
				} else {
					System.out.println("There is no avaiable prescription for this patient!!");
				}
				System.out.println();
				backToMainMenu(input);

			}
		}
		catch(Exception e) {
			System.out.println("Your Choice is Invalid, Try Again");
			applypToPatient(input);
		}
	}
	public static void statistic(Scanner input) {
		String m = "q";
		if(input.hasNext()) {
			 m = input.next();
		}
		int count = 0;
		switch (m){
			case "a":
				for(Resept i: reseptliste) {
					if(i.hentLegemiddel() instanceof Vanedannende) {
						count++;	
					}
				}
				System.out.println("Total number of prescriptions for addictive drugs: "+count);
				break;
			case "b":
				for(Resept i: reseptliste) {
					if(i.hentLegemiddel() instanceof Narkotisk) {
						count++;	
					}
				}
				System.out.println("Total number of prescriptions for narotic drugs: "+count);
				break;
			case "c":
				System.out.println("Doctors with narotic prescription: ");
				int count2 = 0;
				for(Lege i: legeliste) {
					int count1= 0;
					for(Resept m1: i.henteLegeUtskrevendeResept()) {
						if(m1.hentLegemiddel() instanceof Narkotisk) {
							count1++;
						}
					}
					if (count1!=0) {
						count2++;
						System.out.println(i.hentLegeNavn() + ": "+count1);
					}
				}
				System.out.println("Sum: "+ count2);
				System.out.println("Patients with narotic prescription: ");
				int count3 = 0;
				for(Pasient k: pasientliste) {
					int count4= 0;
					for(Resept j: k.sinReseptListe) {
						if(j.hentLegemiddel() instanceof Narkotisk) {
							count4++;
						}
					}
					if (count4!=0) {
						count3++;
						System.out.println(k.henteNavn() +", "+k.hentefodselnummer()+",  "+count4);
					}
				}
				System.out.println("Sum: "+ count3);
				
				break;
			default:
				System.out.println("[invalid input, choose again]");
				statistic(input);
				break; 
		}
		System.out.println();
		backToMainMenu(input);
	}

	@SuppressWarnings("resource")

	public static void writeToFile(Scanner input) {
		// ask user to enter the file name
		System.out.println("Please enter the name of the new file");
		String w = "new";
		if (input.hasNext()) {
			w = input.next();
		}
		/*
		 * else { System.out.println("Warning: Filename is not given");
		 * writeToFile(input); }
		 */
		// create printerwriter to print to the new file
		// System.out.println("filename: "+w);
		try {
			PrintWriter writer = new PrintWriter(w+".txt", "UTF-8");
			// patient writing to the file
			writer.println("# Pasienter (navn, fnr)");
			for (Pasient i : pasientliste) {
				writer.println(i.henteNavn() + "," + i.hentefodselnummer());
			}
			// legemiddel writing to the file
			writer.println("# Legemidler (navn,type,pris,virkestoff,[styrke]");
			for (Legemiddel i : legemiddelliste) {
				writer.print(i.hentNavn() + "," + i.getClass().getSimpleName().toLowerCase() + "," + i.hentPris() + ","
						+ i.hentVirkestoff());
				if (i instanceof Narkotisk) {
					writer.println("," + ((Narkotisk) i).hentNarkotiskStyrke());
				} else if (i instanceof Vanedannende) {
					writer.println("," + ((Vanedannende) i).hentVanedannendeStyrke());
				} else {
					writer.println();
				}
			}
			// lege writing to the file
			writer.println("# Leger (navn,kontrollid / 0 hvis vanlig lege)");
			for (Lege i : legeliste) {
				if (i instanceof Spesialister) {
					writer.println(i.hentLegeNavn() + "," + ((Spesialister) i).hentKontrollID());
				} else {
					writer.println(i.hentLegeNavn() + ",0");
				}

			}
			// resept: writing to the file
			writer.println("# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])");
			for (Resept i : reseptliste) {
				writer.print(i.hentLegemiddel().hentId() + "," + i.hentLege().hentLegeNavn() + ","
						+ i.hentPasient().PasientID + ",");
				if (i instanceof Blaaresepter) {
					writer.println("blaa" + "," + i.hentReit());
				} else if (i instanceof Presept) {
					writer.println("p");
				} else if (i instanceof Militaerresepter) {
					writer.println("militaer," + i.hentReit());
				} else {
					writer.println("hvit," + i.hentReit());

				}

			}
			System.out.println("File writing completed, Please check <3");

			writer.close();
			backToMainMenu(input);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	// main menu input 1---print()
	public static void print(Scanner input) {
		String alpha = "z";

		System.out.println("[Choose from the list: ]\n a.Print doctors \n b.Print Specialists \n c.Print Patient");
		System.out.println(" d.Print drugs \n e.Print prescriptions");
		if (input.hasNext()) {
			alpha = input.next();
		} else {
			System.out.println("No input");
		}
		switch (alpha) {
		case "a":
			System.out.println("Task admitted: print all doctors: " + legeliste.stoerrelse());
			for (Lege i : legeliste) {
				System.out.println(i);
			}
			System.out.println();
			backToMainMenu(input);
			break;
		case "b":
			System.out.println("Task admitted: print all specialists: " + spesialisterliste.stoerrelse());
			for (Lege i : spesialisterliste) {
				System.out.println(i);
			}
			System.out.println();
			backToMainMenu(input);
			break;
		case "c":
			System.out.println("Task admitted: print all patients: " + pasientliste.stoerrelse());
			for (Pasient i : pasientliste) {
				System.out.println(i);
			}
			System.out.println();
			backToMainMenu(input);
			break;
		case "d":
			System.out.println("Task admitted: print all drugs: " + legemiddelliste.stoerrelse());
			for (Legemiddel i : legemiddelliste) {
				System.out.println(i);
			}
			System.out.println();
			backToMainMenu(input);
			break;
		case "e":
			System.out.println("Task admitted: print all prescriptions: " + reseptliste.stoerrelse());
			for (Resept i : reseptliste) {
				System.out.println(i);
			}
			System.out.println();
			backToMainMenu(input);
			break;
		default:
			System.out.println("[invalid input, choose again]");
			print(input);
		}

	}

	public static void backToMainMenu(Scanner input) {
		System.out.println("[Back to main(type 0) or quit system(type 100),Please enter a number!]");
		// int b = 100;
		try{
			//System.out.println("step1");
			if(input.hasNext()) {
				String b1 = input.next();
				int b = Integer.parseInt(b1);
				//System.out.println("step2");
				if (b == 0) {
					mainmenu(input);
				} else if (b == 100) {
					System.out.println("System Ends");
				}
				else {
					System.out.println("[Invalid input, choose again]");
					backToMainMenu(input);
				}

			}
					}
		catch (Exception e) {
			System.out.println("[Invalid Input, Please Enter a Number]");
			backToMainMenu(input);
		}
		
		

	}

	@SuppressWarnings("resource")
	private static void lesFraFil(File fil) throws NumberFormatException, UlovligUtskrift {
		Scanner scanner = null;
		try {
			scanner = new Scanner(fil);
			System.out.println("Reading File " + fil);
		} catch (FileNotFoundException e) {
			// System.out.println(System.getProperty("user.dir"));
			System.out.println("Fant ikke filen, starter opp som et tomt Legesystem");
			return;
		}
		// System.out.println("found the file");
		String innlest = scanner.nextLine();

		while (scanner.hasNextLine()) {

			String[] info = innlest.split(" ");

			// Legger til alle pasientene i filen
			if (info[1].compareTo("Pasienter") == 0) {
				while (scanner.hasNextLine()) {
					innlest = scanner.nextLine();

					if (innlest.charAt(0) == '#') {

						break;
					}
					String[] a = innlest.split(",");
					Pasient w = new Pasient(a[0], a[1]);
					pasientliste.leggTil(w);

				}

			}

			// Legger inn Legemidlene OK
			else if (info[1].compareTo("Legemidler") == 0) {
				while (scanner.hasNextLine()) {
					innlest = scanner.nextLine();
					// Om vi er ferdig med å legge til legemidler, bryt whileløkken,
					// slik at vi fortsetter til koden for å legge til leger
					if (innlest.charAt(0) == '#') {
						break;
					}
					String[] legemiddel = innlest.split(",");
					// System.out.println(legemiddel.length);
					for (int i = 1; i <= legemiddel.length - 2; i++) {
						if (legemiddel[i].compareTo("narkotisk") == 0 || legemiddel[i].compareTo("a") == 0) {
							//
							// MERK: Her må du legge til et PreparatA i en lenkeliste
							//
							double c1 = Double.parseDouble(legemiddel[i + 1]);
							double c2 = Double.parseDouble(legemiddel[i + 2]);
							int c3 = Integer.parseInt(legemiddel[i + 3]);
							String w = "";
							for (int s = 0; s < i; s++) {
								if (legemiddel[s] != null) {
									w += legemiddel[s];
									// System.out.println(w);
								}
							}

							Narkotisk n = new Narkotisk(w, c1, c2, c3);
							legemiddelliste.leggTil(n);

						} else if (legemiddel[i].compareTo("vanedannende") == 0 || legemiddel[i].compareTo("b") == 0) {
							double c1 = Double.parseDouble(legemiddel[i + 1]);
							double c2 = Double.parseDouble(legemiddel[i + 2]);
							int c3 = Integer.parseInt(legemiddel[i + 3]);
							String w = "";
							for (int s = 0; s < i; s++) {
								if (legemiddel[s] != null) {
									w += legemiddel[s];
								}
							}
							Vanedannende n = new Vanedannende(w, c1, c2, c3);
							legemiddelliste.leggTil(n);
						} else if (legemiddel[i].compareTo("vanlig") == 0 || legemiddel[i].compareTo("c") == 0) {
							//
							// MERK: Her må du legge til et PreparatC i en lenkeliste
							//
							double c1 = Double.parseDouble(legemiddel[i + 1]);
							double c2 = Double.parseDouble(legemiddel[i + 2]);
							String w = "";
							for (int s = 0; s < i; s++) {
								if (legemiddel[s] != null) {
									w += legemiddel[s];
								}
							}

							Vanlig n = new Vanlig(w, c1, c2);
							legemiddelliste.leggTil(n);

						}
					}

				}
			}
			// Legger inn leger OK
			else if (info[1].compareTo("Leger") == 0) {
				while (scanner.hasNextLine()) {
					innlest = scanner.nextLine();
					// Om vi er ferdig med å legge til leger, bryt whileløkken,
					// slik at vi fortsetter til koden for å legge til resepter
					if (innlest.charAt(0) == '#') {
						break;
					}
					info = innlest.split(",");
					int kontrollid = Integer.parseInt(info[1]);
					if (kontrollid == 0) {

						Lege a = new Lege(info[0]);
						legeliste.leggTil(a);
					} else {

						Lege a = new Spesialister(info[0], Integer.parseInt(info[1]));
						spesialisterliste.leggTil(a);
						legeliste.leggTil((Lege) a);
						// System.out.println("spesialister in legeliste!!!!!");
					}
				}

			}
			// Legger inn Resepter
			else if (info[1].compareTo("Resepter") == 0) {
				/*
				 * for(Lege i:legeliste) { System.out.println(i); }
				 */
				//int c = 1;
				int count = 0;
				Lenkeliste<String[]> bug = new Lenkeliste<String[]>();
				while (scanner.hasNextLine()) {
					innlest = scanner.nextLine();
					// System.out.println("Read line:"+ c);
					//c++;
					info = innlest.split(",");
					// l1 is the Id of the legemiddel
					int l1 = Integer.parseInt(info[0]);
					Legemiddel l = null;
					for (Legemiddel i : legemiddelliste) {
						if (i.hentId() == l1) {
							l = i;
							// System.out.println("found a legemiddel");
							break;
						}
					}
					// n1 is the doctor's name
					String n1 = info[1];
					// System.out.println(n1);
					Lege n = null;
					for (Lege i2 : legeliste) {

						if (i2.hentLegeNavn().equals(n1)) {

							n = i2;
							// System.out.println("found a lege");
							break;
						}
					}
					// p1 is the patient ID
					int p1 = Integer.parseInt(info[2]);
					// System.out.println(p1);
					Pasient p = null;
					for (Pasient i1 : pasientliste) {
						// System.out.println(i1.PasientID);
						if (i1.PasientID == p1) {
							p = i1;
							// System.out.println("found a pasient");
							break;
						}
					}

					switch (info[3]) {
					case "hvit":
						try {

							HvitResept pr = n.skrivHvitResept(l, p, Integer.parseInt(info[4]));
							reseptliste.leggTil(pr);

							break;
						} catch (Exception e) {
							count++;
							// System.out.println("hvit" + count);
							break;
						}
					case "blaa":
						try {

							Blaaresepter pr1 = n.skrivBlaaResept(l, p, Integer.parseInt(info[4]));
							reseptliste.leggTil(pr1);

							break;
						} catch (Exception e) {
							count++;
							// System.out.println("bloaa" + count);
							break;
						}
					case "p":
						// System.out.println("yes p");
						try {

							Presept pr2 = n.skrivPresept(l, p);

							reseptliste.leggTil(pr2);

							break;
						} catch (Exception e) {
							count++;
							// System.out.println(" p" + count);
							break;
						}
					case "militaer":
						// System.out.println("yes mm");
						try {

							Militaerresepter pr3 = n.skrivMillitaerResept(l, p, Integer.parseInt(info[4]));
							reseptliste.leggTil(pr3);

							break;
						} catch (Exception e) {
							count++;

							// System.out.println("mili" + count);
							break;
						}
					default:

						bug.leggTil(info);
					}

				}
				System.out.println("Invalid prescriptions in total: " + count);
				if (bug.stoerrelse() != 0) {
					System.out.println("Incomplete prescription Count:" + bug.stoerrelse() + ". \nas followed:");
				}
				for (String[] i : bug) {
					for (String a : i) {
						System.out.print(a);
					}
					System.out.println();
				}
				System.out.println();

			}
		}
	}
}
