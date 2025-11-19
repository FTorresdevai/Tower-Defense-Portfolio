
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


    public class Shape {
        private List<Pixel> pixels;

        public Shape() {
            pixels = new ArrayList<>();
        }

        public void add(int dx, int dy, char c) {
            pixels.add(new Pixel(dx, dy, c));
        }

        public List<Pixel> getPixels() {
            return pixels;
        }




}
