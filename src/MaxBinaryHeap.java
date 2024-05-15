public class MaxBinaryHeap {

    //Variables to control array and its resizes
    private int initialArraySize = 50;
    private int arrayResizeFactor = 2;

    //Contains numbers
    private int[] heap;

    //number of values in heap
    private int numOfValues;

    //lists index of last FILLED slot unless it is empty, in which it is 0
    private int lastIndex;

    //current array size, NOT how many values are in the array
    private int currentArraySize = initialArraySize;

    public MaxBinaryHeap() {
        heap = new int[initialArraySize];
        numOfValues = 0;
    }

    //Functional methods

    //add to heap method
    public void insert(int value) {

        //first add
        if (numOfValues == 0) {
            heap[0] = value;
            lastIndex = 0;

        //other adds
        } else {

            //update the last index variable to reflect add
            lastIndex++;

            //if heap is full, resize
            if (numOfValues == currentArraySize) {
                resize();
            }

            //assign value to next available space
            heap[lastIndex] = value;

            //initalize traversal index
            int currentIndex = lastIndex;

            //set up loop
            boolean loop = true;

            while(loop) {

                //if index is even...
                if (currentIndex%2 == 0) {

                    //...and the value at the index is greater than its parent's value...
                    if (heap[currentIndex] > heap[(currentIndex-2)/2]) {
                        
                        //...swap them and update the currentIndex variable

                        //swap
                        int temp = heap[currentIndex];
                        heap[currentIndex] = heap[(currentIndex-2)/2];
                        heap[(currentIndex-2)/2] = temp;

                        //update
                        currentIndex = (currentIndex-2)/2;

                        //If the current index is now 0, then end the loop
                        if (currentIndex == 0) {
                            break;
                        }
                    
                        //if the parent is greater or equal to child, then stop the loop
                    } else {
                        break;
                    }
                
                //symmetrical to above, with slightly different index calculations for odd index
                } else {

                    if (heap[currentIndex] > heap[(currentIndex-1)/2]) {
                        
                        int temp = heap[currentIndex];
                        heap[currentIndex] = heap[(currentIndex-1)/2];
                        heap[(currentIndex-1)/2] = temp;

                        currentIndex = (currentIndex-1)/2;

                        if (currentIndex == 0) {
                            break;
                        }
                    
                    } else {
                        break;
                    }

                }

            }


        }

        //update number of values variable
        numOfValues++;

    }

    // display the tree by dividing the levels (simple)
    public void display(){
        int incrementor = 0;
        int levelThreshold = 1;

        if (numOfValues == 0) {
            System.out.println("Nothing to display!");
            return;
        }

        while(incrementor <= lastIndex) {
            if (incrementor == levelThreshold) {
                System.out.println("\n----------DIVIDE OF LEVEL----------");
                levelThreshold = (levelThreshold*2)+1;
            }

            System.out.print(heap[incrementor] + " ");
            incrementor++;
        }
    }

    //Accessor method
    public int extractMax() {
        //start at head
        int currentIndex = 0;

        //save the max to return later, "delete" within heap
        int numToReturn = heap[0];
        heap[0] = 0;

        //if nothing in heap
        if (numOfValues == 0) {
            System.out.println("Nothing to return!");
            return -1;

        //if only one thing in heap
        } else if (numOfValues == 1) {
            numOfValues = 0;
            lastIndex = 0;
            return numToReturn;
        }

        //need to rearrange tree, loop only active in the filled binary heap
        while(currentIndex <= lastIndex) {

            //checks two children of current index
            if (heap[(currentIndex*2)+2] >= heap[(currentIndex*2)+1]) {

                //swap if appropriate
                int temp = heap[currentIndex];
                heap[currentIndex] = heap[(currentIndex*2)+2];
                heap[(currentIndex*2)+2] = temp;

                //update current index
                currentIndex = (currentIndex*2)+2;
                
                //if the next swap would go outside of the boundry, then move all digits forward in the heap until the inital 0 value is at the end of the array
                if((currentIndex*2)+1 > lastIndex) {
                    while(currentIndex <= lastIndex) {
                        temp = heap[currentIndex];
                        heap[currentIndex] = heap[currentIndex+1];
                        heap[currentIndex+1] = temp;
                        currentIndex++;
                    }
                    break;
                }

            } else {

                //Code identical to above, aside from different index calculation

                int temp = heap[currentIndex];
                heap[currentIndex] = heap[(currentIndex*2)+1];
                heap[(currentIndex*2)+1] = temp;

                currentIndex = (currentIndex*2)+1;  

                if((currentIndex*2)+1 > lastIndex) {
                    while(currentIndex <= lastIndex) {
                        temp = heap[currentIndex];
                        heap[currentIndex] = heap[currentIndex+1];
                        heap[currentIndex+1] = temp;
                        currentIndex++;
                    }
                    break;
                }

            }

        }

        //update values
        numOfValues--;
        lastIndex--;

        //end method by returning max
        return numToReturn;
    }

    //get functional "length" of heap
    public int getNumOfValues() {
        return numOfValues;
    }

    //helper method
    private void resize() {

        //Transfers values from old array into larger new array

        int[] newHeap =  new int[currentArraySize * arrayResizeFactor];
        currentArraySize *= arrayResizeFactor;

        for(int i = 0; i < heap.length; i++) {
            newHeap[i] = heap[i];
        }

        heap = newHeap;
    }

    
}
