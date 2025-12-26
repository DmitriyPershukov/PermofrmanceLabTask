import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class task1 {
    public static void main(String[] args) {
        int firstArrayLength = Integer.parseInt(args[0]);
        int firstIntervalLength = Integer.parseInt(args[1]);
        int secondArrayLength = Integer.parseInt(args[2]);
        int secondIntervalLength = Integer.parseInt(args[3]);

        int[] circularArray = IntStream
                .range(1, Math.max(firstArrayLength, secondArrayLength) + 1)
                .toArray();
        int firstLastIntervalElement = 0;
        int secondLastIntervalElement = 0;
        int firstPointer = 0;
        int secondPointer = 0;
        ArrayList<Integer> firstPath = new ArrayList<>();
        ArrayList<Integer> secondPath = new ArrayList<>();
        while(firstLastIntervalElement != 1 || secondLastIntervalElement != 1){
            if (firstLastIntervalElement != 1){
                firstPath.add(circularArray[firstPointer]);
                firstPointer = (firstPointer + firstIntervalLength - 1) % firstArrayLength;
                firstLastIntervalElement = circularArray[firstPointer];
            }
            if (secondLastIntervalElement != 1){
                secondPath.add(circularArray[secondPointer]);
                secondPointer = (secondPointer + secondIntervalLength - 1) % secondArrayLength;
                secondLastIntervalElement = circularArray[secondPointer];
            }
        }
        System.out.println(converListToString(firstPath) + converListToString(secondPath));
    }

    private static String converListToString(List<Integer> list){
        return list
                .stream()
                .map(String::valueOf)
                .reduce("", (subtotal, element) -> subtotal + element);
    }
}