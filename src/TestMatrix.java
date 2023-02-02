public class TestMatrix {

    //функция вычисления определителя используя миноры
    public static double det(double[][] matrix) {
        int size = matrix.length;
        double det = 0;
        if (size == 1)
            det = matrix[0][0];
        else
            for (int i = 0; i < size; i++)
                det += (i % 2 == 0 ? 1 : -1) * matrix[0][i] * det(getMinor(matrix, 0, i));
        return det;
    }

    //функция вычисления определителя с помощью преоброзования матрицы к треугольной
    public static double determinant(double[][] a) {
        double result = 1;
        double[][] matrix = new double[a.length][a.length];
        for (int i = 0; i < a.length; i++)
            matrix[i] = a[i].clone();
        int n = matrix.length;
        //Идет преоброзование матрицы к треугольному виду с помощью метода Гаусса
        //после данных преоброзований определитель марицы будет считатся как произведение диоганальных элементов
        for (int i = 0; i < n; i++) {
            // если диагональный элемент равен 0, то матрица не имеет обратной
            if (matrix[i][i] == 0) return 0;
            //замечание: для использования функции в быту, а не для теста, нужно поменять преоброзовать матрицу
            //так, чтобы matrix[0][0]!=0 например {{0,1},{1,1}} выдаст 0,
            //нужно поменять строки местами и не забыть домножить определитель на -1

            // делаем матрицу треугольной
            for (int j = i + 1; j < n; j++) {
                double m = matrix[j][i] / matrix[i][i];
                for (int k = 0; k < n; k++)
                    matrix[j][k] -= m * matrix[i][k];
            }
            result *= matrix[i][i];
        }
        return result;
    }

    // Функция вычисления обратной матрицы используя миноры
    public static double[][] inverseMatrix(double[][] matrix) {
        int n = matrix.length;
        double[][] inverse = new double[n][n];
        double det = det(matrix);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double[][] minor = getMinor(matrix, i, j);
                inverse[j][i] = ((i + j) % 2 == 0 ? 1 : -1) * det(minor) / det;
            }
        }

        return inverse;
    }

    // Функция вычисления обратной матрицы с помощью метода Гаусса
    public static double[][] inverseMatrixGauss(double[][] matrix) {
        int n = matrix.length;
        double[][] inverse = new double[n][n];
        double[][] tmp = new double[n][2 * n];

        // инициализация вспомогательной матрицы
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                tmp[i][j] = matrix[i][j];

            // добавляем единичную матрицу
            tmp[i][n + i] = 1;
        }

        // трансформация матрицы так чтобы слева по диоганалям стояли 1 с помощью линейных преобразований
        for (int i = 0; i < n; i++) {
            double tmpDiv = tmp[i][i];

            // проверяем на 0
            // Замечание тоже что и в determinant()
            if (tmpDiv == 0) return null;

            for (int j = 0; j < 2 * n; j++)
                tmp[i][j] /= tmpDiv;

            for (int k = 0; k < n; k++)
                if (k != i) {
                    double tmpMul = tmp[k][i];
                    for (int j = 0; j < 2 * n; j++)
                        tmp[k][j] -= tmp[i][j] * tmpMul;
                }
        }

        // получаем обратную матрицу
        for (int i = 0; i < n; i++) {
            for (int j = n; j < 2 * n; j++)
                inverse[i][j - n] = tmp[i][j];
        }

        return inverse;
    }

    //получение миноров матрицы
    public static double[][] getMinor(double[][] matrix, int row, int col) {
        double[][] minor = new double[matrix.length - 1][matrix.length - 1];
        int dI = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (i == row) {
                dI = 1;
                continue;
            }
            int dJ = 0;
            for (int j = 0; j < matrix.length; j++) {
                if (j == col) dJ = 1;
                else minor[i - dI][j - dJ] = matrix[i][j];
            }
        }
        return minor;
    }

    //выводит матрицу на экран
    public static void printMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%.3f ", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
