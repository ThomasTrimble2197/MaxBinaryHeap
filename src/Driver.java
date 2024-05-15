public class Driver {
    public static void main(String[] args) throws Exception {
        
        //first max heap to test resize
        MaxBinaryHeap test = new MaxBinaryHeap();
        
        //requires two resizes, tests insert
        for (int i = 140; i > 0; i--) {
            test.insert(i);
        }

        test.display();

        //second max heap to test other methods
        MaxBinaryHeap test2 = new MaxBinaryHeap();

        //tests error messages
        System.out.println("\n");
        test2.extractMax();
        test2.display();
        System.out.println("\n");
        
        //fill second binary heap
        test2.insert(14);
        test2.insert(12);
        test2.insert(11);
        test2.insert(10);
        test2.insert(9);
        test2.insert(14);
        test2.insert(50);

        //verifies inserts
        test2.display();

        //tests extract max
        test2.extractMax();

        System.out.println("\n\n");

        //verifies extract max
        test2.display();
        
    }
}
