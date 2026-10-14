import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ComplexMatrix a = null, b = null;

        while (true) {
            System.out.println("\n1. Ввести матрицу A\n2. Ввести матрицу B\n3. Показать матрицы\n4. Сложить (A + B)\n5. Умножить (A * B)\n6. Разделить (A / B)\n7. Транспонировать A\n8. Определитель A\n0. Выход");
            System.out.print("Выбор: ");
            int choice = sc.nextInt();

            if (choice == 0) break;

            if (choice == 1 || choice == 2) {
                System.out.print("Строки: ");
                int r = sc.nextInt();
                System.out.print("Столбцы: ");
                int c = sc.nextInt();
                ComplexMatrix m = new ComplexMatrix(r, c);
                System.out.println("Введите элементы (действ и мним через пробел):");
                for (int i = 0; i < r; i++) {
                    for (int j = 0; j < c; j++) {
                        m.data[i][j] = new ComplexNumber(sc.nextDouble(), sc.nextDouble());
                    }
                }
                if (choice == 1) a = m; else b = m;
            } else if (choice == 3) {
                if (a != null) System.out.println("A:\n" + a);
                if (b != null) System.out.println("B:\n" + b);
            } else if (choice == 4 && a != null && b != null) {
                System.out.println(a.add(b));
            } else if (choice == 5 && a != null && b != null) {
                System.out.println(a.multiply(b));
            } else if (choice == 6 && a != null && b != null) {
                System.out.println(a.divide(b));
            } else if (choice == 7 && a != null) {
                System.out.println(a.transpose());
            } else if (choice == 8 && a != null) {
                System.out.println("Определитель: " + a.determinant());
            } else {
                System.out.println("Ошибка или матрицы не созданы!");
            }
        }
        sc.close();
    }
}
