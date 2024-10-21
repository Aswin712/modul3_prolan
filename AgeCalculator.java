import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;
import java.time.DateTimeException;

class Person {
    private LocalDate birthDate;

    public Person(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Period calculateAge() {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate);
    }
}

public class AgeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int birthYear = 0, birthMonth = 0, birthDay = 0;

        try {
            // Refaktor #1: Tambahkan validasi input tahun
            int currentYear = LocalDate.now().getYear();
            while (true) {
                System.out.print("Masukkan tahun lahir Anda: ");
                birthYear = scanner.nextInt();
                if (birthYear > 0 && birthYear <= currentYear) {
                    break;
                } else {
                    System.out.println("Tahun lahir tidak valid. Coba lagi.");
                }
            }

            // Refaktor #2: Modularisasi dengan metode terpisah
            System.out.print("Masukkan bulan lahir Anda (1-12): ");
            birthMonth = scanner.nextInt();
            System.out.print("Masukkan hari lahir Anda: ");
            birthDay = scanner.nextInt();

            // Refaktor #3: Penggunaan kelas Person
            LocalDate birthDate = LocalDate.of(birthYear, birthMonth, birthDay);
            Person person = new Person(birthDate);

            // Refaktor #4: Perhitungan umur dalam tahun, bulan, dan hari
            Period age = person.calculateAge();
            System.out.println("Umur Anda adalah: " + age.getYears() + " tahun, "
                    + age.getMonths() + " bulan, dan "
                    + age.getDays() + " hari.");

        } catch (DateTimeException e) {
            System.out.println("Tanggal yang dimasukkan tidak valid.");
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan input.");
        } finally {
            // Refaktor #5: Tambahkan penanganan error dengan try-catch
            scanner.close();
        }
    }
}
