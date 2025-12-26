import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class task4 {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String line;
            while((line = br.readLine()) != null){
                nums.add(Integer.parseInt(line));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Collections.sort(nums);
        int ceiledMedian = (int)Math.ceil(findMedian(nums));
        int stepsCount = nums
                .stream()
                .map((x) -> Math.abs(x - ceiledMedian))
                .reduce(0, (subtotal, element) -> subtotal + element);
        if(stepsCount > 20){
            System.out.println("20 ходов недостаточно для приведения всех элементов массива к одному числу");
        } else{
            System.out.println(stepsCount);
        }
    }

    private static double findMedian(List<Integer> list){
        if(list.size() % 2 == 0){
            return (list.get(list.size()/2-1) + list.get(list.size()/2)) / 2;
        } else{
            return list.get((list.size()-1)/2);
        }
    }
}