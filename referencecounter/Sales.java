package referencecounter;

import java.util.HashMap;
import java.util.Map;

// Singleton Child
public class Sales extends ReferenceCounter {
        private Map<String, Integer> sells;
        private static Sales instance = null;

        private Sales(){
            this.sells = new HashMap<String, Integer>();
        }

        public static Sales getInstance(){
            if(instance == null){
                instance = new Sales();
            }
            instance.addReference();
            return instance;
        }

        public int sellsOf(String name) {
            if (sells.containsKey(name)){
                return sells.get(name);
            }else{
                setSells(name, 0);
                return 0;
            }
        }
    
    
        public void setSells(String name, int sell) {
            if (sells.containsKey(name)){
                this.sells.put(name, sellsOf(name)+sell);
            }else{
                this.sells.put(name, sell);
            }
        }

}
