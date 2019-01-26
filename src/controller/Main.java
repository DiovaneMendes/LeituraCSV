package controller;

public class Main {
    public static void main (String args[]){
		LeituraDeCSV l = new LeituraDeCSV();

		l.lerColuna("name").forEach(System.out::println);
	}
}