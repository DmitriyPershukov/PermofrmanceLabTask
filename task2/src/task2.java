import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class task2 {
    public static void main(String[] args) {
        Point ellipseCenter;
        double xRadius;
        double yRadius;
        List<Point> points = new ArrayList<>();
        try(
            BufferedReader ellipseCoordinatesReader = new BufferedReader(new FileReader(args[0]));
            BufferedReader pointsCoordinatesReader = new BufferedReader(new FileReader(args[1]))
        ) {
            ellipseCenter = parseCoordinatesLine(ellipseCoordinatesReader.readLine());
            String[] radiusCoordinates = ellipseCoordinatesReader.readLine().split(" ");
            xRadius = Double.parseDouble(radiusCoordinates[0]);
            yRadius = Double.parseDouble(radiusCoordinates[1]);

            String line;
            while((line = pointsCoordinatesReader.readLine()) != null){
                points.add(parseCoordinatesLine(line));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        double ellipseEquationRightSide;
        for(Point point: points){
            ellipseEquationRightSide =
                    Math.pow((point.getX() - ellipseCenter.getX()), 2) / Math.pow(xRadius, 2)
                    + Math.pow((point.getY() - ellipseCenter.getY()), 2) / Math.pow(yRadius, 2);
            if (Double.compare(ellipseEquationRightSide, 1.0) == 0){
                System.out.print("0\n");
            } else if (ellipseEquationRightSide < 1){
                System.out.print("1\n");
            } else{
                System.out.print("2\n");
            }
        }
    }

    private static Point parseCoordinatesLine(String line){
        double[] coordinates = Arrays.stream(line.split(" "))
                .limit(2)
                .mapToDouble(Double::parseDouble)
                .toArray();
        return new Point(coordinates[0], coordinates[1]);
    }
}