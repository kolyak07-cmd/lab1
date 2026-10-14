public class ComplexMatrix {
    int rows, cols;
    ComplexNumber[][] data;

    public ComplexMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new ComplexNumber[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = new ComplexNumber(0, 0);
            }
        }
    }

    // Сложение
    public ComplexMatrix add(ComplexMatrix o) {
        ComplexMatrix res = new ComplexMatrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                res.data[i][j] = this.data[i][j].add(o.data[i][j]);
            }
        }
        return res;
    }

    // Умножение
    public ComplexMatrix multiply(ComplexMatrix o) {
        ComplexMatrix res = new ComplexMatrix(this.rows, o.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < o.cols; j++) {
                ComplexNumber sum = new ComplexNumber(0, 0);
                for (int k = 0; k < this.cols; k++) {
                    sum = sum.add(this.data[i][k].multiply(o.data[k][j]));
                }
                res.data[i][j] = sum;
            }
        }
        return res;
    }

    // Транспонирование
    public ComplexMatrix transpose() {
        ComplexMatrix res = new ComplexMatrix(cols, rows);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                res.data[j][i] = this.data[i][j];
            }
        }
        return res;
    }

    // Определитель (метод Гаусса)
    public ComplexNumber determinant() {
        int n = rows;
        ComplexMatrix m = cloneMat();
        ComplexNumber det = new ComplexNumber(1, 0);

        for (int i = 0; i < n; i++) {
            int pivot = i;
            while (pivot < n && (m.data[pivot][i].real * m.data[pivot][i].real + m.data[pivot][i].imag * m.data[pivot][i].imag) < 1e-10) {
                pivot++;
            }
            if (pivot == n) return new ComplexNumber(0, 0);
            if (pivot != i) {
                ComplexNumber[] temp = m.data[i];
                m.data[i] = m.data[pivot];
                m.data[pivot] = temp;
                det = det.multiply(new ComplexNumber(-1, 0));
            }
            det = det.multiply(m.data[i][i]);
            for (int j = i + 1; j < n; j++) {
                ComplexNumber factor = m.data[j][i].divide(m.data[i][i]);
                for (int k = i; k < n; k++) {
                    m.data[j][k] = m.data[j][k].subtract(factor.multiply(m.data[i][k]));
                }
            }
        }
        return det;
    }

    // Упрощенное деление (умножение на обратную через единичную матрицу или заглушка)
    public ComplexMatrix divide(ComplexMatrix o) {
        // Для простейшей сдачи можно использовать умножение на инвертированную (если требуется)
        // Либо вызвать метод решения. Оставим базовую логику умножения на обратную:
        return this.multiply(o.inverseSimple());
    }

    public ComplexMatrix inverseSimple() {
        // Упрощенный поиск обратной для теста (через союзную матрицу)
        double d = this.determinant().real; // упрощенно для примера
        int n = rows;
        ComplexMatrix adj = new ComplexMatrix(n, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                adj.data[j][i] = this.getSub(i, j).determinant();
                if ((i + j) % 2 != 0) adj.data[j][i] = adj.data[j][i].multiply(new ComplexNumber(-1, 0));
            }
        }
        ComplexNumber det = this.determinant();
        ComplexMatrix res = new ComplexMatrix(n, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res.data[i][j] = adj.data[i][j].divide(det);
            }
        }
        return res;
    }

    private ComplexMatrix getSub(int r, int c) {
        ComplexMatrix sub = new ComplexMatrix(rows - 1, cols - 1);
        int ti = 0;
        for (int i = 0; i < rows; i++) {
            if (i == r) continue;
            int tj = 0;
            for (int j = 0; j < cols; j++) {
                if (j == c) continue;
                sub.data[ti][tj] = this.data[i][j];
                tj++;
            }
            ti++;
        }
        return sub;
    }

    private ComplexMatrix cloneMat() {
        ComplexMatrix c = new ComplexMatrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                c.data[i][j] = new ComplexNumber(data[i][j].real, data[i][j].imag);
            }
        }
        return c;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            sb.append("[ ");
            for (int j = 0; j < cols; j++) {
                sb.append(data[i][j]).append("\t");
            }
            sb.append("]\n");
        }
        return sb.toString();
    }
}

