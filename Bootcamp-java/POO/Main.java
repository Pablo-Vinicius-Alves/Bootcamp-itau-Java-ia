import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // CLASS
        // var male = new Person();
        // male.setName("João") ;
        // male.setAge(13);

        // var female = new Person();
        // female.setName("Maria");
        // female.setAge(18);

        // System.out.println("Male name: " +male.getName() + male.getAge());
        // System.out.println("Female name: " +female.getName() + female.getAge());

        
        // RECORDS 
        var people = new People("João");
        System.out.println(people);
        System.out.println(people.name());
        scanner.close();
    }
}
