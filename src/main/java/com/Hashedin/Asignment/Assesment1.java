package com.Hashedin.Asignment;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Assesment1 {

	private static final String SAMPLE_CSV_FILE_PATH = "src/main/java/com/Hashedin/Resources/netflix_titles.csv";
	private static int exitStatus = 0;
	private static int userInput = 0;

	public static void Task1() {
		double start = System.currentTimeMillis();
// ...


		CSVParser csvParser = null;
		try {
			Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
			csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
			int loopCount = 0;
			Scanner sc = new Scanner(System.in);
			System.out.print("Please enter the number of records to fetch with type(TV Show) :   ");
			int numberOfRecordToFetch = sc.nextInt();
			for (CSVRecord csvRecord : csvParser) {
				if (loopCount >= numberOfRecordToFetch) {
					break;
				}
				// Accessing Values by Column Index
				String type = csvRecord.get(1);
				if (type.equals("TV Show")) {
					loopCount++;
					int col = 0;
					for (col = 0; col < 11; col++) {
						System.out.print(csvRecord.get(col) + ",");

					}
					System.out.println(csvRecord.get(col));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeCsvParser(csvParser);
			double finish = System.currentTimeMillis();
			System.out.println((finish - start)/1000 +"seconds");

		}

	}

	public static void Task2() {
		double start = System.currentTimeMillis();
		CSVParser csvParser = null;
		try {
			Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
			csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
			int loopCount = 0;
			Scanner sc = new Scanner(System.in); // System.in is a standard input stream.
			System.out.print(
					"Please enter the number of records to fetch where listed_in contains 'Horror Movies'  :   ");
			int numberOfRecordToFetch = sc.nextInt();
			for (CSVRecord csvRecord : csvParser) {
				if (loopCount >= numberOfRecordToFetch) {
					break;
				}
				// Accessing Values by Column Index
				String listed_in = csvRecord.get(10);
				if (listed_in.contains("Horror Movies")) {
					loopCount++;
					int col = 0;
					for (col = 0; col < 11; col++) {
						System.out.print(csvRecord.get(col) + ",");

					}
					System.out.println(csvRecord.get(col));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeCsvParser(csvParser);
			double finish = System.currentTimeMillis();
			System.out.println((finish - start)/1000 +"seconds");

		}

	}

	public static void Task3() {
		double start = System.currentTimeMillis();
		CSVParser csvParser = null;
		try {
			Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
			csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
			int loopCount = 0;
			Scanner sc = new Scanner(System.in); // System.in is a standard input stream.
			System.out
					.print("Please enter the number of records to fetch from type 'Movie' where country is 'India':  ");
			int numberOfRecordToFetch = sc.nextInt();
			for (CSVRecord csvRecord : csvParser) {
				if (loopCount >= numberOfRecordToFetch) {
					break;
				}
				String type = csvRecord.get(1);
				String country = csvRecord.get(5);
				if (type.equals("Movie") && country.equals("India")) {
					loopCount++;
					int col = 0;
					for (col = 0; col < 11; col++) {
						System.out.print(csvRecord.get(col) + ",");

					}
					System.out.println(csvRecord.get(col));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeCsvParser(csvParser);
			double finish = System.currentTimeMillis();
			System.out.println((finish - start)/1000 +"seconds");

		}

	}


	public static void Task4() throws ParseException {
		double start = System.currentTimeMillis();
		CSVParser csvParser = null;
		try {
			Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
			csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
			int loopCount = 0;
			Scanner sc = new Scanner(System.in);
			Date startDate = null;
			Date endDate = null;
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat format2 = new SimpleDateFormat("MMMM dd, yyyy");
			System.out.println("Enter Start date (MM/dd/yyyy):");
			String startDateToFilter = sc.nextLine();
			System.out.println("Enter End date (MM/dd/yyyy):");
			String endDateToFilter = sc.nextLine();
			if (null != startDateToFilter && startDateToFilter.trim().length() > 0) {
				try {
					startDate = format.parse(startDateToFilter);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (null != endDateToFilter && endDateToFilter.trim().length() > 0) {
				try {
					endDate = format.parse(endDateToFilter);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			int userInput = 0;
			do {
				System.out.println("Please select any one of below options:\n");
				System.out.println("1. Print n records where type: TV Show\n");
				System.out.println(
						"2. Print n records where listed_in: Horror Movies \n");
				System.out.println("3. Print n type: Movie where country: India\n");
				userInput = sc.nextInt();
				if (userInput > 0 && userInput < 4) {
					break;
				} else {
					System.out.println("Wrong Choice!");
				}
			} while (true);
			System.out.print("Enter the number of records to fetch between " + startDate + " and " + endDate + ":  ");

			int numberOfRecordToFetch = sc.nextInt();
			int recordNo = 0;
			Date formated_date_added = null;
			Timestamp beforeQuerry = Timestamp.from(Instant.now());
			Timestamp afterQuerry = null;
			for (CSVRecord csvRecord : csvParser) {
				recordNo++;
				if (loopCount >= numberOfRecordToFetch) {
					break;
				}
				// Accessing Values by Column Index
				String date_added = csvRecord.get(6);

				try {
					if (recordNo > 1) {
						formated_date_added = format2.parse(date_added);
					}
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					// e1.printStackTrace();
					System.out.print("");
				}
				if ((recordNo > 1) && (formated_date_added.compareTo(startDate) > 0)
						&& (formated_date_added.compareTo(endDate) < 0)) {
					if (userInput == 1) {
						String type = csvRecord.get(1);
						if (type.equals("TV Show")) {
							loopCount++;
							int col = 0;
							for (col = 0; col < 11; col++) {
								System.out.print(csvRecord.get(col) + ",");

							}
							System.out.println(csvRecord.get(col));
						}

					} else if (userInput == 2) {
						// Accessing Values by Column Index
						String listed_in = csvRecord.get(10);
						if (listed_in.contains("Horror Movies")) {
							loopCount++;
							int col = 0;
							for (col = 0; col < 11; col++) {
								System.out.print(csvRecord.get(col) + ",");

							}
							System.out.println(csvRecord.get(col));
						}

					} else {
						// Accessing Values by Column Index
						String type = csvRecord.get(1);
						String country = csvRecord.get(5);
						if (type.equals("Movie") && country.equals("India")) {
							loopCount++;
							int col = 0;
							for (col = 0; col < 11; col++) {
								System.out.print(csvRecord.get(col) + ",");

							}
							System.out.println(csvRecord.get(col));
						}
					}
				}
			}
			afterQuerry = Timestamp.from(Instant.now());
			System.out.print(
					"\n\n\n\nTIME TAKEN TO EXECUTE THE QUERY IS:  " + (afterQuerry.getTime() - beforeQuerry.getTime()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeCsvParser(csvParser);
			double finish = System.currentTimeMillis();
			System.out.println((finish - start)/1000 +"seconds");

		}

	}

	private static void closeCsvParser(CSVParser csvParser) {
		if (null != csvParser) {
			try {
				csvParser.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void Menu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please select any one of below options:\n");
		System.out.println("1. Print n records where type: TV Show\n");
		System.out.println("2. Print n records where listed_in: Horror Movies\n");
		System.out.println("3. Print n type Movie where country: India\n");
		System.out.println("4. print in between field date_added\n");
		System.out.println("5. For Exitn\n\n");
		System.out.print("Enter Your Input:  ");
		userInput = sc.nextInt();
		// Switch case
		switch (userInput) {
		case 1:
			Task1();
			break;
		case 2:
			Task2();
			break;
		case 3:
			Task3();
			break;
		case 4:
			try {
				Task4();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			break;
		case 5:
			exitStatus = 1;
			break;
		default:
			System.out.println("Invalid Choice\n");
			break;
		}
	}
	public static void main(String[] args) throws Exception {
		char ch = 'y';
		int exitStatus = 0;
		Scanner sc = new Scanner(System.in);
		while (ch == 'y' || ch == 'Y') {
			Menu();
			if (exitStatus == 1) {
				break;
			}
			System.out.print("\n\nDo you want to continue??? Y/N : ");
			ch = sc.next().charAt(0);
		}
		sc.close();
	}

}
