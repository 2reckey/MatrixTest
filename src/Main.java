public class Main {
    public static void main(String[] args) {
// Сравнение скорости работы

//        double[][] matrix={
//        {2,1},
//        {1,2}
//        };
        double[][] matrix = {
                {1, 2, 3, 4, 1},
                {2, 3, 4, 1, 1},
                {3, 4, 1, 2, 1},
                {4, 1, 2, 3, 1},
                {1, 1, 2, 3, 4}
        };
        long startTime = System.nanoTime();
        double det = TestMatrix.det(matrix);
        long endTime = System.nanoTime();
        long duration1 = (endTime - startTime);

        long startTime2 = System.nanoTime();
        double det2 = TestMatrix.determinant(matrix);
        long endTime2 = System.nanoTime();
        long duration2 = (endTime2 - startTime2);

        System.out.println("\nОпределитель:");
        System.out.println(det + "\n");
        double div = (double) duration1 / (double) duration2;

        if (duration1 < duration2) {
            System.out.println("Функция вычисления определителья с помощью миноров быстрее функции вычисления определителья с помощью метода треугольника");
        } else {
            System.out.printf("Функция вычисления определителья с помощью метода треугольника в %.3f раз быстрее функции вычисления определителья с помощью миноров\n", div);
        }
        System.out.println("\nМиноры: " + duration1 + "     Треугольник: " + duration2);

        startTime = System.nanoTime();
        double[][] inverseMatrix = TestMatrix.inverseMatrix(matrix);
        endTime = System.nanoTime();
        duration1 = (endTime - startTime);

        startTime2 = System.nanoTime();
        double[][] inverseMatrix2 = TestMatrix.inverseMatrixGauss(matrix);
        endTime2 = System.nanoTime();
        duration2 = (endTime2 - startTime2);
        System.out.println("\nОбратная Матрица:");
        TestMatrix.printMatrix(inverseMatrix);
        div = (double) duration1 / (double) duration2;

        if (duration1 < duration2) {
            System.out.println("Функция вычисления обратной матрицы с помощью миноров быстрее функции вычисления обратной матрицы с помощью метода Гауса");
        } else {
            System.out.printf("Функция вычисления обратной матрицы с помощью метода Гауса в %.3f раз быстрее функции вычисления обратной матрицы с помощью миноров\n", div);
        }
        System.out.println("\nМиноры: " + duration1 + "     Гауcс: " + duration2);
    }
}