package obli2;
/*
 * NOTES:
 * Scanner try hasnext(), and then only use .next() do not use nextInt()
 * 
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
	// this is the main method, open Scanner once and pass it in different methods
	//it activate the system, read in file and start the main menu
	public static void main(String[] args) throws NumberFormatException, UlovligUtskrift {
		File a = new File("twist.txt");
		lesFraFil(a);
		Scanner input = new Scanner(System.in);
		mainmenu(input);
		input.close();
	}
	//this is the main menu,it gives several options to the user and take user input 
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
		try {
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
						+ pasientliste.stoerrelse() + "\n   drugs:    " + legemiddelliste.stoerrelse()
						+ "\n   prescriptions: " + reseptliste.stoerrelse());
				System.out.println();
				// pass in Scanner input in other methods
				print(input);
				break;
			case 2:
				addElement(input);
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
		//if the user input is not a number, it will be caught as an error.
		} catch (Exception e) {
			System.out.println("[Invalid Input, Please Enter a Number]");
			mainmenu(input);
		}

	}

	public static void addElement(Scanner input) {
		System.out.println(
				"Please choose:\n a. Add a new doctor \n b. Add a new patient\n c. Add a new drug\n d. Add a new prescription");
		try {
			if (input.hasNext()) {
				String s = input.next();
				switch (s) {
				case "a"://add a doctor 
					System.out
							.println("Task 'Add a new doctor' accepted \nPlease enter the doctor name, use'_'as substitute of ' '. \n e.g. if the name is 'Dr. House Box' then enter 'House_Box' \n e.g. if the name 'Marlen Lie' then enter'Marlen_Lie'" );
					String w = "";
					if (input.hasNext()) {
						w += input.next();
						w = w.replace("_", " ");
						w += ",";
					}

					System.out.println("Please enter the control ID or O if not a specialist");
					if (input.hasNext()) {
						w += input.next();
					}
					System.out.println("Added: " + w);
					String[] info = w.split(",");
					int kontrollid = Integer.parseInt(info[1]);
					if (kontrollid == 0) {
						Lege a = new Lege(info[0]);
						legeliste.leggTil(a);
					} else {
						Lege a = new Spesialister(info[0], Integer.parseInt(info[1]));
						spesialisterliste.leggTil(a);
						legeliste.leggTil((Lege) a);
					}
					break;
				case "b"://add a patient
					System.out.println(
							"Task 'Add a new patient' accepted \nPlease enter the first name of the new patient(use'_'as substitute of ' '.):");
					String w1 = "";
					if (input.hasNext()) {
						w1 += input.next();
						w1 += " ";
					}
					System.out.println("Please enter the last name(use'_'as substitute of ' '.):");
					if (input.hasNext()) {
						w1 += input.next();
						w1 += ",";
					}
					System.out.println("Please enter the 'fodselsnummert'");
					if (input.hasNext()) {
						w1 += input.next();
					}
					System.out.println("Added: " + w1);
					String[] a = w1.split(",");
					Pasient w2 = new Pasient(a[0], a[1]);
					pasientliste.leggTil(w2);
					break;
				case "c"://add a drug
					// cuz type in space could cause trouble for Scanner....so use'_'instead,
					// temporary solution.
					System.out.println(
							"Task 'Add a new drug' accepted\nPlease enter the name of the drug\n(!!!!Please use'_' instead of space, e.g. Paralgin_Forte)");
					String w11 = "";
					String e = "";
					if (input.hasNext()) {
						String w17 = input.next();
						w11 += w17.replace("_", " ");
						w11 += ",";
					}
					System.out.println("Please enter the type, choose from narkotisk, vanedannende or vanlig ");
					if (input.hasNext()) {
						e = input.next();
						if (e.compareTo("narkotisk") == 0 || e.compareTo("vanedannende") == 0
								|| e.compareTo("vanlig") == 0) {
							w11 += e;
							w11 += ",";
						} else {
							System.out.println("Your Choice " + e + " is Invalid, Start Over!");
							addElement(input);

						}

					}
					System.out.println("Please enter the price");
					if (input.hasNext()) {
						w11 += input.next();
						w11 += ",";
					}
					System.out.println("Please enter the active ingredient?(virkestoff, a number)");
					if (input.hasNext()) {
						w11 += input.next();
						w11 += ",";
					}
					if (e.compareTo("vanlig") != 0) {
						System.out.println("Please enter the styke, (it is a number)");
						if (input.hasNext()) {
							w11 += input.next();
						}
					}
					System.out.println("Added: " + w11);
					readlineLegemiddel(w11.split(","));
					break;
				case "d"://add a prescription
					System.out.println("Task 'Add a new prescription' accepted\n Choose a Patient, Enter the Number:");
					for(Pasient i6:pasientliste) {
						System.out.println(i6.PasientID+": "+i6.henteNavn());
					}
					Pasient p =null;
					Lege aa= null;
					Legemiddel r1=null;
					if(input.hasNext()) {
						String y1= input.next();
						
						int y2=0;
						try {
						y2= Integer.parseInt(y1);
						}
						catch(Exception e1) {
							System.out.println("Your Choice " + y1 + " is Invalid, Start Over!");
							addElement(input);
						}
						for(Pasient i7:pasientliste) {
							if(y2==i7.PasientID) {
								 p= i7;
							}
						}
						if(p==null) {
							System.out.println("Your Choice " + y2 + " is Invalid, Start Over!");
							addElement(input);
						}
						else {
							System.out.println(p.henteNavn() +"is chosen");
						}
					}
					System.out.println("Choose a doctor:");
					for(Lege i:legeliste) {
						System.out.println(i.hentLegeNavn());
					}
					System.out.println("Please enter the doctor name, use'_'as substitute of ' '.\n e.g. if choose 'Dr. House Box' then enter 'House_Box'\n e.g if choose 'Marlen Lie' then enter'Marlen_Lie'" );
					if(input.hasNext()) {
						String f1 = input.next();
						String f = f1.replace("_"," ");
					   
					    for(Lege g: legeliste) {
					    	if(g.hentLegeNavn().compareTo(f)==0|| g.hentLegeNavn().compareTo("Dr. "+f)==0) {
					    		aa= g;
					    	}
					    }
					    if (aa ==null) {
					    	System.out.println("No such doctor, try again");
					    	addElement(input);
					    }
					    else {
					    	System.out.println(f+" is chosen.");
					    	System.out.println("Choose a drug");
					    	if (aa instanceof Spesialister) {
					    		for(Legemiddel u:legemiddelliste) {
					    			System.out.println(u.hentId()+": "+u.hentNavn());
					    		}
					    	}else {
					    		System.out.println(aa+" is not a specialist therefore no narcotic drug is allowed/Please choose from the list(choose number)");
					    		for(Legemiddel u:legemiddelliste) {
					    			if(u instanceof Vanedannende || u instanceof Vanlig) {
					    				System.out.println(u.hentId()+": "+u.hentNavn());
					    			}
					    		}
					    	}
				    		if(input.hasNext()) {
				    			
				    			String e1 = input.next();
				    			int e2= Integer.parseInt(e1);
				    			for(Legemiddel u:legemiddelliste) {
				    				if(u.hentId()==e2) {
				    					r1= u;	
				    				}
				    			}
				    			 if (e1 ==null) {
								    	System.out.println("No such drug, try again");
								    	addElement(input);
								    }
								    else {
								    	System.out.println(r1+" is chosen.");
								    }
				    		}
					    }
					
				    	System.out.println("Enter type of precription: \nChoose between hvit,blaa,p,militaer");
				    	if(input.hasNext()) {
			    			String e8 = input.next();
			    			if(e8.compareTo("blaa")==0||e8.compareTo("hvit")==0|| e8.compareTo("militaer")==0) {
			    				System.out.println("Please enter reit");
			    				if(input.hasNext()) {
			    					String h= input.next();
			    					int y= Integer.parseInt(h);
			    					if(e8.compareTo("blaa")==0) {
			    						Blaaresepter pr1 = aa.skrivBlaaResept(r1, p,y);
										reseptliste.leggTil(pr1);
										System.out.println("prescription created: "+pr1);
			    					}
			    					else if(e8.compareTo("hvit")==0) {
			    						HvitResept pr1 = aa.skrivHvitResept(r1, p,y);
										reseptliste.leggTil(pr1);
										System.out.println("prescription created: "+pr1);
			    					}	
			    					else if(e8.compareTo("militaer")==0) {
			    						Militaerresepter pr1 = aa.skrivMillitaerResept(r1, p,y);
										reseptliste.leggTil(pr1);
										System.out.println("prescription created: "+pr1);
			    					}		
			    				}
			    			}
			    			
			    			else if(e8.compareTo("p")==0) {
			    				Presept pr = aa.skrivPresept(r1, p);
								reseptliste.leggTil(pr);
								System.out.println("prescription created: "+pr);
			    			}
			    			else {
			    				System.out.println("Your Choice"+ e8+ "is Invalid, Try Again");
			    				addElement(input);
			    			}
				    	}
					}
					break;
				default:
					System.out.println("Your Choice is Invalid, Try Again");
					addElement(input);
				}
			}
			System.out.println();
			backToMainMenu(input);
		} catch (Exception e) {
			System.out.println("Your Choice is Invalid, Try Again");
			addElement(input);
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
		} catch (Exception e) {
			System.out.println("Your Choice is Invalid, Try Again");
			applypToPatient(input);
		}
	}

	public static void statistic(Scanner input) {
		String m = "q";
		if (input.hasNext()) {
			m = input.next();
		}
		int count = 0;
		switch (m) {
		case "a":
			for (Resept i : reseptliste) {
				if (i.hentLegemiddel() instanceof Vanedannende) {
					count++;
				}
			}
			System.out.println("Total number of prescriptions for addictive drugs: " + count);
			break;
		case "b":
			for (Resept i : reseptliste) {
				if (i.hentLegemiddel() instanceof Narkotisk) {
					count++;
				}
			}
			System.out.println("Total number of prescriptions for narotic drugs: " + count);
			break;
		case "c":
			System.out.println("Doctors with narotic prescription: ");
			int count2 = 0;
			for (Lege i : legeliste) {
				int count1 = 0;
				for (Resept m1 : i.henteLegeUtskrevendeResept()) {
					if (m1.hentLegemiddel() instanceof Narkotisk) {
						count1++;
					}
				}
				if (count1 != 0) {
					count2++;
					System.out.println(i.hentLegeNavn() + ": " + count1);
				}
			}
			System.out.println("Sum: " + count2);
			System.out.println("Patients with narotic prescription: ");
			int count3 = 0;
			for (Pasient k : pasientliste) {
				int count4 = 0;
				for (Resept j : k.sinReseptListe) {
					if (j.hentLegemiddel() instanceof Narkotisk) {
						count4++;
					}
				}
				if (count4 != 0) {
					count3++;
					System.out.println(k.henteNavn() + ", " + k.hentefodselnummer() + ",  " + count4);
				}
			}
			System.out.println("Sum: " + count3);

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
			PrintWriter writer = new PrintWriter(w + ".txt", "UTF-8");
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
		try {
			// System.out.println("step1");
			if (input.hasNext()) {
				String b1 = input.next();
				int b = Integer.parseInt(b1);
				// System.out.println("step2");
				if (b == 0) {
					mainmenu(input);
				} else if (b == 100) {
					System.out.println("System Ends");
				} else {
					System.out.println("[Invalid input, choose again]");
					backToMainMenu(input);
				}

			}
		} catch (Exception e) {
			System.out.println("[Invalid Input, Please Enter a Number]");
			backToMainMenu(input);
		}

	}

	public static void readlineLegemiddel(String[] legemiddel) {
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
					readlineLegemiddel(legemiddel);
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
				// int c = 1;
				int count = 0;
				Lenkeliste<String[]> bug = new Lenkeliste<String[]>();
				// while loop while it has next line
				while (scanner.hasNextLine()) {
					innlest = scanner.nextLine();
					// System.out.println("Read line:"+ c);
					// c++;
					info = innlest.split(",");
					// l1 is the Id of the legemiddel
					try {
						int l1 = Integer.parseInt(info[0]);
						Legemiddel l = null;
						for (Legemiddel i : legemiddelliste) {
							if (i.hentId() == l1) {
								l = i;
								// System.out.println("found a legemiddel");
								break;
							}
						}
						// n1 is the doctor's name, index could be out of range here if the data is
						// incomplete.therefore should add try catch in this whole section
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
						if (info[3].compareTo("hvit") == 0 || info[3].compareTo("militaer") == 0
								|| info[3].compareTo("blaa") == 0) {
							int test = Integer.parseInt(info[4]);
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
					} catch (Exception e) {
						bug.leggTil(info);
					}
				}
				System.out.println(
						"Invalid prescriptions[Doctors Issuing Narcotic Drugs Without License] in total: " + count);
				if (bug.stoerrelse() != 0) {
					System.out.println("Incomplete prescription Count:" + bug.stoerrelse() + ". \nas followed:");
				}
				for (String[] i : bug) {
					for (String a : i) {
						System.out.print(a+",");
					}
					System.out.println();
				}
				System.out.println();
			}
		}
	}
}
