package referencecounter;

//  Refrence counter
abstract class ReferenceCounter {
    private int count = 0;
    
        public ReferenceCounter() {
            super();
        }

    
    public void addReference() {
        count++;
    }

    public void removeReference() {
        if (count > 0) {
            count--;
        }
    }

    public int getCount() {
        return count;
    }

    public void gc(){
        if (count == 0){
            System.gc();
        }
    }

    
}