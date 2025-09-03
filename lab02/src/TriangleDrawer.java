public class TriangleDrawer {
    public static void main (String[] args) {
        drawTriangle();
    }
    public static void drawTriangle() {
        int SIZE = 5;
        int i = 0;
        int j = 0;
        while(i < SIZE) {
            while(j < i) {
                System.out.print("*");
                j++;
            }
            System.out.println("*");
            j = 0;
            i++;
        }
    }
}
